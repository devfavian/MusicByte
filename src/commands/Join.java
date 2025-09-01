package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;

public class Join {
	
	public static String ensureConnection(SlashCommandInteractionEvent event) {
		Member m = event.getMember();
		
		if(m == null || m.getVoiceState() == null)	return "I can't determine your voice state.";
		
		AudioChannelUnion channel = m.getVoiceState().getChannel();
		
		if(channel == null) return "You must be in a voice channel.";
		
		AudioManager audio = event.getGuild().getAudioManager();
		
        // Already connected in a different channel?
        if (audio.isConnected() && audio.getConnectedChannel() != null
                && !audio.getConnectedChannel().equals(channel)) {
            return "I'm already connected to another voice channel.";
        }
        
        // Ensure the sending handler is attached (important after /leave)
        var pm  = utils.PlayerManager.getInstance();
        var gmm = pm.getGuildMusicManager(event.getGuild());
        if (audio.getSendingHandler() == null) {
            audio.setSendingHandler(gmm.getSendHandler());
        }
        
        if(!audio.isConnected()) {
        	audio.openAudioConnection(channel);
        	audio.setSelfDeafened(true);
        }
        
        return null;
		
	}
	
	public void handle(SlashCommandInteractionEvent event) {
        String error = ensureConnection(event);
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(error == null ? "Connected." : "❌ " + error);
        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
	}
	
}

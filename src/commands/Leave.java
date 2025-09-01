package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import utils.PlayerManager;

public class Leave {
	public void handle(SlashCommandInteractionEvent event) {
		VoiceChannel channel = (VoiceChannel) event.getMember().getVoiceState().getChannel();
		AudioManager audio = event.getMember().getGuild().getAudioManager();
		
		
		EmbedBuilder embed = new EmbedBuilder();
		
		if(audio.isConnected() && audio.getConnectedChannel() == channel) {
			PlayerManager.getInstance().stop(event.getGuild());
			audio.setSendingHandler(null);
			audio.closeAudioConnection();
			embed.setDescription("Disconnected");
			event.replyEmbeds(embed.build()).setEphemeral(true).queue();
			return;
		}
		else if(audio.getConnectedChannel() != channel) {
			embed.setDescription("The bot is not in your voice channel");
			event.replyEmbeds(embed.build()).setEphemeral(true).queue();
			return;
		}
		else {
			embed.setDescription("The bot is not in a voice channel");
			event.replyEmbeds(embed.build()).setEphemeral(true).queue();
			return;
		}
	}
}

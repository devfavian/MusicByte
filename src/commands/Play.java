package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.PlayerManager;

public class Play extends ListenerAdapter {

    public void handle(SlashCommandInteractionEvent event) {
        String query = event.getOption("query").getAsString();

        // Reuse the join logic without replying here
        String error = Join.ensureConnection(event);
        if (error != null) {
            event.replyEmbeds(new EmbedBuilder().setDescription("❌ " + error).build())
                 .setEphemeral(true).queue();
            return;
        }

        // If it's not a URL, perform a YouTube search
        String identifier = query.startsWith("http") ? query : "ytsearch:" + query;

        PlayerManager.getInstance().loadAndPlay(event.getGuild(), identifier);
        event.reply("🎶 Loading: `" + query + "`").queue();
    }
}
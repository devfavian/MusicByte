package main;

import commands.*;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
	Join join = new Join();
	Leave leave = new Leave();
	Play play = new Play();
	
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    	
    	switch (event.getName()) {
    		
    	case "join" -> join.handle(event);
    	case "leave" -> leave.handle(event);
    	case "play" -> play.handle(event);
    	}
    }
}
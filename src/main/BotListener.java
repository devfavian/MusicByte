package main;

import commands.*;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
	Join join = new Join();
	
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    	
    	switch (event.getName()) {
    		
    	case "command" -> join.handle(event);    	
    	}
    }
}
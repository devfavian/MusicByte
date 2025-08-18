package main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("YOUR_TOKEN")		//discord developers bot token
        		  .enableIntents(   GatewayIntent.GUILD_MEMBERS,
        				    		GatewayIntent.GUILD_PRESENCES,
        				    		GatewayIntent.GUILD_MESSAGES,
        				    		GatewayIntent.MESSAGE_CONTENT)
                  .addEventListeners(new BotListener())
                  .build()
        		  .awaitReady();
        
        //MODES
        
        boolean DEV_MODE = true;
        
        if(DEV_MODE) {
        	String guildid = "1057987584938233866";		//enable developer mode on discord -> right click on your discord server -> "copy ID"
            Guild testGuild = jda.getGuildById(guildid);
            
            if (testGuild != null) {
                testGuild.updateCommands().addCommands(
                	    Commands.slash("command", "command")
                	        //.addOption(OptionType.USER, "user", "The user to get information about", false)
                ).queue();
                System.out.println("Guild commands saved (DEV_MODE)");
            }
        } else {
        	jda.updateCommands().addCommands(
            	    Commands.slash("command", "command")
            	        //.addOption(OptionType.USER, "user", "The user to get information about", false)
        	).queue();
        	System.out.println("Global commands saved (PROD_MODE)");
        }
    }
}
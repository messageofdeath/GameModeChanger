package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import eu.wServers.messageofdeath.GameModeChanger.API.Eco;
import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class signListener implements Listener {

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] signline = event.getLines();
		if(signline[0].equalsIgnoreCase("[GameMode]") && signline[1].equalsIgnoreCase("[Player]") && signline[2].equalsIgnoreCase("creative") || signline[2].equalsIgnoreCase("survival") || signline[2].equalsIgnoreCase("adventure")) {
			String line3 = signline[2];
			if(player.hasPermission("gamemode.signs.create.player." + line3.toLowerCase())) {
				Eco eco = null;
				if(line3.equalsIgnoreCase("creative")) {
					eco = new Eco(player.getName(), Gamemode.getSignCreativeCreatePrice());
				}
				if(line3.equalsIgnoreCase("survival")) {
					eco = new Eco(player.getName(), Gamemode.getSignSurvivalCreatePrice());
				}
				if(line3.equalsIgnoreCase("adventure")) {
					eco = new Eco(player.getName(), Gamemode.getSignAdventureCreatePrice());
				}
				if(eco.hasAccount()) {
					if(eco.hasEnough()) {
						eco.charge();
						player.sendMessage(Gamemode.getSuccess() + "You successfully purchased a " + line3.replace("s", "S").replace("c", "C") + " Sign with " + eco.getFormat() + "!");
						event.setLine(0, ChatColor.AQUA + "[GameMode]");
						event.setLine(1, ChatColor.AQUA + "[Player]");
						if(line3.equalsIgnoreCase("creative"))
							line3 = ChatColor.GOLD + "Creative";
						if(line3.equalsIgnoreCase("survival"))
							line3 = ChatColor.GOLD + "Survival";
						if(line3.equalsIgnoreCase("adventure"))
							line3 = ChatColor.GOLD + "Adventure";
						event.setLine(2, line3);
					}else {
						player.sendMessage(Gamemode.getError() + "You do not have enough money to make this sign!");
						event.setCancelled(true);
					}
				}else {
					eco.newAccount();
					player.sendMessage(Gamemode.getError() + "You do not have an Account. We made you an account!");
				}
			}else {
				player.sendMessage(Gamemode.getNoPermission());
				int i = 3;
				while (i != -1) {
					event.setLine(i, ChatColor.RED + "Error");
					i--;
				}
			}
		}
		if(signline[0].equalsIgnoreCase("[GameMode]") && signline[1].equalsIgnoreCase("Creative") || signline[1].equalsIgnoreCase("Survival") || signline[1].equalsIgnoreCase("adventure")) {
			String line2 = signline[1];
			if(player.hasPermission("gamemode.signs.create." + line2.toLowerCase())) {
				event.setLine(0, ChatColor.AQUA + "[GameMode]");
				if(line2.equalsIgnoreCase("creative"))
					line2 = ChatColor.GOLD + "Creative";
				if(line2.equalsIgnoreCase("survival"))
					line2 = ChatColor.GOLD + "Survival";
				if(line2.equalsIgnoreCase("adventure"))
					line2 = ChatColor.GOLD + "Adventure";
				event.setLine(1, line2);
				player.sendMessage(Gamemode.getSuccess() + "You have successfully created a " + line2.toLowerCase() + " sign!");
			}else {
				player.sendMessage(Gamemode.getNoPermission());
				int i = 3;
				while (i != -1) {
					event.setLine(i, ChatColor.RED + "Error");
					i--;
				}
			}
		}
		if(signline[0].equalsIgnoreCase("[GameMode]") && signline[1].equalsIgnoreCase("Toggle")) {
			if(player.hasPermission("gamemode.signs.create.toggle")) {
				event.setLine(0, ChatColor.AQUA + "[GameMode]");
				event.setLine(1, ChatColor.GOLD + "Toggle");
				player.sendMessage(Gamemode.getSuccess() + "You have successfully created a toggle sign!");
			}else {
				player.sendMessage(Gamemode.getNoPermission());
				int i = 3;
				while (i != -1) {
					event.setLine(i, ChatColor.RED + "Error");
					i--;
				}
			}
		}
	}
}

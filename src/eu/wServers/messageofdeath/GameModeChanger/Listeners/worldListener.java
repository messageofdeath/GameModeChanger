package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class worldListener implements Listener {

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		World to = player.getWorld();
		World from = event.getFrom();
		if(to != from) {
			if(Gamemode.getWorlds().contains(to.getName())) {
				if(Gamemode.getWorldSupport() == true) {
					if(Gamemode.getGamemode(to).equalsIgnoreCase("creative")) {
						if(player.hasPermission("gamemode.world.creative")) {
							if(player.getGameMode() != Gamemode.getCreative()) {
								player.sendMessage(Gamemode.getMessage(to, player.getName(), "creative"));
								player.setGameMode(Gamemode.getCreative());
							}else {
								player.sendMessage(Gamemode.getSuccess() + "You will not get creative due to you already have creative");
							}
						}else {
							player.sendMessage(Gamemode.getNoPermission());
						}
					}
					if(Gamemode.getGamemode(to).equalsIgnoreCase("survival")) {
						if(player.hasPermission("gamemode.world.survival")) {
							if(player.getGameMode() != Gamemode.getSurvival()) {
								player.sendMessage(Gamemode.getMessage(to, player.getName(), "survival"));
								player.setGameMode(Gamemode.getSurvival());
							}else {
								player.sendMessage(Gamemode.getSuccess() + "You will not get survival due to you already have survival");
							}
						}else {
							player.sendMessage(Gamemode.getNoPermission());
						}
					}
				}
			}else {
				if(player.hasPermission("gamemode.recieve.debug"))
					player.sendMessage(Gamemode.getError() + "This world is not set in the config. You can keep it this way or configure it in the config.");
			}
		}
	}
}

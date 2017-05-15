package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class playerInteractEvent implements Listener {

	@EventHandler
	public void onEvents(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		if(action == Action.RIGHT_CLICK_BLOCK) {
			int block = player.getItemInHand().getType().getId();
			int item = event.getClickedBlock().getType().getId();
			if(player.getGameMode() == Gamemode.getCreative()) {
				if(block == 383) {
					if(!player.hasPermission("gamemode.bypass.creative.spawnegg")) {
						player.sendMessage(Gamemode.getError() + "You cannot use a Spawning Egg in Creative Mode");
						event.setCancelled(true);
					}
				}
				if(item == 23 || item == 58 || item == 61 || item == 54 || item == 84 || item == 116 || item == 342 || item == 343) {
					if(!player.hasPermission("gamemode.bypass.creative.gui")) {
						player.sendMessage(Gamemode.getError() + "You cannot use a gui in creative!");
						event.setCancelled(true);
					}
				}
			}
			if(player.getGameMode() == Gamemode.getSurvival()) {
				if(block == 383) {
					if(!player.hasPermission("gamemode.bypass.survival.spawnegg")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot use a Spawning Egg in Creative Mode");
					}
				}
				if(item == 23 || item == 58 || item == 61 || item == 54 || item == 84 || item == 116 || item == 342 || item == 343) {
					if(!player.hasPermission("gamemode.bypass.survival.gui")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot use a gui in survival!");
					}
				}
			}
		}
		if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			int block = player.getItemInHand().getType().getId();
			if(block == 384 || block == 373) {
				// block potions
				if(player.getGameMode() == Gamemode.getCreative()) {
					if(!player.hasPermission("gamemode.bypass.creative.potions")) {
						player.sendMessage(Gamemode.getError() + "You cannot use Potions in Creative Mode");
						event.setCancelled(true);
					}
				}
				if(player.getGameMode() == Gamemode.getSurvival()) {
					if(!player.hasPermission("gamemode.bypass.survival.potions")) {
						player.sendMessage(Gamemode.getError() + "You cannot use Potions in Survival Mode");
						event.setCancelled(true);
					}
				}
			}
			// block snow balls
			if(block == 332) {
				if(player.getGameMode() == Gamemode.getCreative()) {
					if(!player.hasPermission("gamemode.bypass.creative.snowballs")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot use Snow Balls in Creative Mode!");
					}
				}
				if(player.getGameMode() == Gamemode.getSurvival()) {
					if(!player.hasPermission("gamemode.bypass.survival.snowballs")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot use Snow Balls in Survival Mode!");
					}
				}
			}
			// block ender pearls
			if(block == 368) {
				if(player.getGameMode() == Gamemode.getCreative()) {
					if(!player.hasPermission("gamemode.bypass.creative.enderpearl")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot use an Ender Pearl in creative!");
					}
				}
				if(player.getGameMode() == Gamemode.getSurvival()) {
					if(!player.hasPermission("gamemode.bypass.survival.enderpearl")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot use an Ender Pearl in survival!");
					}
				}
			}
			// block chicken eggs
			if(block == 344) {
				if(player.getGameMode() == Gamemode.getCreative()) {
					if(!player.hasPermission("gamemode.bypass.creative.egg")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot throw an egg in creative!");
					}
				}
				if(player.getGameMode() == Gamemode.getSurvival()) {
					if(!player.hasPermission("gamemode.bypass.survival.egg")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + "You cannot throw an egg in survival!");
					}
				}
			}
		}
	}
}

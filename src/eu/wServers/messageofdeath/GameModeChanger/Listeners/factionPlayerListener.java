package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class factionPlayerListener implements Listener {

	// On drop with factions enabled
	@EventHandler
	public void onFactionDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(Gamemode.useFactions() == true) {
			FLocation loc = new FLocation(player.getLocation());
			if(Board.getFactionAt(loc).isWarZone() == true) {
				if(!player.hasPermission("gamemode.bypass.factions.warzone.drop")) {
					event.setCancelled(true);
					player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions]" + ChatColor.RED + " You cannot drop items in a war zone!");
				}
			}
			if(Board.getFactionAt(loc).isSafeZone() == true) {
				if(!player.hasPermission("gamemode.bypass.factions.safezone.drop")) {
					event.setCancelled(true);
					player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions]" + ChatColor.RED + " You cannot drop items in a safe zone!");
				}
			}
		}
	}

	@EventHandler
	public void onFactionInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		FLocation loc = new FLocation(player.getLocation());
		if(Gamemode.useFactions() == true) {
			if(Board.getFactionAt(loc).isWarZone()) {
				if(action == Action.RIGHT_CLICK_BLOCK) {
					int block = player.getItemInHand().getType().getId();
					int item = event.getClickedBlock().getType().getId();
					if(block == 383) {
						if(!player.hasPermission("gamemode.bypass.factions.warzone.spawnegg")) {
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use a Spawning Egg in a war zone!");
							event.setCancelled(true);
						}
					}
					if(item == 23 || item == 58 || item == 61 || item == 54 || item == 84 || item == 116 || item == 342 || item == 343) {
						if(!player.hasPermission("gamemode.bypass.factions.warzone.gui")) {
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use a gui in a war zone!");
							event.setCancelled(true);
						}
					}
				}
				if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
					int block = player.getItemInHand().getType().getId();
					if(block == 384 || block == 373) {
						if(!player.hasPermission("gamemode.bypass.factions.warzone.potions")) {
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Potions in a war zone!");
							event.setCancelled(true);
						}
					}
					if(block == 368) {
						if(!player.hasPermission("gamemode.bypass.factions.warzone.enderpearl")) {
							event.setCancelled(true);
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Ender Pearls in a war zone!");
						}
					}
					if(block == 332) {
						if(!player.hasPermission("gamemode.bypass.factions.warzone.snowball")) {
							event.setCancelled(true);
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Snow Balls in a war zone!");
						}
					}
					if(block == 344) {
						if(!player.hasPermission("gamemode.bypass.factions.warzone.egg")) {
							event.setCancelled(true);
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Chicken Eggs in a war zone!");

						}
					}
				}
			}
		}
		if(Board.getFactionAt(loc).isSafeZone()) {
			if(action == Action.RIGHT_CLICK_BLOCK) {
				int block = player.getItemInHand().getType().getId();
				int item = event.getClickedBlock().getType().getId();
				if(block == 383) {
					if(!player.hasPermission("gamemode.bypass.factions.safezone.spawnegg")) {
						player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use a Spawning Egg in a safe zone!");
						event.setCancelled(true);
					}
				}
				if(item == 23 || item == 58 || item == 61 || item == 54 || item == 84 || item == 116 || item == 342 || item == 343) {
					if(!player.hasPermission("gamemode.bypass.factions.safezone.gui")) {
						player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use a gui in a safe zone!");
						event.setCancelled(true);
					}
				}
			}
			if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				int block = player.getItemInHand().getType().getId();
				if(block == 384 || block == 373) {
					if(!player.hasPermission("gamemode.bypass.factions.safezone.potions")) {
						player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Potions in a safe zone!");
						event.setCancelled(true);
					}
				}
				if(block == 368) {
					if(!player.hasPermission("gamemode.bypass.factions.safezone.enderpearl")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Ender Pearls in a safe zone!");
					}
				}
				if(block == 332) {
					if(!player.hasPermission("gamemode.bypass.factions.safezone.snowball")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Snow Balls in a safe zone!");
					}
				}
				if(block == 344) {
					if(!player.hasPermission("gamemode.bypass.factions.safezone.egg")) {
						event.setCancelled(true);
						player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Factions] " + ChatColor.RED + "You cannot use Chicken Eggs in a safe zone!");

					}
				}
			}
		}
	}
}

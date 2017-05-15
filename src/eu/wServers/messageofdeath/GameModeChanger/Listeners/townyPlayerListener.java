package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.palmergames.bukkit.towny.Towny;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class townyPlayerListener implements Listener {

	// On drop with towny enabled
	@SuppressWarnings("static-access")
	@EventHandler
	public void onTownyDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(Gamemode.useTowny() == true) {
			Towny towny = (Towny) Bukkit.getPluginManager().getPlugin("Towny");
			if(towny.getTownyUniverse().isWarTime() == true) {
				if(!player.hasPermission("gamemode.bypass.towny.war.drop")) {
					event.setCancelled(true);
					player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny]" + ChatColor.RED + " You cannot drop items during a war!");
				}
			}
		}
	}

	@SuppressWarnings("static-access")
	@EventHandler
	public void onTownyInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		if(Gamemode.useTowny() == true) {
			Towny towny = (Towny) Bukkit.getPluginManager().getPlugin("Towny");
			if(towny.getTownyUniverse().isWarTime() == true) {
				if(action == Action.RIGHT_CLICK_BLOCK) {
					int block = player.getItemInHand().getType().getId();
					int item = event.getClickedBlock().getType().getId();
					if(block == 383) {
						if(!player.hasPermission("gamemode.bypass.towny.war.spawnegg")) {
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You cannot use a Spawning Egg during a war!");
							event.setCancelled(true);
						}
					}
					if(item == 23 || item == 58 || item == 61 || item == 54 || item == 84 || item == 116 || item == 342 || item == 343) {
						if(!player.hasPermission("gamemode.bypass.towny.war.gui")) {
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You cannot use a gui during a war!");
							event.setCancelled(true);
						}
					}
				}
				if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
					int block = player.getItemInHand().getType().getId();
					if(block == 384 || block == 373) {
						if(!player.hasPermission("gamemode.bypass.towny.war.potions")) {
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You cannot use Potions during a war!");
							event.setCancelled(true);
						}
					}
					if(block == 332) {
						if(!player.hasPermission("gamemode.bypass.towny.war.snowball")) {
							event.setCancelled(true);
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You cannot use Snow Balls during a war!");
						}
					}
					if(block == 368) {
						if(!player.hasPermission("gamemode.bypass.towny.war.enderpearl")) {
							event.setCancelled(true);
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You cannot use Ender Pearls during a war!");
						}
					}
					if(block == 344) {
						if(!player.hasPermission("gamemode.bypass.towny.war.egg")) {
							event.setCancelled(true);
							player.sendMessage(Gamemode.getError() + ChatColor.GOLD + "[Towny] " + ChatColor.RED + "You cannot use Chicken Eggs during a war!");

						}
					}
				}
			}
		}
	}
}

package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class blockListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block org = event.getBlock();
		Material north = org.getRelative(BlockFace.NORTH).getType();
		Material south = org.getRelative(BlockFace.SOUTH).getType();
		Material west = org.getRelative(BlockFace.WEST).getType();
		Material east = org.getRelative(BlockFace.EAST).getType();
		if(!player.hasPermission("gamemode.sign.break")) {
			if(org.getLocation().getBlock().getType() == Material.SIGN || org.getLocation().getBlock().getType() == Material.SIGN_POST || org.getLocation().getBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) org.getState();
				if(sign.getLine(0).contains("[GameMode]")) {
					player.sendMessage(Gamemode.getError() + "You do not have permission to destroy that sign!");
					event.setCancelled(true);
				}
			}
			if(north == Material.SIGN || north == Material.WALL_SIGN || north == Material.SIGN_POST) {
				Sign sign = (Sign) org.getRelative(BlockFace.NORTH).getLocation().getBlock().getState();
				if(sign.getLine(0).contains("[GameMode]")) {
					player.sendMessage(Gamemode.getError() + "You do not have permission to destroy that sign!");
					event.setCancelled(true);
				}
			}
			if(south == Material.SIGN || south == Material.WALL_SIGN || south == Material.SIGN_POST) {
				Sign sign = (Sign) org.getRelative(BlockFace.SOUTH).getLocation().getBlock().getState();
				if(sign.getLine(0).contains("[GameMode]")) {
					player.sendMessage(Gamemode.getError() + "You do not have permission to destroy that sign!");
					event.setCancelled(true);
				}
			}
			if(west == Material.SIGN || west == Material.WALL_SIGN || west == Material.SIGN_POST) {
				Sign sign = (Sign) org.getRelative(BlockFace.WEST).getLocation().getBlock().getState();
				if(sign.getLine(0).equalsIgnoreCase("[GameMode]")) {
					player.sendMessage(Gamemode.getError() + "You do not have permission to destroy that sign!");
					event.setCancelled(true);
				}
			}
			if(east == Material.SIGN || east == Material.WALL_SIGN || east == Material.SIGN_POST) {
				Sign sign = (Sign) org.getRelative(BlockFace.EAST).getLocation().getBlock().getState();
				if(sign.getLine(0).contains("[GameMode]")) {
					player.sendMessage(Gamemode.getError() + "You do not have permission to destroy that sign!");
					event.setCancelled(true);
				}
			}
		}
	}
}

package eu.wServers.messageofdeath.GameModeChanger.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.wServers.messageofdeath.GameModeChanger.API.Eco;
import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class playerListener implements Listener {

	// On Drop with towny nor factions enabled
	@EventHandler
	public void onRealDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(Gamemode.useFactions() == false && Gamemode.useTowny() == false) {
			if(player.getGameMode() == Gamemode.getCreative()) {
				Gamemode.dropItem(Gamemode.getCreative(), player, event);
			}
			if(player.getGameMode() == Gamemode.getSurvival()) {
				Gamemode.dropItem(Gamemode.getSurvival(), player, event);
			}
		}
	}

	@EventHandler
	public void onSign(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK)
			if(event.getClickedBlock().getType() == Material.WALL_SIGN || event.getClickedBlock().getType() == Material.SIGN_POST) {
				if(Gamemode.getSignSupport() == true) {
					Player player = event.getPlayer();
					Block block = event.getClickedBlock();
					if(block != null) {
						BlockState state = block.getState();
						Sign sign = (Sign) state;
						String signline = sign.getLine(0);
						String signline2 = sign.getLine(1);
						String signline3 = sign.getLine(2);
						if(state instanceof Sign) {
							if(signline.contains("[GameMode]") && signline2.contains("Creative") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.creative") || player.hasPermission("gamemode.*")) {
									Gamemode.setPlayerGamemode(player, Gamemode.getCreative(), "non toggle");
								}else {
									player.sendMessage(Gamemode.getNoPermission());
								}
							}
							if(signline.contains("[GameMode]") && signline2.contains("Adventure") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.adventure") || player.hasPermission("gamemode.*")) {
									Gamemode.setPlayerGamemode(player, Gamemode.getAdventure(), "non toggle");
								}else {
									player.sendMessage(Gamemode.getNoPermission());
								}
							}
							if(signline.contains("[GameMode]") && signline2.contains("Survival") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.survival") || player.hasPermission("gamemode.*")) {
									Gamemode.setPlayerGamemode(player, Gamemode.getSurvival(), "non toggle");
								}
							}
							if(signline.contains("[GameMode]") && signline2.contains("Toggle") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.toggle") || player.hasPermission("gamemode.*")) {
									Gamemode.setPlayerGamemode(player, null, "toggle");
								}else {
									player.sendMessage(Gamemode.getNoPermission());
								}
							}
							if(signline.contains("[GameMode]") && signline2.contains("[Player]") && signline3.contains("Creative") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.player.creative") || player.hasPermission("gamemode.*")) {
									Eco eco = new Eco(player, Gamemode.getSignCreativeUsePrice());
									if(eco.hasAccount()) {
										if(eco.hasEnough()) {
											Gamemode.setSignPlayerGamemode(player, Gamemode.getCreative(), eco);
											return;
										}else {
											player.sendMessage(Gamemode.getError() + "You do not have enough money! You need " + ChatColor.GOLD + eco.getFormat() + ChatColor.RED + "!");
											return;
										}
									}else {
										eco.newAccount();
										player.sendMessage(Gamemode.getError() + "You do not have an account. We made you an account!");
										return;
									}
								}else {
									player.sendMessage(Gamemode.getNoPermission());
								}
							}
							if(signline.contains("[GameMode]") && signline2.contains("[Player]") && signline3.contains("Adventure") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.player.adventure") || player.hasPermission("gamemode.*")) {
									Eco eco = new Eco(player, Gamemode.getSignAdventureUsePrice());
									if(eco.hasAccount()) {
										if(eco.hasEnough()) {
											Gamemode.setSignPlayerGamemode(player, Gamemode.getAdventure(), eco);
											return;
										}else {
											player.sendMessage(Gamemode.getError() + "You do not have enough money! You need " + ChatColor.GOLD + eco.getFormat() + ChatColor.RED + "!");
											return;
										}
									}else {
										eco.newAccount();
										player.sendMessage(Gamemode.getError() + "You do not have an account. We made you an account!");
										return;
									}
								}else {
									player.sendMessage(Gamemode.getNoPermission());
								}
							}
							if(signline.contains("[GameMode]") && signline2.contains("[Player]") && signline3.contains("Survival") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
								if(player.hasPermission("gamemode.sign.interact.player.survival") || player.hasPermission("gamemode.*")) {
									Eco eco = new Eco(player, Gamemode.getSignSurvivalUsePrice());
									if(eco.hasAccount()) {
										if(eco.hasEnough()) {
											Gamemode.setSignPlayerGamemode(player, Gamemode.getSurvival(), eco);
											return;
										}else {
											player.sendMessage(Gamemode.getError() + "You do not have enough money! You need " + ChatColor.GOLD + eco.getFormat() + ChatColor.RED + "!");
											return;
										}
									}else {
										eco.newAccount();
										player.sendMessage(Gamemode.getError() + "You do not have an account. We made you an account!");
										return;
									}
								}else {
									player.sendMessage(Gamemode.getNoPermission());
								}
							}
						}
					}
				}
			}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(Gamemode.getJoinSupport() == true) {
			String gm1 = Gamemode.getJoinGamemode();
			GameMode gm = null;
			if(gm1.equalsIgnoreCase("creative"))
				gm = Gamemode.getCreative();
			if(gm1.equalsIgnoreCase("survival"))
				gm = Gamemode.getSurvival();
			if(gm1.equalsIgnoreCase("adventure"))
				gm = Gamemode.getAdventure();
			if(gm != null) {
				if(!player.hasPermission("gamemode.bypass.log.join")) {
					player.setGameMode(gm);
					player.sendMessage(Gamemode.getSuccess() + "You were changed to " + ChatColor.GOLD + gamemode(gm.name()) + " Mode " + ChatColor.GREEN + "on login!");
				}
			}else {
				player.sendMessage(Gamemode.getError() + "Please notify an admin as soon as possible! The config is not set correctly!");
			}
		}
		return;
	}
	
	public String gamemode(String gamemode) {
		if(gamemode.equalsIgnoreCase("creative"))return "Creative";
		if(gamemode.equalsIgnoreCase("adventure"))return "Adventure";
		if(gamemode.equalsIgnoreCase("survival"))return "Survival";
		return null;
	}
}
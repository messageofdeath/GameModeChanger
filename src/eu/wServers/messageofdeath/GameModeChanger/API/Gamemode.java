package eu.wServers.messageofdeath.GameModeChanger.API;

import java.io.IOException;
import java.util.List;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;

import eu.wServers.messageofdeath.GameModeChanger.GameModeChanger;

public class Gamemode {

	public static GameModeChanger plugin;

	public Gamemode(GameModeChanger instance) {
		plugin = instance;
	}

	// gets
	public static String getAllPlayersGamemode() {
		String gm = null;
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if(gm == null) {
				gm = Gamemode.getSuccess() + ChatColor.RED + player.getName() + ChatColor.WHITE + "," + ChatColor.GREEN + player.getGameMode().name().toLowerCase();
			}else {
				gm = gm + ChatColor.WHITE + "; " + ChatColor.RED + player.getName() + ChatColor.WHITE + "," + ChatColor.GREEN + player.getGameMode().name().toLowerCase();
			}
		}
		return gm;
	}

	public static void dropItem(GameMode gamemode, Player player, PlayerDropItemEvent event) {
		if(!player.hasPermission("gamemode.bypass." + gamemode.name().toLowerCase() + ".drop")) {
			event.setCancelled(true);
		}
	}

	public static GameModeChanger getPlugin() {
		return plugin;
	}

	public static String getSuccess() {
		return Chat.SUCCESS.getChat();
	}

	public static boolean useFactions() {
		return Gamemode.getConfig().getBoolean("Plugin.Use.Factions");
	}

	public static boolean useTowny() {
		return Gamemode.getConfig().getBoolean("Plugin.Use.Towny");
	}

	public static boolean useVault() {
		return Gamemode.getConfig().getBoolean("Plugin.Use.Vault_Economy");
	}

	public static Economy getVault() {
		return GameModeChanger.economy;
	}

	public static Eco getEconomy(Player player, double amount) {
		return new Eco(player, amount);
	}

	public static double getCommandCreativePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Command.Creative");
	}

	public static double getCommandSurvivalPrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Command.Survival");
	}

	public static double getCommandAdventurePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Command.Adventure");
	}

	public static double getSignAdventureCreatePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Sign.Create.Adventure");
	}

	public static double getSignCreativeCreatePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Sign.Create.Creative");
	}

	public static double getSignSurvivalCreatePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Sign.Create.Survival");
	}

	public static double getSignAdventureUsePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Sign.Use.Adventure");
	}

	public static double getSignSurvivalUsePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Sign.Use.Survival");
	}

	public static double getSignCreativeUsePrice() {
		return Gamemode.getConfig().getDouble("Vault.Prices.Sign.Use.Creative");
	}

	public static void getHelp(int page, Player player) {
		if(page > 3)
			return;
		if(page < 1)
			return;
		if(page == 1) {
			player.sendMessage(Gamemode.getHelp() + ChatColor.GOLD + Gamemode.getPluginName() + ChatColor.RED + "v" + Gamemode.getPluginVersion());
			player.sendMessage(Gamemode.getHelp() + "Commands:");
			player.sendMessage(Gamemode.getHelp() + "[] are required   ||  <> optional");
			player.sendMessage(Gamemode.getHelp() + "/gm [cre|creative|1] <player> || Change a player's gamemode to creative or yours");
			player.sendMessage(Gamemode.getHelp() + "/gm [sur|survival|0] <player> || Change a player's gamemode to survival or yours");
			player.sendMessage(Gamemode.getHelp() + "/gm [adv|adventur|2] <player> || Change a player's gamemode to adventure or yours");
			player.sendMessage(Gamemode.getHelp() + "/gs <player> || Change a player's gamemode to survival or yours");
			player.sendMessage(Gamemode.getHelp() + "/gc <player> || Change a player's gamemode to creative or yours");
			player.sendMessage(Gamemode.getHelp() + "/gt <player> || Toggle a player's gamemode or yours");
			player.sendMessage(Gamemode.getHelp() + "/gm [help | sign | playersign | 1  | 2 | 3]  || Get help");
		}
		if(page == 2) {
			player.sendMessage(Gamemode.getHelp() + ChatColor.GOLD + Gamemode.getPluginName() + ChatColor.RED + "v" + Gamemode.getPluginVersion());
			player.sendMessage(Gamemode.getHelp() + "How to make a sign:");
			player.sendMessage(Gamemode.getHelp() + "Line 1 = [Gamemode]");
			player.sendMessage(Gamemode.getHelp() + "Line 2 = Creative   |   Survival  | Toggle");
			player.sendMessage(Gamemode.getHelp() + "Line 3 = (Nothing required) You can place a note here");
			player.sendMessage(Gamemode.getHelp() + "Line 4 = (Nothing required) You can place a note here");
		}
		if(page == 3) {
			player.sendMessage(Gamemode.getHelp() + ChatColor.GOLD + Gamemode.getPluginName() + ChatColor.RED + "v" + Gamemode.getPluginVersion());
			player.sendMessage(Gamemode.getHelp() + "How to make a player sign:");
			player.sendMessage(Gamemode.getHelp() + "Line 1 = [GameMode]");
			player.sendMessage(Gamemode.getHelp() + "Line 2 = [Player]");
			player.sendMessage(Gamemode.getHelp() + "Line 3 = Create  |  Survival");
			player.sendMessage(Gamemode.getHelp() + "Line 4 = (Nothing required) You can place a not here");
		}
	}

	public static String getMessage(World world, String name, String gamemode) {
		return Gamemode.getConfig().getString("World." + world.getName() + ".message").replace("+w", world.getName()).replace("+n", name).replace("+gm", gamemode).replaceAll("(&([a-fk-or0-9]))", "\u00A7$2");
	}

	public static String getGamemode(World world) {
		return Gamemode.getConfig().getString("World." + world.getName() + ".gamemode");
	}

	public static String getError() {
		return Chat.ERROR.getChat();
	}

	public static String getAdventureError() {
		return Chat.ERRORA.getChat();
	}

	public static List<String> getWorlds() {
		return Gamemode.getConfig().getStringList("Worlds");
	}

	public static FileConfiguration getDatabase() {
		return GameModeChanger.db;
	}

	public static void saveDatabase() {
		try {
			getDatabase().save(Gamemode.getPlugin().database);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getPluginName() {
		return GameModeChanger.name;
	}

	public static boolean getOnListAll() {
		return Gamemode.getConfig().getBoolean("Plugin.onListAllGamemodesDisplayOfflinePlayers");
	}

	public static String getPluginVersion() {
		return GameModeChanger.version;
	}

	public static boolean getAnnounceToPlayers() {
		return Gamemode.getConfig().getBoolean("Plugin.Announce.gamemodechange");
	}

	public static boolean getEnabled() {
		return Gamemode.getConfig().getBoolean("Plugin.enabled");
	}

	public static String getCreativeError() {
		return Chat.ERRORC.getChat();
	}

	public static String getSurvivalError() {
		return Chat.ERRORS.getChat();
	}

	public static String getNoPermission() {
		return Chat.NOPERMISSION.getChat();
	}

	public static String getConsoleError() {
		return Chat.CONSOLEERROR.getChat();
	}

	public static String getHelp() {
		return Chat.HELP.getChat();
	}

	public static FileConfiguration getConfig() {
		return GameModeChanger.config;
	}

	public static String getDefaultGamemode() {
		String d = Bukkit.getServer().getDefaultGameMode().name().toLowerCase();
		String ds = Gamemode.getSuccess() + "The default gamemode is:" + ChatColor.RED + " " + d;
		return ds;
	}

	public static String getNoOtherPermission() {
		return Chat.NOOTHERPERMISSION.getChat();
	}

	public static String getPlayerDoesNotExist() {
		return Chat.PLAYERDOESNOTEXIST.getChat();
	}

	public static boolean getVerboseLogging() {
		return Gamemode.getConfig().getBoolean("Plugin.logging");
	}

	public static boolean getSignSupport() {
		return Gamemode.getConfig().getBoolean("Plugin.sign_support");
	}

	public static boolean getWorldSupport() {
		return Gamemode.getConfig().getBoolean("Plugin.world_support");
	}

	public static boolean getJoinSupport() {
		return Gamemode.getConfig().getBoolean("Plugin.Change.onJoin.enabled");
	}

	public static String getJoinGamemode() {
		return Gamemode.getConfig().getString("Plugin.Change.onJoin.gamemode");
	}

	public static String getCreativeOtherError(String name) {
		return Chat.ERRORCP.getChat().replace("+n", name);
	}

	public static String getSurvivalOtherError(String name) {
		return Chat.ERRORSP.getChat().replace("+n", name);
	}

	public static String getInvalidArgs() {
		return Chat.INVAILDARGS.getChat();
	}

	public static GameMode getCreative() {
		return GameMode.CREATIVE;
	}

	public static GameMode getSurvival() {
		return GameMode.SURVIVAL;
	}

	public static GameMode getAdventure() {
		return GameMode.ADVENTURE;
	}

	// Sets
	public static void setAllPlayersGamemode(GameMode gamemode) {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.setGameMode(gamemode);
		}
	}

	public static void setPlayerGamemode(Player player, GameMode gamemode, String param) {
		if(param.equalsIgnoreCase("toggle")) {
			if(player.getGameMode() == GameMode.CREATIVE) {
				player.setGameMode(GameMode.SURVIVAL);
			}else if(player.getGameMode() == GameMode.SURVIVAL) {
				player.setGameMode(GameMode.ADVENTURE);
			}else if(player.getGameMode() == GameMode.ADVENTURE) {
				player.setGameMode(GameMode.CREATIVE);
			}
		}
		if(param.equalsIgnoreCase("non toggle")) {
			player.setGameMode(gamemode);
		}
	}

	public static void setPlayerGamemode(Player player, GameMode gamemode, boolean support) {
		if(player.hasPermission("gamemode.change") && player.hasPermission("gamemode." + gamemode.name().toLowerCase() + ".change.self")) {
			if(gamemode == Gamemode.getCreative()) {
				if(player.getGameMode() == Gamemode.getSurvival()) {
					player.setGameMode(Gamemode.getCreative());
					if(support == false) {
						player.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + "!");
					}
				}else {
					player.sendMessage(Gamemode.getCreativeError());
				}
			}
			if(gamemode == Gamemode.getSurvival()) {
				if(player.getGameMode() == Gamemode.getCreative()) {
					player.setGameMode(Gamemode.getSurvival());
					if(support == false) {
						player.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + "!");
					}
				}else {
					player.sendMessage(Gamemode.getSurvivalError());
				}
			}
		}else {
			player.sendMessage(Gamemode.getNoPermission());
		}
	}

	public static void setSignPlayerGamemode(Player player, GameMode gm, Eco eco) {
		if(player.hasPermission("gamemode.change")) {
			if(gm == Gamemode.getCreative()) {
				if(!(player.getGameMode() == Gamemode.getCreative())) {
					eco.charge();
					player.setGameMode(gm);
					player.sendMessage(Gamemode.getSuccess() + "You spent " + eco.getFormat() + " on creative mode from a sign!");
				}else {
					player.sendMessage(Gamemode.getCreativeError());
				}
			}
			if(gm == Gamemode.getSurvival()) {
				if(player.getGameMode() != gm) {
					eco.charge();
					player.setGameMode(gm);
					player.sendMessage(Gamemode.getSuccess() + "You spent " + eco.getFormat() + " on survival mode from a sign!");
				}else {
					player.sendMessage(Gamemode.getSurvivalError());
				}
			}
		}
	}

	public static void setOtherPlayerGamemode(CommandSender player, Player targetplayer, GameMode gamemode, boolean console) {
		if(console == false) {
			if(player.hasPermission("gamemode.change") && player.hasPermission("gamemode." + gamemode.name().toLowerCase() + ".change.player")) {
				if(targetplayer != null) {
					if(gamemode == Gamemode.getCreative()) {
						if(targetplayer.getGameMode() == Gamemode.getSurvival()) {
							targetplayer.setGameMode(Gamemode.getCreative());
							player.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + "!");
							targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + player.getName());
						}else {
							player.sendMessage(Gamemode.getCreativeOtherError(targetplayer.getName()));
						}
					}
					if(gamemode == Gamemode.getSurvival()) {
						if(targetplayer.getGameMode() == Gamemode.getCreative()) {
							targetplayer.setGameMode(Gamemode.getSurvival());
							player.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + "!");
							targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + player.getName());
						}else {
							player.sendMessage(Gamemode.getSurvivalOtherError(targetplayer.getName()));
						}
					}
				}else {
					player.sendMessage(Gamemode.getPlayerDoesNotExist());
				}
			}else {
				player.sendMessage(Gamemode.getNoOtherPermission());
			}
		}else {
			ConsoleCommandSender p = Bukkit.getServer().getConsoleSender();
			if(targetplayer != null) {
				if(gamemode == Gamemode.getCreative()) {
					if(targetplayer.getGameMode() == Gamemode.getSurvival()) {
						targetplayer.setGameMode(Gamemode.getCreative());
						p.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + "!");
						targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + p.getName());
					}else {
						p.sendMessage(Gamemode.getCreativeOtherError(targetplayer.getName()));
					}
				}
				if(gamemode == Gamemode.getSurvival()) {
					if(targetplayer.getGameMode() == Gamemode.getCreative()) {
						targetplayer.setGameMode(Gamemode.getSurvival());
						p.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + "!");
						targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + p.getName());
					}else {
						player.sendMessage(Gamemode.getSurvivalOtherError(targetplayer.getName()));
					}
				}
			}else {
				p.sendMessage(Gamemode.getPlayerDoesNotExist());
			}
		}
	}

	public static void setOtherPlayerGamemode(Player player, Player targetplayer, GameMode gamemode, boolean console) {
		if(console == false) {
			if(player.hasPermission("gamemode.change") && player.hasPermission("gamemode." + gamemode.name().toLowerCase() + ".change.player")) {
				if(targetplayer != null) {
					if(gamemode == Gamemode.getCreative()) {
						if(targetplayer.getGameMode() == Gamemode.getSurvival()) {
							targetplayer.setGameMode(Gamemode.getCreative());
							player.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + "!");
							targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + player.getName());
						}else {
							player.sendMessage(Gamemode.getCreativeOtherError(targetplayer.getName()));
						}
					}
					if(gamemode == Gamemode.getSurvival()) {
						if(targetplayer.getGameMode() == Gamemode.getCreative()) {
							targetplayer.setGameMode(Gamemode.getSurvival());
							player.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + "!");
							targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + player.getName());
						}else {
							player.sendMessage(Gamemode.getSurvivalOtherError(targetplayer.getName()));
						}
					}
				}else {
					player.sendMessage(Gamemode.getPlayerDoesNotExist());
				}
			}else {
				player.sendMessage(Gamemode.getNoOtherPermission());
			}
		}else {
			ConsoleCommandSender p = Bukkit.getServer().getConsoleSender();
			if(targetplayer != null) {
				if(gamemode == Gamemode.getCreative()) {
					if(targetplayer.getGameMode() == Gamemode.getSurvival()) {
						targetplayer.setGameMode(Gamemode.getCreative());
						p.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + "!");
						targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Creative Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + p.getName());
					}else {
						p.sendMessage(Gamemode.getCreativeOtherError(targetplayer.getName()));
					}
				}
				if(gamemode == Gamemode.getSurvival()) {
					if(targetplayer.getGameMode() == Gamemode.getCreative()) {
						targetplayer.setGameMode(Gamemode.getSurvival());
						p.sendMessage(Gamemode.getSuccess() + ChatColor.BLUE + targetplayer.getName() + ChatColor.GREEN + " is now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + "!");
						targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + "Survival Mode" + ChatColor.GREEN + " by " + ChatColor.BLUE + p.getName());
					}else {
						player.sendMessage(Gamemode.getSurvivalOtherError(targetplayer.getName()));
					}
				}
			}else {
				p.sendMessage(Gamemode.getPlayerDoesNotExist());
			}
		}
	}

	public static void setDefaultGamemode(GameMode gamemode) {
		Bukkit.getServer().setDefaultGameMode(gamemode);
		Bukkit.getServer().broadcastMessage(Gamemode.getSuccess() + "The default gamemode of the server has been changed to " + ChatColor.GOLD + gamemode.name().toLowerCase());
	}
}
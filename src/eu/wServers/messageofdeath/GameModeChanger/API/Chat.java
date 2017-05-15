package eu.wServers.messageofdeath.GameModeChanger.API;

import org.bukkit.ChatColor;

// Inspired by Wundark
public enum Chat {

	SUCCESS(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.GREEN),
	NOPERMISSION(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "You do not have permission to use this."),
	NOOTHERPERMISSION(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "You do not have permission to use this for someone."),
	ERROR(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED),
	ERRORC(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "You are already in Creative Mode!"),
	ERRORS(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "You are already in Survival Mode!"),
	ERRORA(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "You are already in Adventure Mode!"),
	CONSOLEERROR(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "You cannot change your gamemode!"),
	ERRORCP(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.BLUE + "+n" + ChatColor.RED + " is already in " + ChatColor.GOLD + "Creative Mode" + ChatColor.RED + "!"),
	ERRORSP(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.BLUE + "+n" + ChatColor.RED + " is already in " + ChatColor.GOLD + "Survival Mode" + ChatColor.RED + "!"),
	PLAYERDOESNOTEXIST(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "That player does not exist!"),
	HELP(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.BLUE),
	INVAILDARGS(ChatColor.BLUE + "[" + ChatColor.GOLD + "GMC" + ChatColor.BLUE + "] " + ChatColor.RED + "Invalid Arguments!");

	private String message = null;

	Chat(String msg) {
		message = msg;
	}

	public String getChat() {
		return message;
	}
}

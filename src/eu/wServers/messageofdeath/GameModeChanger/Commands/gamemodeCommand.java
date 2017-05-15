package eu.wServers.messageofdeath.GameModeChanger.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.wServers.messageofdeath.GameModeChanger.API.Eco;
import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class gamemodeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String commandLabel, String[] args) {
		if(p instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("gamemode")) {
				if(Gamemode.useVault() == true) {
					Player player = (Player) p;
					if(player.hasPermission("gamemode.change")) {
						if(args.length == 0) {
							player.sendMessage(Gamemode.getHelp() + "Please use /gamemode [cre|creative|0///sur|survival|1///adv|adventure|2]");
						}
						if(args.length == 1) {
							Eco eco = null;
							GameMode gm = null;
							if(args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("adv") || args[0].equalsIgnoreCase("2")) {
								eco = new Eco(player.getName(), Gamemode.getCommandAdventurePrice());
								gm = Gamemode.getAdventure();
							}
							if(args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("cre") || args[0].equalsIgnoreCase("1")) {
								eco = new Eco(player.getName(), Gamemode.getCommandCreativePrice());
								gm = Gamemode.getCreative();
							}
							if(args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sur") || args[0].equalsIgnoreCase("0")) {
								eco = new Eco(player.getName(), Gamemode.getCommandSurvivalPrice());
								gm = Gamemode.getSurvival();
							}
							if(player.getGameMode() != gm) {
								if(eco.hasAccount()) {
									if(eco.hasEnough()) {
										eco.charge();
										player.setGameMode(gm);
										player.sendMessage(Gamemode.getSuccess() + "You have bought " + gm.name().toLowerCase() + " mode for " + eco.getFormat());
										if(Gamemode.getAnnounceToPlayers() == true)
											Bukkit.broadcastMessage(Gamemode.getSuccess() + ChatColor.BLUE + player.getName() + ChatColor.GREEN + " has bought " + ChatColor.GOLD + gm.name().toLowerCase().replace("s", "S").replace("c", "C") + " Mode " + ChatColor.GREEN + "for " + eco.getFormat() + "!");
									}else {
										player.sendMessage(Gamemode.getError() + "You do not have enough money to buy " + gm.name().toLowerCase() + " mode!");
										player.sendMessage(Gamemode.getHelp() + "You need " + eco.getFormat());
									}
								}else {
									eco.newAccount();
									player.sendMessage(Gamemode.getError() + "You do not have a account. Account created!");
								}
							}else {
								if(player.getGameMode() == Gamemode.getCreative())
									player.sendMessage(Gamemode.getCreativeError());
								if(player.getGameMode() == Gamemode.getSurvival())
									player.sendMessage(Gamemode.getSurvivalError());
								if(player.getGameMode() == Gamemode.getAdventure())
									player.sendMessage(Gamemode.getAdventureError());
							}
						}
					}else
						p.sendMessage(Gamemode.getNoPermission());
				}else
					p.sendMessage(Gamemode.getError() + "Vault is not enabled on this server!");
			}
		}else
			p.sendMessage(Gamemode.getConsoleError());
		return false;
	}
}
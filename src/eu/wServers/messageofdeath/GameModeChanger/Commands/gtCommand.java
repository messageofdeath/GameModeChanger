package eu.wServers.messageofdeath.GameModeChanger.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class gtCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender player, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gt")) {
			if(args.length > 1) {
				player.sendMessage(Gamemode.getInvalidArgs());
			}
			if(args.length == 0) {
				if(player instanceof Player) {
					Gamemode.setPlayerGamemode((Player) player, null, "toggle");
					player.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + ((Player) player).getGameMode().name().toLowerCase() + " mode!");
				}else
					player.sendMessage(Gamemode.getConsoleError());
			}
			if(args.length == 1) {
				Player targetplayer = player.getServer().getPlayer(args[0]);
				Gamemode.setPlayerGamemode(targetplayer, null, "toggle");
				targetplayer.sendMessage(Gamemode.getSuccess() + "You are now in " + ChatColor.GOLD + targetplayer.getGameMode().name().toLowerCase() + " mode!");
				player.sendMessage(Gamemode.getSuccess() + targetplayer.getName() + " is now in " + ChatColor.GOLD + targetplayer.getGameMode().name().toLowerCase() + " mode!");
			}
		}
		return false;
	}
}

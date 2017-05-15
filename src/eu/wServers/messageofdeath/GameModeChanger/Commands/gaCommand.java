package eu.wServers.messageofdeath.GameModeChanger.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class gaCommand implements CommandExecutor {

	public boolean onCommand(CommandSender player, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ga")) {
			if(args.length > 1) {
				player.sendMessage(Gamemode.getInvalidArgs());
			}
			if(args.length == 0) {
				if(player instanceof Player)
					Gamemode.setPlayerGamemode((Player) player, Gamemode.getAdventure(), false);
				else
					player.sendMessage(Gamemode.getConsoleError());
			}
			if(args.length == 1) {
				Player targetplayer = player.getServer().getPlayer(args[0]);
				Gamemode.setOtherPlayerGamemode(player, targetplayer, Gamemode.getAdventure());
			}
		}
		return false;
	}
}

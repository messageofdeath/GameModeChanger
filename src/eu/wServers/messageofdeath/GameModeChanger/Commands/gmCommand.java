package eu.wServers.messageofdeath.GameModeChanger.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;

public class gmCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gm")) {
			if(args.length > 2 || args.length == 0)
				p.sendMessage(Gamemode.getInvalidArgs());
			if(args.length == 1) {
				String cd = args[0];
				if(cd.equalsIgnoreCase("help") || cd.equalsIgnoreCase("h") || cd.equalsIgnoreCase("1")) {
					if(p instanceof Player)
						Gamemode.getHelp(1, (Player) p);
					else
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
					return false;
				}
				if(cd.equalsIgnoreCase("sign") || cd.equalsIgnoreCase("s") || cd.equalsIgnoreCase("2")) {
					if(p instanceof Player)
						Gamemode.getHelp(2, (Player) p);
					else
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
					return false;
				}
				if(cd.equalsIgnoreCase("playersign") || cd.equalsIgnoreCase("ps") || cd.equalsIgnoreCase("3")) {
					if(p instanceof Player)
						Gamemode.getHelp(3, (Player) p);
					else
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
					return false;
				}
				if(cd.equalsIgnoreCase("getall")) {
					if(p.hasPermission("gamemode.getall"))
						p.sendMessage(Gamemode.getAllPlayersGamemode() != null ? Gamemode.getAllPlayersGamemode() : ChatColor.RED + "No one online!");
					else
						p.sendMessage(Gamemode.getNoPermission());
				}
				if(cd.equalsIgnoreCase("cre") || cd.equalsIgnoreCase("creative") || cd.equalsIgnoreCase("1")) {
					if(p instanceof Player)
						Gamemode.setPlayerGamemode(((Player) p), Gamemode.getCreative(), false);
					else
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
					return false;
				}
				if(cd.equalsIgnoreCase("adv") || cd.equalsIgnoreCase("adventure") || cd.equalsIgnoreCase("2")) {
					if(p instanceof Player)
						Gamemode.setPlayerGamemode(((Player) p), Gamemode.getAdventure(), false);
					else
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
				}
				if(cd.equalsIgnoreCase("sur") || cd.equalsIgnoreCase("survival") || cd.equalsIgnoreCase("0")) {
					if(p instanceof Player)
						Gamemode.setPlayerGamemode(((Player) p), Gamemode.getSurvival(), false);
					else
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
					return false;
				}
				if(cd.equalsIgnoreCase("tog") || cd.equalsIgnoreCase("toggle")) {
					if(!(p instanceof Player)) {
						p.sendMessage(Gamemode.getError() + "This command can only be used in-game");
						return false;
					}
					Player player = (Player) p;
					if(player.hasPermission("gamemode.toggle.change.self")) {
						Gamemode.setPlayerGamemode(player, null, "toggle");
						player.sendMessage(Gamemode.getSuccess() + "You have been changed to " + ChatColor.GOLD + player.getGameMode().name().toLowerCase() + "!");
						return false;
					}else {
						p.sendMessage(Gamemode.getNoPermission());
					}
				}
			}
			if(args.length == 2) {
				String cd = args[0];
				String cd1 = args[1];
				if(cd.equalsIgnoreCase("setall")) {
					if(p.hasPermission("gamemode.setall")) {
						if(cd1.equalsIgnoreCase("cre") || cd1.equalsIgnoreCase("creative") || cd1.equalsIgnoreCase("1")) {
							Gamemode.setAllPlayersGamemode(Gamemode.getCreative());
							p.sendMessage(Gamemode.getSuccess() + "Everyone is now in " + ChatColor.GOLD + "Creative Mode!");
							return false;
						}
						if(cd1.equalsIgnoreCase("sur") || cd1.equalsIgnoreCase("survival") || cd1.equalsIgnoreCase("0")) {
							Gamemode.setAllPlayersGamemode(Gamemode.getSurvival());
							p.sendMessage(Gamemode.getSuccess() + "Everyone is now in " + ChatColor.GOLD + "Survival Mode!");
							return false;
						}
						if(cd1.equalsIgnoreCase("adv") || cd1.equalsIgnoreCase("adventure") || cd1.equalsIgnoreCase("2")) {
							Gamemode.setAllPlayersGamemode(Gamemode.getAdventure());
							p.sendMessage(Gamemode.getSuccess() + "Everyone is now in" + ChatColor.GOLD + " Adventure Mode!");
							return false;
						}else
							p.sendMessage(Gamemode.getError() + "That gamemode does not exist");
					}else
						p.sendMessage(Gamemode.getNoPermission());
				}
				if(cd.equalsIgnoreCase("setdefault")) {
					if(p.hasPermission("gamemode.setdefault")) {
						if(cd1.equalsIgnoreCase("cre") || cd1.equalsIgnoreCase("creative") || cd1.equalsIgnoreCase("1")) {
							Gamemode.setDefaultGamemode(Gamemode.getCreative());
						}
						if(cd1.equalsIgnoreCase("sur") || cd1.equalsIgnoreCase("survival") || cd1.equalsIgnoreCase("0")) {
							Gamemode.setDefaultGamemode(Gamemode.getSurvival());
						}
						if(cd1.equalsIgnoreCase("adv") || cd1.equalsIgnoreCase("adventure") || cd1.equalsIgnoreCase("2")) {
							Gamemode.setDefaultGamemode(Gamemode.getAdventure());
						}
					}else
						p.sendMessage(Gamemode.getNoPermission());
				}
				if(cd.equalsIgnoreCase("tog") || cd.equalsIgnoreCase("toggle")) {
					Player tplayer = Bukkit.getServer().getPlayer(cd1);
					Gamemode.setPlayerGamemode(tplayer, null, "toggle");
				}
				if(cd.equalsIgnoreCase("getdefault")) {
					if(p.hasPermission("gamemode.getdefault"))
						p.sendMessage(Gamemode.getDefaultGamemode());
					else
						p.sendMessage(Gamemode.getNoPermission());
				}
				if(cd.equalsIgnoreCase("adv") || cd.equalsIgnoreCase("adventure") || cd.equalsIgnoreCase("2")) {
					Player tplayer = Bukkit.getServer().getPlayer(cd1);
					Gamemode.setOtherPlayerGamemode(p, tplayer, Gamemode.getAdventure());
					return false;
				}
				if(cd.equalsIgnoreCase("cre") || cd.equalsIgnoreCase("creative") || cd.equalsIgnoreCase("1")) {
					Player tplayer = Bukkit.getServer().getPlayer(cd1);
					Gamemode.setOtherPlayerGamemode(p, tplayer, Gamemode.getCreative());
					return false;
				}
				if(cd.equalsIgnoreCase("sur") || cd.equalsIgnoreCase("survival") || cd.equalsIgnoreCase("0")) {
					Player tplayer = Bukkit.getServer().getPlayer(cd1);
					Gamemode.setOtherPlayerGamemode(p, tplayer, Gamemode.getSurvival());
					return false;
				}
			}
		}
		return false;
	}
}
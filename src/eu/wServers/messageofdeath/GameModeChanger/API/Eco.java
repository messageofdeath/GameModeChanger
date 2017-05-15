package eu.wServers.messageofdeath.GameModeChanger.API;

import org.bukkit.entity.Player;

public class Eco {

	public double amount;
	public Player player;

	public Eco(Player player, double amount) {
		this.player = player;
		this.amount = amount;
	}

	public boolean hasEnough() {
		if(Gamemode.getVault().has(player, amount))
			return true;
		return false;
	}

	public boolean hasAccount() {
		if(Gamemode.getVault().hasAccount(player))
			return true;
		return false;
	}

	public void charge() {
		if(hasEnough() == true)
			Gamemode.getVault().withdrawPlayer(player, amount);
	}

	public String getFormat() {
		return Gamemode.getVault().format(amount);
	}

	public void newAccount() {
		Gamemode.getVault().createPlayerAccount(player);
	}
}

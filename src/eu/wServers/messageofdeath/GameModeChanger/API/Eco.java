package eu.wServers.messageofdeath.GameModeChanger.API;

public class Eco {

	public double amount;
	public String name;

	public Eco(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}

	public boolean hasEnough() {
		if(Gamemode.getVault().has(name, amount))
			return true;
		return false;
	}

	public boolean hasAccount() {
		if(Gamemode.getVault().hasAccount(name))
			return true;
		return false;
	}

	public void charge() {
		if(hasEnough() == true)
			Gamemode.getVault().withdrawPlayer(name, amount);
	}

	public String getFormat() {
		return Gamemode.getVault().format(amount);
	}

	public void newAccount() {
		Gamemode.getVault().createPlayerAccount(name);
	}
}

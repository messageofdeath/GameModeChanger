package eu.wServers.messageofdeath.GameModeChanger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import eu.wServers.messageofdeath.GameModeChanger.API.Gamemode;
import eu.wServers.messageofdeath.GameModeChanger.Commands.gaCommand;
import eu.wServers.messageofdeath.GameModeChanger.Commands.gamemodeCommand;
import eu.wServers.messageofdeath.GameModeChanger.Commands.gcCommand;
import eu.wServers.messageofdeath.GameModeChanger.Commands.gmCommand;
import eu.wServers.messageofdeath.GameModeChanger.Commands.gsCommand;
import eu.wServers.messageofdeath.GameModeChanger.Commands.gtCommand;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.blockListener;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.factionPlayerListener;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.playerInteractEvent;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.playerListener;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.signListener;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.townyPlayerListener;
import eu.wServers.messageofdeath.GameModeChanger.Listeners.worldListener;

public class GameModeChanger extends JavaPlugin {

	public Logger logger = Bukkit.getLogger();
	public String lprefix;
	public String pluginName;
	public String pluginVersion;
	public File file, database;

	@Override
	public void onEnable() {
		d();
		lprefix = "[" + Gamemode.getPluginName() + "] v" + Gamemode.getPluginVersion() + ": ";
		file = new File(getDataFolder(), "config.yml");
		database = new File(getDataFolder(), "database.yml");
		file();
		checkv();
		db = new YamlConfiguration();
		try {
			db.load(database);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		if(Gamemode.getEnabled() == true) {
			if(Gamemode.getVerboseLogging() == true) {
				log("is now enabling...");
				log("registering commands...");
				commands();
				log("registered commands!");
				log("registering listeners...");
				listeners();
				log("registered listeners!");
				log("Checking to see if you are using Factions, Towny, and Vault");
				check();
				log("is now enabled!");
			}else {
				commands();
				listeners();
				log("is now enabled");
			}
		}else {
			log("Shutting down...");
			log("CHECK YOUR CONFIG! Plugin.enabled IS SET TO FALSE!");
			getServer().getPluginManager().disablePlugin(this);
			log("Shut down!");
		}
	}

	@Override
	public void onDisable() {
		log("is now disabled!");
	}

	public void log(String log) {
		logger.info(lprefix + log);
	}

	public void commands() {
		getCommand("gamemode").setExecutor(new gamemodeCommand());
		getCommand("gm").setExecutor(new gmCommand());
		getCommand("gc").setExecutor(new gcCommand());
		getCommand("gs").setExecutor(new gsCommand());
		getCommand("gt").setExecutor(new gtCommand());
		getCommand("ga").setExecutor(new gaCommand());
	}

	public void check() {
		if(Gamemode.useVault() == true) {
			PluginManager pm = Bukkit.getServer().getPluginManager();
			if(pm.getPlugin("Vault").isEnabled()) {
				setupEconomy();
				log("Using Vault!");
			}else {
				log("[ERROR] Vault not found and vault is enabled in the config!");
			}
		}
		if(Gamemode.useFactions() == true) {
			PluginManager pm = Bukkit.getServer().getPluginManager();
			if(pm.getPlugin("Factions").isEnabled()) {
				log("Using Factions!");
			}else {
				log("[ERROR] Factions not found and factions is enabled in the config!");
			}
		}
		if(Gamemode.useTowny() == true) {
			PluginManager pm = Bukkit.getServer().getPluginManager();
			if(pm.getPlugin("Towny").isEnabled()) {
				log("Using Towny!");
			}else {
				log("[ERROR] Towny not found and towny is enabled in the config!");
				return;
			}
		}
		if(Gamemode.useFactions() == false && Gamemode.useTowny() == false && Gamemode.useVault() == false)
			log("Not using any plugin");
	}

	public void listeners() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new blockListener(), this);
		pm.registerEvents(new playerListener(), this);
		pm.registerEvents(new signListener(), this);
		pm.registerEvents(new playerInteractEvent(), this);
		pm.registerEvents(new worldListener(), this);
		if(Gamemode.useFactions() == true) {
			pm.registerEvents(new factionPlayerListener(), this);
		}
		if(Gamemode.useTowny() == true) {
			pm.registerEvents(new townyPlayerListener(), this);
		}
	}

	public void file() {
		if(!this.file.exists()) {
			try {
				this.file.getParentFile().mkdirs();
				this.file.createNewFile();
				copy(getResource("config.yml"), this.file);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(!this.database.exists()) {
			try {
				this.database.getParentFile().mkdirs();
				this.database.createNewFile();
				copy(getResource("database.yml"), this.database);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void checkv() {
		if(getConfig().getDouble("Plugin.version") != 1.4) {
			log("[ERROR] Config file is not up to date! --- Updating...");
			file.delete();
			try {
				file.createNewFile();
			}catch (IOException e) {
				e.printStackTrace();
			}
			copy(getResource("config.yml"), file);
			log("!!!!!!!! Config file is now up-to date !!!!!!!!");
		}
		if(Gamemode.getEnabled() != true && Gamemode.getEnabled() != false) {
			file.delete();
			try {
				file.createNewFile();
				copy(getResource("config.yml"), file);
				d();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Economy economy;

	@SuppressWarnings("rawtypes")
	private Boolean setupEconomy() {
		RegisteredServiceProvider economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if(economyProvider != null) {
			economy = (Economy) economyProvider.getProvider();
		}
		if(economy != null)
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	public static String name;
	public static String version;
	public static FileConfiguration config, db;

	public void d() {
		PluginDescriptionFile pdfFile = getDescription();
		name = pdfFile.getName();
		version = pdfFile.getVersion();
		config = getConfig();
	}
}
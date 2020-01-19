package fr.redcraft.minparc;



import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.redcraft.minparc.Listeners.BlockListener;
import fr.redcraft.minparc.Listeners.EntityListener;
import fr.redcraft.minparc.Listeners.PlayerListener;
import fr.redcraft.minparc.Menu.LandMinParcGUI;
import fr.redcraft.minparc.Menu.LibertySquareLandGUI;
import fr.redcraft.minparc.Menu.MinparcMainInventory;
import fr.redcraft.minparc.Menu.Lands.DiscoverylandGUI;
import fr.redcraft.minparc.Menu.Lands.FrontierlandGUI;
import fr.redcraft.minparc.Menu.Lands.MainstreetGUI;
import fr.redcraft.minparc.Menu.Lands.RubixWorldGUI;
import fr.redcraft.minparc.data.Config;
import fr.redcraft.minparc.data.ConfigurationsManager;
import fr.redcraft.minparc.data.SettingsManager;
import fr.redcraft.minparc.data.SpawnData;
import fr.redcraft.minparc.data.WarpData;
import fr.redcraft.minparc.utils.AutoMessages;
import fr.redcraft.minparc.utils.Warp;

public class Core extends JavaPlugin {
	public static JavaPlugin plugin;
	public static int version = getIntVersion()[0];
	public static int release = getIntVersion()[1];
	public static CommandRegister executor;
	public static String prefix = "§7[§5Min§9Parc§7]§f ";

	
	
	@Override
	public void onEnable() {
		plugin = this;
		loadEvent();
		genereteFolders();
		ConfigurationsManager.setupAllConfig();
		    ConfigurationsManager.saveAllConfig();
		CommandRegister cmdload = new CommandRegister();
		cmdload.loadCommands();
		executor = cmdload;
		new AutoMessages();
		AutoMessages.loadAutoMessages();
		new AutoMessages();
		AutoMessages.sendAutoMessages();
		SettingsManager.getInstance().setup(this);

}
	public static Warp getWarpManager()
	  {
	    return new Warp();
	  }
	@Override
	public void onDisable() {
	}

	public static JavaPlugin getInstance() {
		return plugin;
	}
	
	

	public void loadEvent() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new EntityListener(), this);
		pm.registerEvents(new MinparcMainInventory(), this);
		pm.registerEvents(new LandMinParcGUI(), this);
		pm.registerEvents(new LibertySquareLandGUI(), this);
		pm.registerEvents(new FrontierlandGUI(), this);
		pm.registerEvents(new MainstreetGUI(), this);
		pm.registerEvents(new DiscoverylandGUI(), this);
		pm.registerEvents(new RubixWorldGUI(), this);
	}
	
	public static int[] getIntVersion() {
		int version = Integer.valueOf(Bukkit.getServer().getClass().getName().split("\\.")[3].split("_")[1]);
		int release = Integer.valueOf(Bukkit.getServer().getClass().getName().split("\\.")[3].split("R")[1]);
		int[] v = { version, release };
		return v;
	}

	public static void genereteFolders() {


		SpawnData spawn = new SpawnData();
		WarpData warp = new WarpData();
		spawn.onCreate();
		warp.onCreate();
		Config.folders();

	}
	


}

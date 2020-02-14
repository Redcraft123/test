package fr.redcraft.minparc.shop;

import java.io.File;
import java.io.IOException;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class PlayerConfig {
	
	public static File PlayerConfigFile = new File("plugins/MinParcCore", "player.yml");
	public static FileConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(PlayerConfigFile);
	
	public static void save() throws IOException {
		PlayerConfig.save(PlayerConfigFile);
	}

	
	public static void setMute(Player p, Boolean muted) throws IOException{
		PlayerConfig.set(p.getName()+ ".muted", muted);
		save();
	}
	public static Boolean getMute(Player p) {
		return PlayerConfig.getBoolean(p.getName()+ ".muted");
	}
	
	public static void setAFK(Player p, Boolean muted) throws IOException{
		PlayerConfig.set(p.getName()+ ".AFK", muted);
		save();
	}
	public static Boolean getAFK(Player p) {
		return PlayerConfig.getBoolean(p.getName()+ ".AFK");
	}	
	
	public static void setBuilder(Player p, Boolean builder) throws IOException{
		PlayerConfig.set(p.getName()+ ".builder", builder);
		save();
	}	
	public static Boolean getBuilder(Player p) {
		return PlayerConfig.getBoolean(p.getName()+ ".builder");
	}
	
	//Cars---
	public static void setCar(Player p, Integer damage) throws IOException{
		PlayerConfig.set(p.getName()+ ".car", damage);
		save();
	}	
	public static Integer getCar(Player p) {
		return PlayerConfig.getInt(p.getName()+ ".car");
	}
	
	//Hotels---
	public static void setInHotel(Player p, Integer inhotel) throws IOException{
		PlayerConfig.set(p.getName()+ ".inhotel", inhotel);
		save();
	}	
	public static Integer getInHotel(Player p) {
		return PlayerConfig.getInt(p.getName()+ ".inhotel");
	}
	public static void setRentedHotel(Player p, Integer builder) throws IOException{
		PlayerConfig.set(p.getName()+ ".rentedhotel", builder);
		save();
	}	
	public static int getRentedHotel(Player p) {
		return PlayerConfig.getInt(p.getName()+ ".rentedhotel");
	}
	
	//Resourcepack---
	public static void Resourcepackactive(Player p, Boolean active) throws IOException{
		PlayerConfig.set(p.getName()+ ".resourcepack", active);
		save();
	}	
	public static Boolean getResourcepack(Player p) {
		return PlayerConfig.getBoolean(p.getName()+ ".resourcepack");
	}
	
	//Current Inventory
	public static void CurrentInventory(Player p, String curInv) throws IOException{
		PlayerConfig.set(p.getName()+ ".curInv", curInv);
		save();
	}	
	public static String getCurrentInventory(Player p) {
		return PlayerConfig.getString(p.getName()+ ".curInv");
	}
	public static void setCurrentInventory(Player p, String curInv) throws IOException{
		PlayerConfig.set(p.getName()+ ".curInv", curInv);
		save();
	}	
	
	//Runtime
	public static void Runtime(Player p, int time) throws IOException{
		PlayerConfig.set(p.getName()+ ".runtime", time);
		save();
	}	
	public static int getRuntime(Player p) {
		return PlayerConfig.getInt(p.getName()+ ".runtime");
	}
	
	//Achievements---
	

	public static void giveItemInv(Player p, ShopEnums shop) {
		if(!hasItemInv(p, shop)) {
			PlayerConfig.set(p.getName() + ".inventory." + shop.getName(), true);
			try {
				PlayerConfig.save(PlayerConfigFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public static boolean hasItemInv(Player p, ShopEnums shop) {
		if (PlayerConfig.get(p.getName() + ".inventory." + shop.getName()) != null )
			return true;
		else
			return false;
	}
}

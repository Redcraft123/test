package fr.redcraft.minparc.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.utils.LocationUtils;


public class WarpData extends Config {

	public WarpData() {
		super(new File(Core.getInstance().getDataFolder() + "/warps.yml"));
	}

	public void onCreate() {
		File file = new File(Core.getInstance().getDataFolder() + "/warps.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				super.get().createSection("Warps");
				super.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setWarp(String name, Location loc) {
		super.get().set("Warps." + name + ".x", loc.getX());
		super.get().set("Warps." + name + ".y", loc.getY());
		super.get().set("Warps." + name + ".z", loc.getZ());
		super.get().set("Warps." + name + ".yaw", loc.getYaw());
		super.get().set("Warps." + name + ".pitch", loc.getPitch());
		super.get().set("Warps." + name + ".world", loc.getWorld().getName());
		super.save();
	}

	public Location getWarp(String name) {
		double x = Double.parseDouble(super.getString("Warps." + name + ".x"));
		double y = Double.parseDouble(super.getString("Warps." + name + ".y"));
		double z = Double.parseDouble(super.getString("Warps." + name + ".z"));
		float yaw = Float.parseFloat(super.getString("Warps." + name + ".yaw"));
		float pitch = Float.parseFloat(super.getString("Warps." + name + ".pitch"));
		World world = LocationUtils.findWorld(super.getString("Warps." + name + ".world"));
		return new Location(world, x, y, z, yaw, pitch);
	}

}
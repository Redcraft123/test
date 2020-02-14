package fr.redcraft.minparc.utils;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.data.Data;


public class AFK {

	public static HashMap<String, Location> hashAfk = new HashMap<String, Location>();
	public static HashSet<String> afk = new HashSet<String>();
	public static HashMap<String, Location> hashFreeze = new HashMap<String, Location>();

	private Player p;

	public AFK(Player p) {
		this.p = p;
	}

	public void autoAfk(Location loc) {
		hashAfk.put(this.p.getName(), loc);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.plugin, new Runnable() {
				@Override
				public void run() {
					if (p == null) {
						if (hashAfk.containsKey(p.getName()))
							hashAfk.remove(p.getName());
						return;
					}
					if (isAfk()) {
						if (hashAfk.containsKey(p.getName()))
							hashAfk.remove(p.getName());
						return;
					}
					if (((int) p.getLocation().getX()) == ((int) loc.getX())
							&& ((int) p.getLocation().getY()) == ((int) loc.getY())
							&& ((int) p.getLocation().getZ()) == ((int) loc.getZ())
							&& p.getLocation().getWorld().getName().equals(loc.getWorld().getName())) {
						Bukkit.broadcastMessage("CC");

						setAfk();
					} else {
						if (hashAfk.containsKey(p.getName())) {
							hashAfk.remove(p.getName());
						}
					}
				}
			}, 3 * 20);
		}
	

	public boolean isAway() {
		return this.p.getLocation().equals(hashAfk.get(this.p.getName()));
	}

	public void setAfk() {
		if (isAfk())
			return;
		afk.add(this.p.getName());
		if (hashAfk.containsKey(this.p.getName())) {
			hashAfk.remove(this.p.getName());
		}
		Data config = new Data();
		if (config.freezeAfk()) {
			hashFreeze.put(this.p.getName(), this.p.getLocation());
		}
		if (config.autoKick()) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.plugin, new Runnable() {
				@Override
				public void run() {
					if (p != null && isAfk()) {
						if (p.hasPermission("mp.apk"))
							return;
						afk.remove(p.getName());
					}
				}
			}, config.autoKickTime() * 20);
		}
	}

	public void freeze() {
		if (hashFreeze.containsKey(this.p.getName())) {
			this.p.teleport(hashFreeze.get(this.p.getName()));
		}
	}

	public void removeAfk() {
		if (hashAfk.containsKey(this.p.getName())) {
			hashAfk.remove(this.p.getName());
		}
		if (hashFreeze.containsKey(this.p.getName())) {
			hashFreeze.remove(this.p.getName());
		}
		if (afk.contains(this.p.getName())) {
			afk.remove(this.p.getName());
		}
	}

	public boolean isAfk() {
		return afk.contains(this.p.getName());
	}

}
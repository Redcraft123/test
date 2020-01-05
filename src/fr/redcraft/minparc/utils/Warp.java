package fr.redcraft.minparc.utils;


import java.io.File;
import java.io.IOException;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;

public class Warp
{
  public File f = new File(Core.getInstance().getDataFolder(), "warp.yml");
  public FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.f);
  
  private void save()
  {
    try
    {
      this.cfg.save(this.f);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public void createWarp(Player player, String warpName)
  {
    Location playerLoc = player.getLocation();
    if (!isWarpExists(warpName))
    {
      this.cfg.set(warpName, playerLoc);
      save();
      player.sendMessage(Core.prefix + " §6Le warp §e" + warpName + " §6Vient d'être crée");
    }
    else
    {
    	player.sendMessage(Core.prefix + "§6Le warp §e" + warpName + " §6existe déjà");
    }
  }
  
  public void removeWarp(String warpName)
  {
    this.cfg.set(warpName, null);
    save();
  }
  
  public boolean isWarpExists(String warpName)
  {
    return this.cfg.contains(warpName);
  }
  
  public Location getWarpLocation(String warpName)
  {
    return (Location)this.cfg.get(warpName);
  }
  
  public void teleportToWarp(Player p, String warpName)
  {
    p.teleport(getWarpLocation(warpName));
  }
}

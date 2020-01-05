package fr.redcraft.minparc.data;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import fr.redcraft.minparc.Core;

public class ConfigUtils
{
  static ConfigUtils instance = new ConfigUtils();
  Plugin p;
  FileConfiguration config;
  File cfile;
  FileConfiguration scoreboard;
  File sfile;
  
  public static ConfigUtils getInstance()
  {
    return instance;
  }
  
  
  public void setup2(Plugin p)
  {
    this.sfile = new File(Core.getInstance().getDataFolder(), "automessages.yml");
    if (!this.sfile.exists()) {
      try
      {
        this.sfile.createNewFile();
      }
      catch (IOException e)
      {
        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create automessages.yml file!");
      }
    }
    this.scoreboard = YamlConfiguration.loadConfiguration(this.sfile);
  }
  
  public FileConfiguration getScoreboard()
  {
    return this.scoreboard;
  }
  
  public void saveAutomessages()
  {
    try
    {
      this.scoreboard.save(this.sfile);
    }
    catch (IOException e)
    {
      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save automessages.yml file!");
    }
  }
  
  public void reloadAutomessages()
  {
    this.scoreboard = YamlConfiguration.loadConfiguration(this.sfile);
  }
  
  public void setup(Plugin p)
  {
    this.cfile = new File(Core.getInstance().getDataFolder(), "config.yml");
    if (!this.cfile.exists()) {
      try
      {
        this.cfile.createNewFile();
      }
      catch (IOException e)
      {
        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml file!");
      }
    }
    this.config = YamlConfiguration.loadConfiguration(this.cfile);
  }
  
  public FileConfiguration getConfig()
  {
    return this.config;
  }
  
  public void saveData()
  {
    try
    {
      this.config.save(this.cfile);
    }
    catch (IOException e)
    {
      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml file!");
    }
  }
  
  public void reloadData()
  {
    this.config = YamlConfiguration.loadConfiguration(this.cfile);
  }
  
 
}

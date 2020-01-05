package fr.redcraft.minparc.utils;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.data.AutoMessagesData;
import fr.redcraft.minparc.data.ConfigurationsManager;

public class AutoMessages
{
  private static Configuration config = ConfigurationsManager.getMessagesConfig() ;
  private static ArrayList<String> msg = new ArrayList<String>();
  private static BukkitTask task;
  private static boolean active = false;
  private static int i = 0;
  public static HashMap<Player, Integer> edition = new HashMap<Player, Integer>();
  
  public static void sendAutoMessages()
  {
    int delay = config.getInt("Messages.delai");
    if (!active)
    {
      active = true;
      
      task = new BukkitRunnable()
      {
        public void run()
        {
          AutoMessages.config = ConfigurationsManager.getMessagesConfig();
          if (AutoMessages.i >= AutoMessagesData.autoMessages.size()) {
            AutoMessages.i = 0;
          }
          if (!AutoMessagesData.autoMessages.isEmpty())
          {
            AutoMessagesData m = getEnabledAutoMessageData();
            if (m == null)
            {
              if (AutoMessages.i >= AutoMessagesData.autoMessages.size()) {
                AutoMessages.i = 0;
              }
              m = getEnabledAutoMessageData();
            }
          }
          AutoMessages.i += 1;
        }
        
        private AutoMessagesData getEnabledAutoMessageData()
        {
          AutoMessagesData m = (AutoMessagesData)AutoMessagesData.autoMessages.get(AutoMessages.i);
          if (m != null)
          {
            if (m.isEnable())
            {
              AutoMessages.sendMessage(m.getMessage().replace("&", "§").replace("\\n", "\n"));
              return m;
            }
            AutoMessages.i += 1;
            return null;
          }
          return null;
        }
      }.runTaskTimer(Core.getInstance(), 0L, 20L * delay);
    }
    else
    {
      reLoadMessages();
      active = false;
      return;
    }
  }
  
  private static void reLoadMessages()
  {
    task.cancel();
    i = 0;
  }
  
  private static void sendMessage(String message)
  {
    Bukkit.broadcastMessage(message);
  }
  
  public static void addAutoMessage(String message)
  {
    int nbr = 0;
    if (config.contains("Messages")) {
      for (String keys : config.getConfigurationSection("Messages").getKeys(false)) {
        if ((!keys.startsWith("delai")) && (!keys.startsWith("enable"))) {
          nbr = Integer.parseInt(keys) + 1;
        }
      }
    }
    new AutoMessagesData(nbr, message, true);
    
    config.set("Messages." + String.valueOf(nbr) + ".texte", message);
    config.set("Messages." + String.valueOf(nbr) + ".enable", Boolean.valueOf(true));
    ConfigurationsManager.saveAllConfig();
  }
  
  public static void delAutoMessage(int msgNum)
  {
    if (!config.contains("Messages")) {
      return;
    }
    AutoMessagesData m = getAutoMessageData(msgNum);
    if (m != null) {
      AutoMessagesData.autoMessages.remove(m);
    }
    config.set("Messages." + msgNum, null);
    ConfigurationsManager.saveAllConfig();
  }
  
  public static void setDelay(int delay)
  {
    if (config.getConfigurationSection("Messages") == null) {
      return;
    }
    config.set("Messages.delai", Integer.valueOf(delay));
    ConfigurationsManager.saveAllConfig();
  }
  
  public static void setEnable(boolean b)
  {
    if (config.getConfigurationSection("Messages") == null) {
      return;
    }
    config.set("Messages.enable", Boolean.valueOf(b));
    ConfigurationsManager.saveAllConfig();
  }
  
  public static void setEnableMessage(int msgNum, boolean b)
  {
    if (config.getConfigurationSection("Messages") == null) {
      return;
    }
    if (!config.contains("Messages." + msgNum)) {
      return;
    }
    getAutoMessageData(msgNum).setEnable(b);
    config.set("Messages." + msgNum + ".enable", Boolean.valueOf(b));
    ConfigurationsManager.saveAllConfig();
  }
  
  private static AutoMessagesData getAutoMessageData(int msgNum)
  {
    for (AutoMessagesData m : AutoMessagesData.autoMessages) {
      if (m.getMessageNum() == msgNum) {
        return m;
      }
    }
    return null;
  }
  
  public static void editMessageText(Player p, int msgNum)
  {
    edition.put(p, Integer.valueOf(msgNum));
    p.sendMessage(Core.prefix + "16Veuillez entrer un nouveau texte de l'auto-message dans le chat");
  }
  
  public static void editMessageText(Player p, int msgNum, String text)
  {
    if (config.getConfigurationSection("Messages") == null) {
      return;
    }
    if (!config.contains("Messages." + msgNum))
    {
    	p.sendMessage(Core.prefix + "§6L'auto-message §e" + msgNum + " §6n'existe pas");
      return;
    }
    config.set("Messages." + msgNum + ".texte", text);
    ConfigurationsManager.saveAllConfig();
    p.sendMessage(Core.prefix + "§6Vous avez modifié le text §e" + msgNum + " §6avec succès");
  }
  
  public static void loadAutoMessages()
  {
    AutoMessagesData.autoMessages.clear();
    msg.clear();
    
    ConfigurationsManager.setupAllConfig();
    ConfigurationsManager.saveAllConfig();
    if (config.getConfigurationSection("Messages") == null) {
      return;
    }
    for (String keys : config.getConfigurationSection("Messages").getKeys(false)) {
      if ((!keys.startsWith("delai")) && (!keys.startsWith("enable")))
      {
        String path = "Messages." + keys;
        new AutoMessagesData(Integer.parseInt(keys), config.getString(path + ".texte"), config.getBoolean(path + ".enable"));
      }
    }
    for (AutoMessagesData message : AutoMessagesData.autoMessages) {
      System.out.println("AutoMessage charge : " + message.getMessageNum() + ": " + message.getMessage() + " -> enable: " + config.getBoolean(new StringBuilder("Messages.").append(message.getMessageNum()).append(".enable").toString()));
    }
  }
  
  public static void getMessagesList(Player p)
  {
    if (config.getConfigurationSection("Messages") == null) {
      return;
    }
    p.sendMessage("");
    p.sendMessage("§7§l§m                                                               ");
    int delay = config.getInt("Messages.delai");
    p.sendMessage("      §6§nAuto-Messages§r      §cdelai: " + delay + "s");
    for (String keys : config.getConfigurationSection("Messages").getKeys(false)) {
      if ((!keys.equalsIgnoreCase("delai")) && (!keys.equalsIgnoreCase("enable"))) {
        p.sendMessage(" - §e" + keys + "§f >>  §a "  + config.getString(new StringBuilder("Messages.").append(keys).append(".texte").toString()).replace("&", ChatColor.translateAlternateColorCodes('&', "§")));
      }
    }
    p.sendMessage("§7§l§m                                                               ");
  }
  
}
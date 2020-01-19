package fr.redcraft.minparc.Attraction;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.data.SettingsManager;

public class Attraction
{
 
  
  public static void addAttraction(String attractie, CommandSender sender)
  {
     List<String> attracties = SettingsManager.getInstance().getData().getStringList("attractions");
    if (!attracties.contains(attractie))
    {
      attracties.add(attractie);
      SettingsManager.getInstance().getData().set("attractions", attracties);
      
      SettingsManager.getInstance().getData().set("attraction." + attractie + ".status", "closed");
      
      SettingsManager.getInstance().saveData();
      
      sender.sendMessage(Core.prefix + "§6Attraction §e" + attractie + "§6Crée");
    }
    else
    {
      sender.sendMessage("§cL'attraction existe déjà");
    }
  }
  
  public static void toggleAttractionStatus(String attractie, String status, CommandSender sender)
  {
    List<String> attracties = SettingsManager.getInstance().getData().getStringList("attractions");
    if (attracties.contains(attractie))
    {
      if ((status.equalsIgnoreCase("open")) || (status.equalsIgnoreCase("maintenance")) || (status.equalsIgnoreCase("closed")))
      {
        SettingsManager.getInstance().getData().set("attraction." + attractie + ".status", status);
        SettingsManager.getInstance().saveData();
        
        sender.sendMessage(Core.prefix + "§6Le status de l'attraction §e" + attractie + "§6est désormait §e" + status);
        if (SettingsManager.getInstance().getConfig().getBoolean("broadcast")) {
          if (status.equalsIgnoreCase("open")) {
        	  
        	  Bukkit.broadcastMessage("§a§lL'attraction §9§l" + attractie +" §a§lest maintenant ouverte");
          } else if (status.equalsIgnoreCase("maintenance")) {
        	  Bukkit.broadcastMessage("§a§lL'attraction §9§l" + attractie +" §a§lest maintenant en maintenance");
          } else if (status.equalsIgnoreCase("closed")) {
	          Bukkit.broadcastMessage("§a§lL'attraction §9§l" + attractie +" §a§lest maintenant Fermé");
          }
        }
      }
      else
      {
    	  sender.sendMessage("Le status est incorrect");
      }
    }
    else {
        sender.sendMessage("§cL'attraction n'existe pas");
    }
  }
  
  public static void setAttractionSpawn(String attractie, Player player)
  {
    List<String> attracties = SettingsManager.getInstance().getData().getStringList("attractions");
    if (attracties.contains(attractie))
    {
      SettingsManager.getInstance().getData().set("attraction." + attractie + ".location", player.getLocation());
      
      SettingsManager.getInstance().saveData();
      
      player.sendMessage(Core.prefix + "Vous venez de changer la localisation de " + attractie);
    }
    else
    {
        player.sendMessage("§cL'attraction n'existe pas");
    }
  }
  
  public static void deleteAttraction(String attractie, CommandSender sender)
  {
    List<String> attracties = SettingsManager.getInstance().getData().getStringList("attractions");
    if (attracties.contains(attractie))
    {
      attracties.remove(attractie);
      SettingsManager.getInstance().getData().set("attractions", attracties);
      
      SettingsManager.getInstance().getData().set("attraction." + attractie, null);
      
      SettingsManager.getInstance().saveData();
      
      sender.sendMessage("§4Vous venez de supprimer l'attraction " + attractie);
    }
    else
    {
        sender.sendMessage("§cL'attraction n'existe pas");
    }
  }
  
  public static void checkAttraction(String attractie, CommandSender sender)
  {
    List<String> attracties = SettingsManager.getInstance().getData().getStringList("attractions");
    if (attracties.contains(attractie))
    {
      String status = SettingsManager.getInstance().getData().getString("attraction." + attractie + ".status");
      if (status.equalsIgnoreCase("open")) {
    	  sender.sendMessage(Core.prefix + "l'attraction " + attractie + " est Ouverte !");
      }
      if (status.equalsIgnoreCase("maintenance")) {
    	  sender.sendMessage(Core.prefix + "l'attraction " + attractie + " est en maintenance !");
      }
      if (status.equalsIgnoreCase("closed")) {
    	  sender.sendMessage(Core.prefix + "l'attraction " + attractie + " est Fermer !");
      }
    }
    else
    {
        sender.sendMessage("§cL'attraction n'existe pas");
    }
  }
  
  public static void teleportToAttraction(Player player, String attractie)
  {
    List<String> attracties = SettingsManager.getInstance().getData().getStringList("attractions");
    if (attracties.contains(attractie))
    {
      String status = SettingsManager.getInstance().getData().getString("attraction." + attractie + ".status");
      
      Location location = (Location)SettingsManager.getInstance().getData().get("attraction." + attractie + ".location");
      if (location != null)
      {
        if (status.equalsIgnoreCase("open"))
        {
          player.teleport(location);
          player.sendMessage("Téléportation...");
        }
        if (status.equalsIgnoreCase("closed")) {
          if (player.hasPermission("am.teleport.closed"))
          {
            player.teleport(location);
            player.sendMessage("Téléportation...");
          }
          else
          {
        	  player.sendMessage("L'attraction est fermé");
          }
        }
        if (status.equalsIgnoreCase("maintenance")) {
          if (player.hasPermission("am.teleport.maintenance"))
          {
            player.teleport(location);
            player.sendMessage("Téléportation...");
          }
          else
          {
        	  player.sendMessage("L'attraction est en Maintenance");
          }
        }
      }
      else
      {
    	  player.sendMessage("Aucun point de location n'est définis pour cette attraction");
      }
    }
    else
    {
        player.sendMessage("§cL'attraction n'existe pas");
    }
  }
}

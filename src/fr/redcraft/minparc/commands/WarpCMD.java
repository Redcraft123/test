package fr.redcraft.minparc.commands;

import java.io.File;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;



public class WarpCMD implements CommandExecutor {

	public static String[] getCommands() {
		String[] array = { "warp", "delwarp", "setwarp", "warps" };
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {


		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
		

			if (cmd.getName().equalsIgnoreCase("setwarp")) {
				if (sender.hasPermission("main.setwarp")) {
				      if (args.length == 1) {
				          Core.getWarpManager().createWarp((Player) sender, args[0]);
				        } else {
				         sender.sendMessage("§6Utilse §e/setwarp nom ");
				        }
				      }
				      else {
							sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				      }
				      return false;
				    }
				  }

	

		if (cmd.getName().equalsIgnoreCase("delwarp")) {
				if (sender.hasPermission("main.delwarp")) {
					if (args.length == 1)
				      {
				        String warpName = args[0];
				        if (Core.getWarpManager().isWarpExists(warpName))
				        {
				          Core.getWarpManager().removeWarp(warpName);
				          sender.sendMessage(Core.prefix + "§6Le warp§e " + warpName + "§6 viens d'être suprimmé");
				        }
				        else
				        {
				        	
				        	sender.sendMessage(Core.prefix + "§6Le warp §e" + warpName + " §6N'existe pas");
				        }
				      }
				      else
				      {
					         sender.sendMessage("§6Utilse §e/delwarp nom ");
				      }
				    }
				    else {
						sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				    }
		}

			if (cmd.getName().equalsIgnoreCase("warp")) {
				if (sender.hasPermission("main.warp")) {
					 Player player = (Player) sender;

					if (args.length == 1)
				      {
						 
				        String warpName = args[0];
				        if (Core.getWarpManager().isWarpExists(warpName))
				        {
				          Core.getWarpManager().teleportToWarp((Player) sender, args[0]);
				          sender.sendMessage("§6Téléportation...");
				          player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 30, 30);
				          
				        }
				        else
				        {
				        	sender.sendMessage(Core.prefix + "§6Le warp §e" + warpName + "§6N'existe pas");
				        }
				      }
				      if (args.length == 0)
				      {
				        File f = new File(Core.getInstance().getDataFolder(), "warp.yml");
				        FileConfiguration config = YamlConfiguration.loadConfiguration(f);
				        String warps = "";
				        for (String s : config.getKeys(false)) {
				          warps = warps + s + ", ";
				        }
				        sender.sendMessage("§6Liste des Warps §f: §e" + warps);
				      }
				    }
				    else
				    {
						sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				    }
				    return false;
				  }
			return false;
	}
	
	}
	


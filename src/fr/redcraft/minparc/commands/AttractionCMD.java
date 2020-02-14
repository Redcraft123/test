package fr.redcraft.minparc.commands;








import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import fr.redcraft.minparc.Attraction.AttractionEnums;
import fr.redcraft.minparc.data.SettingsManager;










public class AttractionCMD
implements CommandExecutor

{


	public static String[] getCommands() {
		String[] array = {"attraction"};
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		{
			if(!sender.hasPermission("minparc.admin")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

			}

			if ((sender instanceof Player))
			{
				if (cmd.getName().equalsIgnoreCase("attraction"))


					if(args.length == 2) {

						if (args[0].equalsIgnoreCase("on")) 
						{
							if(AttractionEnums.getAttraByID(args[1]) == null) {						
								sender.sendMessage("§cLe nom de l'attraction est incorrect ou n'existe pas.");
								return false;
							}
							SettingsManager.getInstance().getData().set("access." + AttractionEnums.getAttraByName(args[1]), 0);
							SettingsManager.getInstance().saveData();
							SettingsManager.getInstance().reloadData();

							Bukkit.broadcastMessage("§a§l" + args[1] + "§a est désormait Ouvert");
						}
					}

				if (args[0].equalsIgnoreCase("off")) 
				{
					if(AttractionEnums.getAttraByID(args[1]) == null) {						
						sender.sendMessage("§cLe nom de l'attraction est incorrect ou n'existe pas.");
						return false;
					}		
					SettingsManager.getInstance().getData().set("access." + AttractionEnums.getAttraByName(args[1]), 1);
					SettingsManager.getInstance().saveData();
					SettingsManager.getInstance().reloadData();

					Bukkit.broadcastMessage("§4§l" + args[1] + "§4 est désormait Fermé");


				}

				if (args[0].equalsIgnoreCase("maintenance")) 
				{
					if(AttractionEnums.getAttraByID(args[1]) == null) {						
						sender.sendMessage("§cLe nom de l'attraction est incorrect ou n'existe pas.");
						return false;
					}				
					SettingsManager.getInstance().getData().set("access." + AttractionEnums.getAttraByName(args[1]), 2);
					SettingsManager.getInstance().saveData();
					SettingsManager.getInstance().reloadData();

					Bukkit.broadcastMessage("§6§l" + args[1] + "§6 est désormait en maintenance");
				}

				if (args[0].equalsIgnoreCase("list")) 
				{


					for(AttractionEnums attraction : AttractionEnums.values()) {
						sender.sendMessage("§f- §6" + attraction.getName());
						SettingsManager.getInstance().getData().set(attraction.getName(), 1);
						SettingsManager.getInstance().saveData();

					}
				}

				return false;
			}
		}
		return false;
	}
}




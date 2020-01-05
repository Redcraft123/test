  
package fr.redcraft.minparc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.functions.User;
import fr.redcraft.minparc.utils.Time;

;

public class TimeCMD implements CommandExecutor {

	public static String[] getCommands() {
		String[] array = { "day", "night", "ptime"};
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {

		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("day")) {
				if (sender.hasPermission("MinParc.Day")) {
					Time.setDay();
					sender.sendMessage(Core.prefix + "§6Vous veneu de mettre le jour dans le monde §e" + player.getWorld().getName());
					return true;
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("night")) {
				if (sender.hasPermission("MinParc.Night")) {
					Time.setNight();
					sender.sendMessage(Core.prefix + "§6Vous veneu de mettre la nuit dans le monde §e" + player.getWorld().getName());
					return true;
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}


			if (cmd.getName().equalsIgnoreCase("ptime")) {
				if (sender.hasPermission("MinParc.Ptime")) {
					if (args.length == 1) {
						if (!(sender instanceof Player))
							return true;
						User user = new User(sender.getName());
						switch (args[0]) {
						case "day":
							user.setDay();
							sender.sendMessage(Core.prefix + "§6Vous venez de vous mettre §ele jour");
							break;
						case "night":
							user.setNight();
							sender.sendMessage(Core.prefix + "§6Vous venez de vous mettre §ela nuit");
							break;

						default:
							user.sendMessage("§6Utilise /Ptime day-night");
							break;

						}
					}
				}
		


			}

		}
		return false;
	}

}
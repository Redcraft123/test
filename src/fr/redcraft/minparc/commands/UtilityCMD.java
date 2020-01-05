
package fr.redcraft.minparc.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.functions.User;
import net.md_5.bungee.api.ChatColor;

;

public class UtilityCMD implements CommandExecutor {

	public static String[] getCommands() {
		String[] array = { "speed", "fly", "top","craft","nick","ci"};
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {

		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
			if (cmd.getName().equalsIgnoreCase("fly")) {
				if (!sender.hasPermission("MinParc.Fly")) {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

					return false;
				}
				Player player = (Player) sender;
				User user = new User(player.getName());


				if (!user.hasFly()) {

					user.setFly(true);
					sender.sendMessage(Core.prefix + "§6Activation du Fly");
					return true;
				} else {
					user.setFly(false);
					sender.sendMessage(Core.prefix + "§6Désactivation du Fly");
				}
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("speed")) {
			if (!sender.hasPermission("MinParc.Fly")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

				return false;
			}				if (args.length == 1) {
					Player player = (Player) sender;

					User user = new User(player.getName());

					if((Integer.valueOf(args[0]) > 10) || Integer.valueOf(args[0]) < 0) {
						sender.sendMessage(Core.prefix + "§6Utiliser /speed 0-10");
						return false;
					}
					user.setWalkSpeed(Integer.valueOf(args[0]));
					sender.sendMessage(Core.prefix + "§6Vous venez de définir votre vitesse à §e" + Integer.valueOf(args[0]));
					return true;
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			
		}

		if (cmd.getName().equalsIgnoreCase("craft")) {
			if (!sender.hasPermission("MinParc.Fly")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

				return false;
			}					
			if (!(sender instanceof Player))
				return true;
			User user = new User(sender.getName());
			user.openCraft();
		
		}
		
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (!sender.hasPermission("MinParc.Fly")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}							
			if (!(sender instanceof Player))
				return true;
			User user = new User(sender.getName());
			user.getPlayer().getInventory().clear();
			sender.sendMessage(Core.prefix + "§6 Vous veneu de clear votre inventaire");
		}



	if (cmd.getName().equalsIgnoreCase("top")) {
		if (!sender.hasPermission("MinParc.Fly")) {
			sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

			return false;
		}							
		Player player = (Player) sender;

		player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation());
		sender.sendMessage("§6Téléportation...");
		return true;

}

	if (cmd.getName().equalsIgnoreCase("nick")) {
		if ((sender instanceof Player))
		{
			Player player = (Player)sender;
			if ((player.hasPermission("MinParc.Nickname")))
			{
				if ((args.length == 0) || (args.length == 1))
				{
					//Messages.sendMessage(player, "�c/nick <player> <name>");
				}
				else if (args.length == 2)
				{
					String name = args[0];

					Player target = Bukkit.getServer().getPlayer(name);
					String newName = ChatColor.translateAlternateColorCodes('&', args[1]);

					player.sendMessage(Core.prefix + "§6Vous venez de vous rennomer en §e" + newName);
					if (player != target) {
						player.sendMessage(Core.prefix + "§6Vous venez de rennomer§e " + target.getName() + " en §e" + newName);
					}

					target.setDisplayName(newName);
					target.setCustomName(newName);
				}
			}
		}

	


	}
	return false;
	}
}

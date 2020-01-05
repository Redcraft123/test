package fr.redcraft.minparc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.data.SpawnData;



public class SpawnCMD implements CommandExecutor {

	public static String[] getCommands() {
		String[] array = {"setspawn", "spawn"};
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {


		if (sender instanceof Player) {

			if (cmd.getName().equalsIgnoreCase("setspawn")) {
				if (sender.hasPermission("main.setspawn")) {
					SpawnData spawn = new SpawnData();
					spawn.setSpawn(((Player) sender).getLocation());
					sender.sendMessage("setspawn");
					return true;
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}
			if (cmd.getName().equalsIgnoreCase("spawn")) {
				SpawnData spawn = new SpawnData();
				((Player) sender).teleport(spawn.getSpawn());
				sender.sendMessage("§6Téléporation...");
				return true;


			}
		}
		return false;
	}
}
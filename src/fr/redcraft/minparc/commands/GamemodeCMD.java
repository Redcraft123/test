package fr.redcraft.minparc.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.functions.User;



public class GamemodeCMD implements CommandExecutor {
	
	public static String[] getCommands() {
		String[] array = {"gm", "gmc", "gma", "gms","gmsp"};
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {


		if (sender instanceof Player || sender instanceof ConsoleCommandSender) {

			if (cmd.getName().equalsIgnoreCase("gmc")) {
				if (sender.hasPermission("MinParc.Gamemode")) {
					if (args.length == 1) {
						if (sender.hasPermission("MinParc.Gamemode")) {
							User user = new User(args[0]);
							if (user.isOnline()) {
								user.setGamemode(GameMode.CREATIVE);
								user.sendMessage(Core.prefix + "§7§lChangegfgfgfgment de mode en §6§lCréative");
								return true;
							} else {
								sender.sendMessage("§cLe joueur n'existe pas");
								return true;
							}
						} else {
							sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
							return true;
						}
					} else {
						if (!(sender instanceof Player))
							return true;
						User user = new User(sender.getName());
						user.setGamemode(GameMode.CREATIVE);
						user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lCréative");
						return true;
					}
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("gms")) {
				if (sender.hasPermission("MinParc.Gamemode")) {
					if (args.length == 1) {
						if (sender.hasPermission("MinParc.Gamemode")) {
							User user = new User(args[0]);
							if (user.isOnline()) {
								user.setGamemode(GameMode.SURVIVAL);
								user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lSurvie");
								return true;
							} else {
								sender.sendMessage("§cLe joueur n'existe pas");
								return true;
							}
						} else {
							sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
							return true;
						}
					} else {
						if (!(sender instanceof Player))
							return true;
						User user = new User(sender.getName());
						user.setGamemode(GameMode.SURVIVAL);
						user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lSurvie");
						return true;
					}
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}

			if (cmd.getName().equalsIgnoreCase("gmsp")) {
				if (sender.hasPermission("MinParc.Gamemode")) {
					if (args.length == 1) {
						if (sender.hasPermission("main.gamemode.other")) {
							User user = new User(args[0]);
							if (user.isOnline()) {
								user.setGamemode(GameMode.SPECTATOR);
								user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lSpectateur");
								return true;
							} else {
								sender.sendMessage("§cLe joueur n'existe pas");
								return true;
							}
						} else {
							sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
							return true;
						}
					} else {
						if (!(sender instanceof Player))
							return true;
						User user = new User(sender.getName());
						user.setGamemode(GameMode.SPECTATOR);
						user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lSpectateur");
						return true;
					}
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}
			
			if (cmd.getName().equalsIgnoreCase("gma")) {
				if (sender.hasPermission("MinParc.Gamemode")) {
					if (args.length == 1) {
						if (sender.hasPermission("main.gamemode.other")) {
							User user = new User(args[0]);
							if (user.isOnline()) {
								user.setGamemode(GameMode.ADVENTURE);
								user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lAdventure");
								return true;
							} else {
								sender.sendMessage("§cLe joueur n'existe pas");
								return true;
							}
						} else {
							sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
							return true;
						}
					} else {
						if (!(sender instanceof Player))
							return true;
						User user = new User(sender.getName());
						user.setGamemode(GameMode.ADVENTURE);
						user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lAdventure");
						return true;
					}
				} else {
					sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
					return true;
				}
			}
			


			if (cmd.getName().equalsIgnoreCase("gm")) {
				if (sender.hasPermission("MinParc.Gamemode")) {
					if (args.length == 1) {
						if (!(sender instanceof Player))
							return true;
						User user = new User(sender.getName());
						switch (args[0]) {
						case "0":
							user.setGamemode(GameMode.SURVIVAL);
							user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lSurvival");

							break;
						case "1":
							user.setGamemode(GameMode.CREATIVE);
							user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lCréative");

							break;
						case "2":
							user.setGamemode(GameMode.ADVENTURE);
							user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lAdventure");

							break;
						case "3":
							user.setGamemode(GameMode.SPECTATOR);
							user.sendMessage(Core.prefix + "§7§lChangement de mode en §6§lSpectateur");

							break;
						default:
							user.sendMessage("§6Utilise /gm 0-3 [Player]");
							break;

						}
					}
				}
				}

		}
		return false;
	}
}
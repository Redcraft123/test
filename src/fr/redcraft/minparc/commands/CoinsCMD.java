package fr.redcraft.minparc.commands;








import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import fr.redcraft.api.Coin.CoinAPI;


public class CoinsCMD
implements CommandExecutor

{
	public static String[] getCommands() {
		String[] array = {"Coins"};
		return array;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		{
			Player player = (Player) sender;
			if(!sender.hasPermission("minparc.coin")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

			}

			if ((sender instanceof Player))
			{
				if (cmd.getName().equalsIgnoreCase("coins"))

					if (args.length == 0) {
						sender.sendMessage("Liste des commands");
						player.sendMessage("§6/Coins give §7{§fJoueur§7} §7{§fNombre§7}");
						player.sendMessage("§6/Coins remove §7{§fJoueur§7} §7{§fNombre§7}");
						player.sendMessage("§6/Coins reset §7{§fJoueur§7}");
						return true;
					}

				if (args.length == 1)
				{
					Player pl = Bukkit.getPlayerExact(args[0]);
					player.sendMessage("§6§l" + pl.getName() + " §epossède §6§l" + CoinAPI.getCoins(null, pl) + " §eMpCoins.");
					return true;
				}
				if(args.length == 2) {
					if (args[0].equalsIgnoreCase("reset")) 
					{
						Player pl = Bukkit.getPlayerExact(args[1]);
						CoinAPI.ResetCoins(null, pl);
						player.sendMessage("§6§l" + pl.getName() + " §e Vient  de ce faire reset sont compte.");
					}
					return true;
				}
				if(args.length == 3) {

					Player pl = Bukkit.getPlayerExact(args[1]);

					int nbr = 0;
					try
					{
						nbr = Integer.parseInt(args[2]);
					}
					catch (NumberFormatException e)
					{
						sender.sendMessage("§cLe nombre que vous avez entrée est incorrect.");
						return false;
					}

					if (args[0].equalsIgnoreCase("give")) 
					{
						CoinAPI.addCoins(null, pl, nbr);
						player.sendMessage("§6§l" + pl.getName() + " §evient de recevoir §6§l" + nbr + " §eMpCoins.");
						pl.sendMessage("§eVous venez de recevoir §6§l" + nbr + "MpCoins.");
					}
					

					if (args[0].equalsIgnoreCase("remove")) 
					{
						CoinAPI.delCoins(null, pl, nbr);
						player.sendMessage("§6§l" + pl.getName() + " §evient de se faire retirer §6§l" + nbr + " §eMpCoins.");
						pl.sendMessage("§eVous venez de vous faire supprimer §6§l" + nbr + "MpCoins.");

					}



				}

				return false;



			}
		}
		return false;
	}
}




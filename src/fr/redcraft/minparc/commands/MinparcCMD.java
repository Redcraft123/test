package fr.redcraft.minparc.commands;








import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.Attraction.AttractionEnums;
import fr.redcraft.minparc.data.SettingsManager;
import fr.redcraft.minparc.utils.AutoMessages;










public class MinparcCMD
implements CommandExecutor

{


	public static String[] getCommands() {
		String[] array = {"minparc"};
		return array;
	}

	@SuppressWarnings("static-access")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		{
			if(!sender.hasPermission("minparc.admin")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

			}

			if ((sender instanceof Player))
			{
				Player p = (Player)sender;
				if (cmd.getName().equalsIgnoreCase("minparc"))
					if (args.length == 0)
					{

					}

				if (args.length >= 1)



					if (args[0].equalsIgnoreCase("attraction")) 
					{	
						if (args.length > 2) {

						if(args[0].equalsIgnoreCase("on")) {
								if(AttractionEnums.getAttraByID(args[2]) == null) {						
									sender.sendMessage("§cLe nom de l'attraction est incorrect ou n'existe pas.");
									return false;
								}
								SettingsManager.getInstance().getConfig().set("access." + AttractionEnums.getAttraByName(args[1]), 0);
								SettingsManager.getInstance().saveData();
								Bukkit.broadcastMessage("§a§l" + args[2] + "§a est désormait Ouvert");
							}

							else if(args[0].equalsIgnoreCase("off")) {
								if(AttractionEnums.getAttraByID(args[2]) == null) {						
									sender.sendMessage("§cLe nom de l'attraction est incorrect ou n'existe pas.");
									return false;
								}		
								SettingsManager.getInstance().getConfig().set("access." + AttractionEnums.getAttraByName(args[1]), 1);
								SettingsManager.getInstance().saveData();
								Bukkit.broadcastMessage("§4§l" + args[2] + "§4 est désormait Fermé");

							}

							else if(args[0].equalsIgnoreCase("maintenance")) {
								if(AttractionEnums.getAttraByID(args[2]) == null) {						
									sender.sendMessage("§cLe nom de l'attraction est incorrect ou n'existe pas.");
									return false;
								}				
								SettingsManager.getInstance().getConfig().set("access." + AttractionEnums.getAttraByName(args[1]), 2);
								SettingsManager.getInstance().saveData();		
								Bukkit.broadcastMessage("§6§l" + args[2] + "§6 est désormait en maintenance");

							}

							else if(args[0].equalsIgnoreCase("list")) {

								for(AttractionEnums attraction : AttractionEnums.values()) {
									sender.sendMessage("§6Liste des attraction du parc : ");
									sender.sendMessage("§f- §6" + attraction.getName());
									return true;
								}
							}
						}
					}


				if (args[0].equalsIgnoreCase("automessages")) {
					int broadcast;
					int morceau;
					if (p.isOp())
					{
						if (args.length == 1)
						{
							new AutoMessages().sendAutoMessages();
							p.sendMessage(Core.prefix + "§6Vous avez activé les messages automatiques");
						}
						if (args.length == 2) {
							if (args[1].equalsIgnoreCase("load"))
							{
								new AutoMessages().loadAutoMessages();
								p.sendMessage(Core.prefix + "§6Vous avez charger les messages automatiques");
							}
							if (args[1].equalsIgnoreCase("list"))
							{
								AutoMessages.getMessagesList(p);
							}


						}
						if (args.length > 2) {

							if (args[1].equalsIgnoreCase("delete"))
							{
								String n = args[2].toString();
								int num = Integer.parseInt(n);
								AutoMessages.delAutoMessage(num);	
								p.sendMessage(Core.prefix + "§6Vous venez de supprimé le message automatique §f>> §e " + num);
							}
							if (args[1].equalsIgnoreCase("on"))
							{
								String n = args[2].toString();
								int num = Integer.parseInt(n);
								AutoMessages.setEnableMessage(num, true);
								p.sendMessage(Core.prefix + "§6Vous venez d'activé le message automatique §f>> §e " + num);
							}
							if (args[1].equalsIgnoreCase("off"))
							{
								String n = args[2].toString();
								int num = Integer.parseInt(n);
								p.sendMessage(Core.prefix + "§6Vous venez de desactivé le message automatique §f>> §e " + num);

							}
							if (args[1].equalsIgnoreCase("add"))
							{
								StringBuilder builder = new StringBuilder("");
								String[] arrayOfString1;
								morceau = (arrayOfString1 = args).length;
								for (broadcast = 0; broadcast < morceau; broadcast++)
								{
									String msg = arrayOfString1[broadcast];
									if ((msg != args[0]) && (msg != args[1]) && (!msg.equals("")))
									{
										builder.append(" ");
										builder.append(msg);
									}
								}
								AutoMessages.addAutoMessage(builder.toString().substring(1));
								p.sendMessage(Core.prefix + "Vous venez de crée un nouveau message automatique");
								p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 20,20);
							}
						}
					}
				}

			}


		}

		if (args[0].equalsIgnoreCase("jump")) 
		{

		}

		if (args[0].equalsIgnoreCase("par")) 
		{
		}

		if (args[0].equalsIgnoreCase("bal")) 
		{

		}

		if (args[0].equalsIgnoreCase("bar")) 
		{


		}

		return false;
	}
}




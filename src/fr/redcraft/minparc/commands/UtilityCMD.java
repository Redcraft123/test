
package fr.redcraft.minparc.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.functions.User;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_13_R2.MinecraftServer;

;

public class UtilityCMD implements CommandExecutor {
	public static final HashMap<String, String> messages = new HashMap<String, String>();
	public static String[] getCommands() {
		String[] array = { "speed", "fly", "top","craft","nick","ci","back","ec","invsee","itemdb","lag","broadcast","msg","r","test"};
		return array;
	}
	
	 private String format(double tps)
	  {
	      return ( ( tps > 18.0 ) ? ChatColor.GREEN : ( tps > 16.0 ) ? ChatColor.YELLOW : ChatColor.RED ).toString()
	              + ( ( tps > 20.0 ) ? "*" : "" ) + Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 );
	  }
	

	@SuppressWarnings({ "static-access", "deprecation" })
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
			if (!sender.hasPermission("MinParc.Speed")) {
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
		
		if(cmd.getName().equalsIgnoreCase("feed")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.Feed")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}
			
			 if (args.length == 1) {
		    	  Player target = Bukkit.getPlayerExact(args[0]);
		    	  
		    	  player.openInventory(target.getInventory()); 
		    	  player.sendMessage(Core.prefix + "§6Vous venez d'ouvrir l'inventaire de §e" + target.getName());  
					return true;

			 }
		    
		}
		
		if(cmd.getName().equalsIgnoreCase("invsee")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.Invsee")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}
			
			 if (args.length == 1) {
		    	  Player target = Bukkit.getPlayerExact(args[0]);
		    	  
		    	  player.openInventory(target.getInventory()); 
		    	  player.sendMessage(Core.prefix + "§6Vous venez d'ouvrir l'inventaire de §e" + target.getName());  
					return true;

			 }
		    
		}
		
		if(cmd.getName().equalsIgnoreCase("ec")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.Enderchest")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}
			
			 if (args.length == 1) {
		    	  Player target = Bukkit.getPlayerExact(args[0]);
		    	  
		    	  player.openInventory(target.getEnderChest()); 
		    	  player.sendMessage(Core.prefix + "§6Vous venez d'ouvrir l'EnderChest de §e" + target.getName());  
					return true;

			 }
		    
		}
			
		if(cmd.getName().equalsIgnoreCase("itemdb")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.itemdb")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}
				if (args.length == 0) {
					sender.sendMessage(player.getItemInHand().getType().toString());
					return true;
				}
			}
	
		
		if(cmd.getName().equalsIgnoreCase("test")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.itemdb")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}
				if (args.length == 0) {
				
					return true;
				}
			}
	
		
	
		if(cmd.getName().equalsIgnoreCase("lag")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.lag")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}

			if (args.length == 0) {
	          StringBuilder sb = new StringBuilder ("§6§lTps §7" );

	    	  for ( double tps : MinecraftServer.getServer().recentTps)
	          {
	              sb.append( format( tps ) );
	              sb.append( ", " );
	          }
	    	  player.sendMessage(sb.substring( 0, sb.length() - 2 ));
	    	  player.sendMessage("§6§lMax Memory §7 " + Runtime.getRuntime().maxMemory() / 1024 / 1024);
	    	  player.sendMessage("§6§lTotal-Memory §7 " + Runtime.getRuntime().totalMemory() / 1024 / 1024);
	    	  player.sendMessage("§6§lMemory-Free §7 " + Runtime.getRuntime().freeMemory() / 1024 / 1024);
	    	  player.sendMessage("§6§lNombre de joueur connecté §7 : " + Bukkit.getOnlinePlayers().size());
	    	  
	      }
			

	    return false;
	  
		}

		
		
		if(cmd.getName().equalsIgnoreCase("msg")) {
			Player player = (Player)sender;

			if (args.length < 2)
		      {
		        sender.sendMessage("§c/msg <player> <message>");
		        return true;
		      }
			
			  String msg = "";
		      String[] arrayOfString;
		      int j = (arrayOfString = args).length;
		      for (int i = 0; i < j; i++)
		      {
		        String s = arrayOfString[i];
		        msg = msg + " " + s;
		      }
		      Bukkit.getPlayer(args[0]).sendMessage(player.getName() + " -> §7[§6Moi§7]§f " + msg.replaceFirst(new StringBuilder(" ").append(args[0]).toString(), ""));
		      player.sendMessage("§7[§6Moi§7]§f -> " + args[0] + ": " + msg.replaceFirst(new StringBuilder(" ").append(args[0]).toString(), ""));
		      messages.put(player.getName(), args[0]);
		      messages.put(args[0], player.getName());
				return true;
		}
			
	
	
		if(cmd.getName().equalsIgnoreCase("r")) {
			Player player = (Player)sender;
			if (args.length < 1)
		      {
				sender.sendMessage("§c/r <message>");
		        return true;
		      }
		      if (!messages.containsKey(player.getName()))
		      {
		    	  player.sendMessage("§fVous n'avez personne à qui répondre");
		        return true;
		      }
		      if (Bukkit.getPlayer((String)messages.get(player.getName())) == null)
		      {
		        player.sendMessage("Le joueur n'est pas en ligne");
		        return true;
		      }
		      
		      String msg = "";
		      String[] arrayOfString;
		      int j = (arrayOfString = args).length;
		      for (int i = 0; i < j; i++)
		      {
		        String s = arrayOfString[i];
		        msg = msg + " " + s;
		      }
		      Bukkit.getPlayer((String)messages.get(player.getName())).sendMessage(player.getName() + " -> �7[�6Moi�7]�f" + msg);
		      player.sendMessage("§7[§6Moi§7]§f -> " + (String)messages.get(player.getName()) + ":" + msg);
				return true;

		}
	

		
		
		if(cmd.getName().equalsIgnoreCase("broadcast")) {
			Player player = (Player) sender;
			if(!sender.hasPermission("MinParc.Broadcast")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}
			if(args.length == 0){
				player.sendMessage("§c/broadcast <message>");
			} else {

				StringBuilder bc = new StringBuilder();
				for(String part : args){
					bc.append(part + " ");
				}

				Bukkit.broadcastMessage("§f§m                                                                        ");
				Bukkit.broadcastMessage("§1");
				Bukkit.broadcastMessage("§6<{§f-§6Annonce§f-§6}> §7: §f " + bc.toString().replace("&", "§"));
				Bukkit.broadcastMessage("§3");
				Bukkit.broadcastMessage("§f§m                                                                        ");



			}
		}

		if (cmd.getName().equalsIgnoreCase("craft")) {
			if (!sender.hasPermission("MinParc.Craft")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");

				return false;
			}					
			if (!(sender instanceof Player))
				return true;
			User user = new User(sender.getName());
			user.openCraft();
			return true;

		
		}


		if (cmd.getName().equalsIgnoreCase("back")) {
			if (!sender.hasPermission("MinParc.Back")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}							
			if (!(sender instanceof Player))
				return true;
			Player player = (Player) sender;
			User user = new User(player.getName());
			player.teleport(user.tpback.get(player.getName()));
			sender.sendMessage("§6Téléportation...");
			return true;

		
		}
		
		if (cmd.getName().equalsIgnoreCase("ci")) {
			if (!sender.hasPermission("MinParc.Ci")) {
				sender.sendMessage("§cVous n'avez pas la permission d'utiliser cette command");
				return false;
			}							
			if (!(sender instanceof Player))
				return true;
			User user = new User(sender.getName());
			user.getPlayer().getInventory().clear();
			sender.sendMessage(Core.prefix + "§6 Vous veneu de clear votre inventaire");
			return true;

		}
		
		



	if (cmd.getName().equalsIgnoreCase("top")) {
		if (!sender.hasPermission("MinParc.Top")) {
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
					sender.sendMessage("§c/nick <player> <name>");
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

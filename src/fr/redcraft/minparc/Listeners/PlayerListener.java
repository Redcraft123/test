package fr.redcraft.minparc.Listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import fr.redcraft.api.Items.ItemBuilder;
import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.Menu.MinparcMainInventory;
import fr.redcraft.minparc.functions.User;
import fr.redcraft.minparc.utils.AFK;
import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws IOException {
		Player player = event.getPlayer();
		User user = new User(player.getName());
		event.setJoinMessage("§8[§a+§8] " + user.getName() + " §5a rejoint MinParc§9 Resort");
		user.sendFullTitle(user.getPlayer(), "Bienvenue " + user.getName(), "sur MinParc Resort", 30, 30, 30);
		user.setHealth(20);
		user.sendMessage("---------------------------------------------");
		user.sendMessage("§5§lM§5in§9§lP§9arc Resort");
		user.sendMessage("§3");
		user.sendMessage("§e§oBienvenue !");
		user.sendMessage("§2");
		user.sendMessage("§ePour obtenir le ressource pack veuillez activer les ressources pack automatique ou faite §e§l/pack");
		user.sendMessage("§1");
		user.sendMessage("§6§lAudio §f: §6/audio");
		user.sendMessage("§6§lRègles du server §f: §6/rules");
		user.sendMessage("---------------------------------------------");
		user.setItem(new ItemBuilder("§7-§e{§5Min§9Parc Resort§e}§7-", Material.IRON_AXE, 1).setUnbreakable(true).build(),4);
		if(!user.exists()) {
			user.newUser();
			user.save();
			Core.getInstance().getLogger().info("Création du fichier joueur de " + user.getName() + "...");
		}
		if(!player.hasPlayedBefore()) {

			User.sendAllMessage("§6Souhaitons la bienvenue à §e" + player.getName() + "§6 sur MinParc Resort ");
			if(!user.exists()) {
				user.newUser();
				Core.getInstance().getLogger().info("Création du fichier joueur de " + user.getName() + "...");

			}
		}
	}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Player player = e.getPlayer();
		User user = new User(player.getName());
		user.saveLastLocation();
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		AFK afk = new AFK(event.getPlayer());
		if (!afk.isAfk()) {
			afk.autoAfk(event.getPlayer().getLocation());
			return;
		}
	}


	@EventHandler
	public void onLeft(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		User user = new User(player.getName());
		user.save();
		user.saveLastLocation();
		event.setQuitMessage("§8[§c-§8] " + user.getName() + " §5a quitté MinParc §9Resort");
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("MinParc.Colo")) {
			event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		}
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String sender = player.getName();
		String message = event.getMessage();

		for(Player pls : Bukkit.getOnlinePlayers()) {
			if(pls.hasPermission("MinParc.SocialSpy")) {
				pls.sendMessage("§4" + sender + "§8§l>> §6" + message);
			}
		}
	}

	@EventHandler
	public void onTP(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		User user = new User(player.getName());
		user.saveLastLocation();
	}
	
	@EventHandler
	public void onC(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getClickedInventory().getType() == InventoryType.CHEST) {
		player.playSound(player.getLocation(), Sound.BLOCK_DISPENSER_FAIL, 2, 2);
		}
	}
	
	 @EventHandler
	  public void onClickItem(PlayerInteractEvent event)
	  {
	    Player player = event.getPlayer();
	    Action action = event.getAction();
		@SuppressWarnings("deprecation")
		ItemStack item = player.getItemInHand();
	    

	    if (((action == Action.RIGHT_CLICK_AIR) || (action == Action.RIGHT_CLICK_BLOCK) || (action == Action.LEFT_CLICK_AIR)) && 
	      (!player.isInsideVehicle()) && 
	      (item.getType() == Material.IRON_AXE) && (item.getItemMeta().getDisplayName().equals("§7-§e{§5Min§9Parc Resort§e}§7-"))) {
	    	MinparcMainInventory.openMinparcInventory(player);

	    }
	  }
	    @EventHandler
	    public void onDrop(PlayerDropItemEvent event)
	    {
	      if (event.getItemDrop().getType() == null) {
	        return;
	      }
	      if (!event.getItemDrop().getItemStack().hasItemMeta()) {
	        return;
	      }
	      if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§7-§e{§5Min§9Parc Resort§e}§7-")) {
	        event.setCancelled(true);
	      }
	      else if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§7-§e{§6Attractions§f/§6lands§e}§7-")) {
	          event.setCancelled(true);
	        }
	      else if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§7-§e{§bBackpack/sac à dos§e}§7-")) {
	          event.setCancelled(true);
	        }
	    }
	    
	    @EventHandler(priority=EventPriority.HIGHEST)
	    public void onSwapHandItems(PlayerSwapHandItemsEvent event)
	    {
	      if (event.getOffHandItem() != null)
	      {
	        if ((event.getOffHandItem().getType() == Material.IRON_AXE) && (event.getOffHandItem().getItemMeta().getDisplayName().equals("§7-§e{§5Min§9Parc Resort§e}§7-"))) {
	          event.setCancelled(true);
	        }
	        if ((event.getOffHandItem().getType() == Material.MINECART) && (event.getOffHandItem().getItemMeta().getDisplayName().equals("§7-§e{§6Attractions§f/§6lands§e}§7-"))) {
	            event.setCancelled(true);
	          }
	        if ((event.getOffHandItem().getType() == Material.CHEST) && (event.getOffHandItem().getItemMeta().getDisplayName().equals("§7-§e{§bBackpack/sac à dos§e}§7-"))) {
	            event.setCancelled(true);
	          }
	        event.getPlayer().closeInventory();
	      }
	    }
	  
	  }


package fr.redcraft.minparc.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import fr.redcraft.minparc.functions.User;
import net.md_5.bungee.api.ChatColor;

public class BlockListener
implements Listener
{
	
	 @EventHandler
	  public void onSignChange(SignChangeEvent event)
	  {
	    if (!event.getPlayer().hasPermission("MinParc.Signcolor")) {
	      return;
	    }
	    event.setLine(0, ChatColor.translateAlternateColorCodes('&', event.getLine(0)));
	    event.setLine(1, ChatColor.translateAlternateColorCodes('&', event.getLine(1)));
	    event.setLine(2, ChatColor.translateAlternateColorCodes('&', event.getLine(2)));
	    event.setLine(3, ChatColor.translateAlternateColorCodes('&', event.getLine(3)));
	  }
	  
	@EventHandler
	public void onPressurePressEvent(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		User user = new User(player.getName());
		if ((!player.hasPermission("MinParc.interact")) && 
				(event.getClickedBlock() != null) && (
						((block != null) && (block.getType() == Material.ACACIA_DOOR)) || (block.getType() == Material.BIRCH_DOOR) || (block.getType() == Material.DARK_OAK_DOOR) || (block.getType() == Material.OAK_DOOR) || (block.getType() == Material.SPRUCE_DOOR) || (block.getType() == Material.JUNGLE_DOOR) || (block.getType() == Material.ACACIA_TRAPDOOR) || (block.getType() == Material.OAK_TRAPDOOR) || (block.getType() == Material.SPRUCE_TRAPDOOR) || (block.getType() == Material.DARK_OAK_TRAPDOOR) || (block.getType() == Material.ACACIA_FENCE_GATE) || (block.getType() == Material.JUNGLE_FENCE_GATE) || (block.getType() == Material.BIRCH_FENCE_GATE) || (block.getType() == Material.DARK_OAK_FENCE_GATE) || (block.getType() == Material.OAK_FENCE_GATE) || (block.getType() == Material.SPRUCE_FENCE_GATE) || (block.getType() == Material.BIRCH_TRAPDOOR) || (block.getType() == Material.JUNGLE_TRAPDOOR) || (block.getType() == Material.STONE_BUTTON) || (block.getType() == Material.CHEST) || (block.getType() == Material.ANVIL)))
		{
			user.sendMessage("§cImpossible de casser des blocks.");
			player.playSound(user.getLastLocation(), Sound.BLOCK_ANVIL_USE, 5, 5);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		User user = new User(player.getName());
		if (!player.hasPermission("minparc.Build"))
		{
			user.sendMessage("§cImpossible de casser des blocks.");
			player.playSound(user.getLastLocation(), Sound.BLOCK_ANVIL_USE, 5, 5);
			event.setCancelled(true);
		}
	}

	@EventHandler
	  public void onTrash(PlayerInteractEvent event)
	  {
	    Player p = event.getPlayer();
	    Block block = event.getClickedBlock();
	    if ((!p.hasPermission("MinParc.poubelle")) && 
	      (event.getClickedBlock() != null) && 
	      (block != null) && (block.getType() == Material.CAULDRON))
	    {
	      Inventory inv = Bukkit.createInventory(null, 27, "§cPoubelle");
	      p.openInventory(inv);
	    }
	  }
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		User user = new User(player.getName());
		if (!player.hasPermission("minparc.place"))
		{
			user.sendMessage("§cImpossible de casser des blocks.");
			player.playSound(user.getLastLocation(), Sound.BLOCK_ANVIL_USE, 5, 5);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent event)
	{
		event.setCancelled(true);
	}

	@EventHandler
	public void onExplosion(EntityExplodeEvent e)
	{	
		if (e.getEntity().getType() == EntityType.PRIMED_TNT)  {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onExplosion1(EntityExplodeEvent e)
	{
		if (e.getEntity().getType() == EntityType.ENDER_CRYSTAL) {
			e.setCancelled(true);
		}
	}
}

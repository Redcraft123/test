package fr.redcraft.minparc.Listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class EntityListener implements Listener{
	
	
	@EventHandler
	  public void onRight(PlayerInteractAtEntityEvent e)
	  {
	    if ((e.getRightClicked() instanceof ArmorStand))
	    {
	      Player p = e.getPlayer();
	      ArmorStand stand = (ArmorStand)e.getRightClicked();
	      if (stand.getName().equalsIgnoreCase("banc"))
	      {
	    	  
	        stand.addPassenger(p);
	        stand.setCustomNameVisible(false);
	      }
	      e.setCancelled(true);
	    }
	  }
}

package fr.redcraft.minparc.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;



import fr.redcraft.api.Items.ItemBuilder;

public class LibertySquareLandGUI implements Listener {

	



	public static void openFrontierInventory(Player player) {

		Inventory inv = Bukkit.createInventory(null, 9, "§6LibertySquare");

		
		inv.setItem(3, new ItemBuilder("§6LibertySquare", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore("Ouvert/open")
				.build());

		inv.setItem(4, new ItemBuilder("§6Hollywood Story", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore("Ouvert/open")
				.build());
		inv.setItem(5, new ItemBuilder("§6New Orléans Square", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore("Ouvert/open")
				.build());


		player.openInventory(inv);

	}

	@EventHandler
	public void onInteract(InventoryClickEvent e)
	{
		Player player = (Player)e.getWhoClicked();
		String inv = e.getView().getTitle();
		ItemStack current = e.getCurrentItem();
		if ((inv != null) && (inv != null) && 
				(inv.equals("§6LibertySquare")))
		{
			player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() -1);

			if (current == null) {
				return;
			}
		
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eWild West")))
			{
				e.setCancelled(true);

			}
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eFrontierland Tour")))
			{
				e.setCancelled(true);
			}

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eMinParc Railroad - Frontierland Station")))
			{
				e.setCancelled(true);
			}
			else if ((current.getType() == Material.BARRIER) && (current.getItemMeta().getDisplayName().equals("§cRetour")))
			{
			}
		}
	}
}



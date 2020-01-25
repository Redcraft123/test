package fr.redcraft.minparc.Menu.Lands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import fr.redcraft.api.Items.ItemBuilder;
import fr.redcraft.minparc.Attraction.AttractionManagers;
import fr.redcraft.minparc.Menu.LandMinParcGUI;

public class DiscoverylandGUI implements Listener {
	
    public static  Location orbi = new Location(Bukkit.getWorld("MINPARC"),4159.058,62,3931.464);

	
	public static void openDiscoInventory(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§1Discoveryland");
		inv.setItem(0, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(1, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(2, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(3, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(4, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(5, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(6, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(7, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(8, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());

		inv.setItem(18, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(19, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(20, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(21, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(22, new ItemBuilder("§cRetour", Material.BARRIER, 1, 14).addLore(" ").build());
		inv.setItem(23, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(24, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(25, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(26, new ItemBuilder(" ", Material.BLUE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		
		inv.setItem(9, new ItemBuilder("§eOrbitron", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("Orbitron"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eFlatRide")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());
		
		inv.setItem(10, new ItemBuilder("§eMinParc Railroad - Discoveryland Station", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("MinParcRailroadDiscoveryland Station"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eTransport")
				.addLore("§b-§eAudio")
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
				(inv.equals("§1Discoveryland")))
		{
			if (current == null) {
				return;
			}
			e.setCancelled(true);
			if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eMinParc Railroad - Discoveryland Station")))
			{

				e.setCancelled(true);
			}

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eOrbitron")))
			{
				if(AttractionManagers.getAttraStatus("Orbitron")) {
				
					player.teleport(orbi);
				}
					else {
						e.setCancelled(true);
					}
				}
				
	
			else if ((current.getType() == Material.BLUE_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
			
			else if ((current.getType() == Material.BARRIER) && (current.getItemMeta().getDisplayName().equals("§cRetour")))
			{
				LandMinParcGUI.openMinparcInventory(player);
			}
		}
	}
}

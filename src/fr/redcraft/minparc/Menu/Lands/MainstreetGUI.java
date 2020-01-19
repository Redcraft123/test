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

public class MainstreetGUI implements Listener {
	
    public static  Location transport = new Location(Bukkit.getWorld("MINPARC"),4049.356,63,4259.591);
    public static  Location aqua = new Location(Bukkit.getWorld("MINPARC"),4016.191,62,4071.027);
    public static  Location roue = new Location(Bukkit.getWorld("MINPARC"),3877.743,62,3887.147);

	
	public static void openMSInventory(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§6MainStreet");
		inv.setItem(0, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(1, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(2, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(3, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(4, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(5, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(6, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(7, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(8, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());

		inv.setItem(18, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(19, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(20, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(21, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(22, new ItemBuilder("§cRetour", Material.BARRIER, 1, 14).addLore(" ").build());
		inv.setItem(23, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(24, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(25, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(26, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		
		inv.setItem(9, new ItemBuilder("§eDisneyland Transportation", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("DisneylandTransportation"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eTransport")
				.addLore("§b-§eAudio")
				.build());
		

		inv.setItem(10, new ItemBuilder("§eAquarium", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("Aquarium"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eVisite")
				.addLore("§b-§eAudio")
				.build());
		
		
		
		
		inv.setItem(11, new ItemBuilder("§eGrande Roue", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("GrandeRoue"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eFlatRide")
				.addLore("§b-§eAudio")
				.build());
		
		inv.setItem(12, new ItemBuilder("§eMinParc Railroad - Main Street", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("MinParcRailroad-MainStreet"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eTransport")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
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
				(inv.equals("§6MainStreet")))
		{
			if (current == null) {
				return;
			}
			e.setCancelled(true);
			if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eDisneyland Transportation")))
			{
				if(AttractionManagers.getAttraStatus("DisneylandTransportation")) {

					player.teleport(transport);
				}
					else {
						e.setCancelled(true);
					}
				}

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eAquarium")))
			{
				if(AttractionManagers.getAttraStatus("Aquarium")) {

					player.teleport(aqua);
				}
					else {
						e.setCancelled(true);
					}
				}
			
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eGrande Roue")))
			{
				if(AttractionManagers.getAttraStatus("GrandRoue")) {

					player.teleport(roue);
				}
					else {
						e.setCancelled(true);
					}
				}
			
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eMinParc Railroad - Main Street")))
			{
				e.setCancelled(true);
			}
			
			else if ((current.getType() == Material.WHITE_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
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

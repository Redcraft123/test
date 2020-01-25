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

public class RubixWorldGUI implements Listener {
	
    public static  Location cube = new Location(Bukkit.getWorld("MINPARC"),4227.607,63,3576.339);
    public static  Location pyra = new Location(Bukkit.getWorld("MINPARC"),4495.742,57,3535.877);
    public static  Location fall = new Location(Bukkit.getWorld("MINPARC"),4374.072,60,3531.164);

	
	public static void openRubixInventory(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 27, "§2Rubik's World");
		inv.setItem(0, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(1, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(2, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(3, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(4, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(5, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(6, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(7, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(8, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());

		inv.setItem(18, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(19, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(20, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(21, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(22, new ItemBuilder("§cRetour", Material.BARRIER, 1, 14).addLore(" ").build());
		inv.setItem(23, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(24, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(25, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(26, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		
		

		inv.setItem(9, new ItemBuilder("§eCube Adventure", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("CubeAdventure"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eRollerCoaster")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());
		
		inv.setItem(10, new ItemBuilder("§ePyraminx", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("Pyraminx"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eRollerCoaster")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());
		
		inv.setItem(11, new ItemBuilder("§eRubik's Falls", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("RubixFalls"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eAquatique")
				.addLore("§b-§eAudio")
				.build());
		

		inv.setItem(12, new ItemBuilder("§ePlanet Exploration", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("PlanetExploration"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eRollerCoaster")
				.addLore("§b-§eDarkRide")
				.addLore("§b-§eAquatique")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());
		

		inv.setItem(13, new ItemBuilder("§eMinParc Railroad - Rubik's Station", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("MinParcRailroadRubikStation"))
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
				(inv.equals("§2Rubik's World")))
		{
			if (current == null) {
				return;
			}
			e.setCancelled(true);
			if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eCube Adventure")))
			{
				if(AttractionManagers.getAttraStatus("CubeAdventure")) {

					player.teleport(cube);
				}
					else {
					//	RegionUtils.RubixCube(p);
						e.setCancelled(true);
					}
				}
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§ePyraminx")))
			{
				if(AttractionManagers.getAttraStatus("Pyraminx")) {

					player.teleport(pyra);
				}
					else {
						//RegionUtils.Pyraminx(p);
						e.setCancelled(true);
					}
				}
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eRubik's Falls")))
			{
				if(AttractionManagers.getAttraStatus("RubixFalls")) {

					player.teleport(fall);
				}
					else {
						//RegionUtils.RubixFalls(p);
						e.setCancelled(true);
					}
				}
			
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§cPlanet Exploration")))
			{
				e.setCancelled(true);
			}
			
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eMinParc Railroad - Rubik's Station")))
			{
				e.setCancelled(true);
			}
			
			else if ((current.getType() == Material.LIME_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
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

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

public class FrontierlandGUI implements Listener {

	public static  Location btm = new Location(Bukkit.getWorld("MINPARC"),3577.648,64,4102.075);
	public static  Location pm = new Location(Bukkit.getWorld("MINPARC"),3824.693,59,4208.321);
	public static  Location tir = new Location(Bukkit.getWorld("MINPARC"),3848.139,62,4051.913);
	public static  Location splash = new Location(Bukkit.getWorld("MINPARC"),3475.913,69,3959.493);



	public static void openFrontierInventory(Player player) {

		Inventory inv = Bukkit.createInventory(null, 27, "§6Frontierland");

		inv.setItem(0, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(1, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(2, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(3, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(4, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(5, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(6, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(7, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(8, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());

		inv.setItem(18, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(19, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(20, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(21, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(22, new ItemBuilder("§cRetour", Material.BARRIER, 1, 14).addLore(" ").build());
		inv.setItem(23, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(24, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(25, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		inv.setItem(26, new ItemBuilder(" ", Material.ORANGE_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());



		inv.setItem(9, new ItemBuilder("§ePhantomManor", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("PhantomManor"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eDarkRide")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")



				.build());

		inv.setItem(10, new ItemBuilder("§eBig Thunder Mountain", Material.PAPER, 1,191)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("BigThunderMountain"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eRollerCoaster")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")

				.build());

		inv.setItem(11, new ItemBuilder("§eTir de l'Ouest", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("Tirdel'Ouest"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eMini-Jeux")
				.build());

		inv.setItem(12, new ItemBuilder("§eSplash Mountain", Material.PAPER, 1,291)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("SplashMountain"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eDarkRide")
				.addLore("§b-§eAudio")
				.addLore("§b-§eAquatique")
				.addLore("§b-§e3D")
				.build());

		inv.setItem(13, new ItemBuilder("§eGeyser Expedition", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("GeyserExpedition"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eRollerCoaster")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());

		inv.setItem(14, new ItemBuilder("§eRiver Rogue Keel Boat", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : §c§oFermé/close")
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eTransport")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());

		inv.setItem(15, new ItemBuilder("§eWild West", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("WildWest"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eRollerCoaster")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());


		inv.setItem(16, new ItemBuilder("§eFrontierland Tour", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("FrontierlandTour"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eTransport")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());

		inv.setItem(17, new ItemBuilder("§eMinParc Railroad - Frontierland Station", Material.PAPER, 1)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("MinParcRailroad-FrontierlandStation"))
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
				(inv.equals("§6Frontierland")))
		{
			player.getItemOnCursor().setAmount(player.getItemOnCursor().getAmount() -1);

			if (current == null) {
				return;
			}
			e.setCancelled(true);
			if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§ePhantomManor")))
			{
				if(AttractionManagers.getAttraStatus("PhantomManor")) {

					player.teleport(pm);
				}
				else {

					//RegionUtils.PhantomManor(p);
					e.setCancelled(true);
				}
			}


			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eBig Thunder Mountain")))
			{
				if(AttractionManagers.getAttraStatus("BigThunderMountain")) {

					player.teleport(btm);
				}
				else {
					//RegionUtils.BTM(p);
					e.setCancelled(true);


				}
			}


			else if ((current.getType() == Material.ORANGE_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eTir de l'Ouest")))
			{
				if(AttractionManagers.getAttraStatus("TirdelOuest")) {

					player.teleport(tir);
				}
				else {
					e.setCancelled(true);
				}
			}

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eSplash Mountain")))
			{
				if(AttractionManagers.getAttraStatus("SplashMountain")) {

					player.teleport(splash);
				}
				else {
					//RegionUtils.SplashMountain(p);
					e.setCancelled(true);
				}
			}			
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eGeyser Expedition")))
			{
				e.setCancelled(true);
			}
			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eRiver Rogue Keel Boat")))
			{
				e.setCancelled(true);
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
				LandMinParcGUI.openMinparcInventory(player);
			}
		}
	}
}



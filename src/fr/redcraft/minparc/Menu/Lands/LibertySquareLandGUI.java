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

public class LibertySquareLandGUI implements Listener {



    public static  Location story = new Location(Bukkit.getWorld("MINPARC"),3770.098,64,3821.738);
    public static  Location tot = new Location(Bukkit.getWorld("MINPARC"),3711.300,62,3678);


	public static void openFrontierInventory(Player player) {

		Inventory inv = Bukkit.createInventory(null, 27, "§6LibertySquare");

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
		inv.setItem(12, new ItemBuilder("§6The Story of Liberty", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("StoryOfLiberty"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eDarkRide")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.addLore("§b-§eDarkRide")
				.build());


		inv.setItem(13, new ItemBuilder("§6MinParc Transportation - LibertySquare station", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("DisneyLandTransportatonLiberty"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eTransport")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.build());

		inv.setItem(14, new ItemBuilder("§6The Hollywood Tower Hotel", Material.PAPER, 1,50).setUnbreakable(true)
				.addLore(" ")
				.addLore("§eStatus : " + AttractionManagers.getAttraStatusByName("TheHollywoodTowerHotel"))
				.addLore(" ")
				.addLore("§eAttente : ")
				.addLore(" ")
				.addLore("§eCatégorie : ")
				.addLore("§b-§eFlatRide")
				.addLore("§b-§eAudio")
				.addLore("§b-§e3D")
				.addLore("§b-§eFreeFall")
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

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§6The Story of Liberty")))
			{
				if(AttractionManagers.getAttraStatus("StoryOfLiberty")) {
					player.teleport(story);
				}
				else {
					//	RegionUtils.RubixCube(p);
					e.setCancelled(true);
				}
			}

			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§eFrontierland Tour")))
			{
				if(AttractionManagers.getAttraStatus(" ")) {

				}
				else {
					//	RegionUtils.RubixCube(p);
					e.setCancelled(true);
				}
			}			


			else if ((current.getType() == Material.PAPER) && (current.getItemMeta().getDisplayName().equals("§6The Hollywood Tower Hotel")))
			{
				if(AttractionManagers.getAttraStatus("TheHollywoodTowerHotel")) {
					player.teleport(tot);
				}
				else {
					//	RegionUtils.RubixCube(p);
					e.setCancelled(true);
				}
			}
			else if ((current.getType() == Material.BARRIER) && (current.getItemMeta().getDisplayName().equals("§cRetour")))
			{
				LandMinParcGUI.openMinparcInventory(player);
			}
			
			else if ((current.getType() == Material.WHITE_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
		}
	}
}





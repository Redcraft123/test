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
import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.data.SettingsManager;



public class ShowGUI implements Listener{

	public static void InvShow(Player player) {

		Inventory invMenu = Bukkit.createInventory(player, 27, "§6Show/spectacle");

		//jaune
		invMenu.setItem(0, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(1, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(7, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(8, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(9, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(17, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(18, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(19, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(25, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(26, new ItemBuilder(" ", Material.MAGENTA_STAINED_GLASS_PANE, 1, 14).addLore(" ").build());
		invMenu.setItem(22, new ItemBuilder("§cRetour", Material.BARRIER, 1, 14).addLore(" ").build());

		//orange




		//menu
		invMenu.setItem(10, new ItemBuilder("§6DisneyDreams", Material.FIREWORK_ROCKET, 1, 14)
				.addLore("§eHoraire §7: ")
				.addLore("§6 ")
				.addLore("§eVendredi §7: " + SettingsManager.getInstance().getData().getString("DisneyDreams.vendredi"))
				.addLore("§eSamedi §7: §f19H")
				.addLore("§eDimanche §7: §f19H")
				.build());

		invMenu.setItem(13, new ItemBuilder("§6Fountain Of Nation", Material.FIREWORK_ROCKET, 1, 0)

				.addLore("§eHoraire §7: ")
				.addLore("§6 ")
				.addLore("§eSamedi §7: §f18H")
				.build());

		invMenu.setItem(16, new ItemBuilder("§6Bientôt/soon", Material.FIREWORK_ROCKET, 1, 14)
				.addLore("§4")
				.build());

		player.openInventory(invMenu);

	}


	@EventHandler
	public void onInteract(InventoryClickEvent e)
	{
		Player player = (Player)e.getWhoClicked();
		String inv = e.getView().getTitle();

		ItemStack current = e.getCurrentItem();
		if ((inv != null) && (inv != null) && 
				(inv.equals("§6Show/spectacle")))
		{
			if (current == null) {
				return;
			}
			e.setCancelled(true);
			if ((current.getType() == Material.MAGENTA_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
			
			if ((current.getType() == Material.BARRIER) && (current.getItemMeta().getDisplayName().equals("§cRetour")))
			{
				MinparcMainInventory.openMinparcInventory(player);
			}

			else if ((current.getType() == Material.FIREWORK_ROCKET) && (current.getItemMeta().getDisplayName().equals("§6DisneyDreams")))
			{
				Core.getWarpManager().teleportToWarp(player, "DisneyDreams");
			}

			else if ((current.getType() == Material.FIREWORK_ROCKET) && (current.getItemMeta().getDisplayName().equals("§6Fountain Of Nation")))
			{
				Core.getWarpManager().teleportToWarp(player, "fountain");

			}

			else if ((current.getType() == Material.FIREWORK_ROCKET) && (current.getItemMeta().getDisplayName().equals("§6Illumination du sapin")))
			{

				Core.getWarpManager().teleportToWarp(player, "sapin");
			}
		}
	}


}

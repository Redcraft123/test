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
import fr.redcraft.minparc.Menu.Lands.DiscoverylandGUI;
import fr.redcraft.minparc.Menu.Lands.FrontierlandGUI;
import fr.redcraft.minparc.Menu.Lands.LibertySquareLandGUI;
import fr.redcraft.minparc.Menu.Lands.MainstreetGUI;
import fr.redcraft.minparc.Menu.Lands.RubixWorldGUI;



public class LandMinParcGUI implements Listener {


	public static void openMinparcInventory(Player player) {

		Inventory inv = Bukkit.createInventory(player, 54, "§7[§6Minparc§7] §dLands");
		inv.setItem(0, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1).build());
		inv.setItem(1, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1).build());
		inv.setItem(2, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1, 2).build());
		inv.setItem(3, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1, 2).build());
		inv.setItem(4, new ItemBuilder("Fantasyland", Material.PINK_SHULKER_BOX, 1).addLore("§aOuvert/Open").build());
		inv.setItem(5, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1, 2).build());
		inv.setItem(6, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1, 2).build());
		inv.setItem(7, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 5).build());
		inv.setItem(8, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 5).build());
		inv.setItem(9, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1).build());
		inv.setItem(10, new ItemBuilder("Liberty Square", Material.WHITE_CONCRETE, 1).addLore("§aOuvert/Open").build());
		inv.setItem(11, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1).build());
		inv.setItem(12, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1).addLore(" ").build());
		inv.setItem(13, new ItemBuilder(" ", Material.PURPLE_STAINED_GLASS_PANE, 1, 2).build());
		inv.setItem(14, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1).addLore(" ").build());
		inv.setItem(15, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 5).build());
		inv.setItem(16, new ItemBuilder("Rubik's World", Material.RED_CONCRETE, 1, 14).addLore("§aOuvert/Open").build());
		inv.setItem(17, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 5).build());
		inv.setItem(18, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1).build());
		inv.setItem(19, new ItemBuilder(" ", Material.WHITE_STAINED_GLASS_PANE, 1).build());
		inv.setItem(20, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(21, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(22, new ItemBuilder("MP Castle", Material.GOLD_BLOCK, 1).addLore("§aOuvert/Open").build());
		inv.setItem(23, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(24, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(25, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 5).build());
		inv.setItem(26, new ItemBuilder(" ", Material.LIME_STAINED_GLASS_PANE, 1, 5).build());
		inv.setItem(27, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(28, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(29, new ItemBuilder("Frontierland", Material.CACTUS, 1).addLore("§aOuvert/Open").build());
		inv.setItem(30, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(31, new ItemBuilder("Central Island", Material.SEA_LANTERN, 1).addLore("§aOuvert").build());
		inv.setItem(32, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(33, new ItemBuilder("Discoveryland", Material.CRAFTING_TABLE, 1).addLore("§aOuvert/Open").build());
		inv.setItem(34, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(36, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(37, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(38, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(39, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(40, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(41, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(42, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(43, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(44, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(45, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(46, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(47, new ItemBuilder(" ", Material.BROWN_STAINED_GLASS_PANE, 1, 12).build());
		inv.setItem(48, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(49, new ItemBuilder("Main Street", Material.WHITE_TERRACOTTA, 1).addLore("§aOuvert/Open").build());
		inv.setItem(50, new ItemBuilder(" ", Material.RED_STAINED_GLASS_PANE, 1, 14).build());
		inv.setItem(51, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(52, new ItemBuilder(" ", Material.YELLOW_STAINED_GLASS_PANE, 1, 4).build());
		inv.setItem(53, new ItemBuilder("§cRetour", Material.BARRIER, 1).build());
		player.openInventory(inv);
	}

	@EventHandler
	public void onInteract(InventoryClickEvent e)
	{
		Player player = (Player)e.getWhoClicked();
		String inv = e.getView().getTitle();
		ItemStack current = e.getCurrentItem();
		if ((inv != null) && (inv != null) && 
				(inv.equals("§7[§6Minparc§7] §dLands")))
		{
			if (current == null) {
				return;
			}
			e.setCancelled(true);
			if ((current.getType() == Material.WHITE_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{

				e.setCancelled(true);
			}

			else if ((current.getType() == Material.RED_STAINED_GLASS) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
			else if ((current.getType() == Material.YELLOW_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
			else if ((current.getType() == Material.LIME_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
			else if ((current.getType() == Material.BROWN_STAINED_GLASS_PANE) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				e.setCancelled(true);
			}
			
			else if ((current.getType() == Material.WHITE_CONCRETE) && (current.getItemMeta().getDisplayName().equals("Liberty Square")))
			{
				LibertySquareLandGUI.openFrontierInventory(player);
			}
			else if ((current.getType() == Material.BARRIER) && (current.getItemMeta().getDisplayName().equals("§cRetour")))
			{
				MinparcMainInventory.openMinparcInventory(player);
			}
			else if ((current.getType() == Material.WHITE_TERRACOTTA) && (current.getItemMeta().getDisplayName().equals("Main Street")))
			{
				MainstreetGUI.openMSInventory(player);
			}
			else if ((current.getType() == Material.SEA_LANTERN) && (current.getItemMeta().getDisplayName().equals("Central Island")))
			{
				player.sendMessage("§eAucune attraction n'est présente dans ce land");
			}
			
			else if ((current.getType() == Material.GOLD_BLOCK) && (current.getItemMeta().getDisplayName().equals("MP Castle")))
			{
				player.sendMessage("§eAucune attraction n'est présente dans ce land");
			}



			else if ((current.getType() == Material.CACTUS) && (current.getItemMeta().getDisplayName().equals("Frontierland")))
			{
				FrontierlandGUI.openFrontierInventory(player);
			}


			else if ((current.getType() == Material.STICK) && (current.getItemMeta().getDisplayName().equals("§cAucun event en cours...")))
			{


			}

			else if ((current.getType() == Material.CRAFTING_TABLE) && (current.getItemMeta().getDisplayName().equals("Discoveryland")))
			{
				DiscoverylandGUI.openDiscoInventory(player);
			}

			else if ((current.getType() == Material.RED_CONCRETE) && (current.getItemMeta().getDisplayName().equals("Rubik's World")))
			{
				RubixWorldGUI.openRubixInventory(player);
			}
		}

	}
}




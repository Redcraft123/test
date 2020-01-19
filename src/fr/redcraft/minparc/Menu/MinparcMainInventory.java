package fr.redcraft.minparc.Menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.redcraft.api.Coin.CoinAPI;
import fr.redcraft.api.Items.ItemBuilder;


public class MinparcMainInventory implements Listener {

	public static void openMinparcInventory(Player player) {

		Inventory inv = Bukkit.createInventory(player, 27, "§3MagicBand " + player.getName());




		ItemStack head1 = new ItemStack(Material.PLAYER_HEAD, 1);         
		SkullMeta head1m = (SkullMeta) head1.getItemMeta();   
		head1m.setDisplayName("§bProfil: " + player.getName());
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§aPseudo: §2" + player.getName());
		lore.add("§aArgent: §2" + CoinAPI.getCoins(null, player));
		//	lore.add("§aLevel: §2" + LevelSystem.getLevel(player.getName()));
		//lore.add("§aXP: §2" + LevelSystem.getXP(player.getName()));
		lore.add("§aEntrée:§2 Soon");
		head1m.setLore(lore);
		head1m.setOwningPlayer(player);
		head1.setItemMeta(head1m);         
		inv.setItem(4, head1);


		inv.setItem(11, new ItemBuilder("§aShow/spectacle", Material.FIREWORK_ROCKET, 1, 15)

				.addLore(" ")
				.addLore("     §fEnvie de découvrir nos spectacles ?")
				.addLore("          §fAlors ouvrez le menu !")
				.build());




		inv.setItem(13, new ItemBuilder("§3MinParc Disneyland", Material.MINECART, 1, 15)

				.addLore(" ")
				.addLore("§f         Envie de connaître le status d'un Land ?")
				.addLore("                 §fAlors ouvrez le menu !")
				.build());


		inv.setItem(15, new ItemBuilder("§6Shop", Material.GOLD_INGOT, 1, 15)

				.addLore(" ")
				.addLore("         §f Envie d'un grade VIP ?")
				.addLore("    §fDécouvrez comment faire en cliquant  !")
				.build());


		inv.setItem(22, new ItemBuilder("§aAchivements", Material.BOOK, 1, 15)

				.addLore(" ")
				.addLore("    §dEnvie de gagner de l'argent ? ")
				.addLore("       alors faites les Achivements !!")
				.build());  



		player.openInventory(inv);
	}

	@EventHandler
	public void onInteract(InventoryClickEvent event)
	{
		Player player = (Player)event.getWhoClicked();
		String inv = event.getView().getTitle();

		ItemStack current = event.getCurrentItem();
		if ((inv != null) && (inv != null) && 
				(inv.equals("§3MagicBand " + player.getName())))
		{
			if (current == null) {
				return;
			}
			event.setCancelled(true);
			if ((current.getType() == Material.PLAYER_HEAD) && (current.getItemMeta().getDisplayName().equals(" ")))
			{
				event.setCancelled(true);
			}

			else if ((current.getType() == Material.FIREWORK_ROCKET) && (current.getItemMeta().getDisplayName().equals("§aShow/spectacle")))
			{

			}

			else if ((current.getType() == Material.MINECART) && (current.getItemMeta().getDisplayName().equals("§3MinParc Disneyland")))
			{
				LandMinParcGUI.openMinparcInventory(player);
			}
			else if ((current.getType() == Material.GOLD_INGOT) && (current.getItemMeta().getDisplayName().equals("§6Shop")))
			{
				event.setCancelled(true);
			}

			else if ((current.getType() == Material.GOLD_BLOCK) && (current.getItemMeta().getDisplayName().equals("§6MenuVIP")))
			{
				event.setCancelled(true);
			}

			else if ((current.getType() == Material.BOOK) && (current.getItemMeta().getDisplayName().equals("§aAchivements")))
			{

				event.setCancelled(true);

			}

			else if ((current.getType() == Material.APPLE) && (current.getItemMeta().getDisplayName().equals("§aRolePlay")))
			{
				event.setCancelled(true);
			}
		}

	}
}



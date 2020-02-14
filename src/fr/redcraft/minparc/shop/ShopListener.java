package fr.redcraft.minparc.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import fr.redcraft.api.Coin.CoinAPI;
import fr.redcraft.minparc.functions.User;

public class ShopListener implements Listener {
	

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		List<String> shopList = new ArrayList<String>();
		if(e.getRightClicked().getType() == EntityType.VILLAGER) {
			Villager v = (Villager) e.getRightClicked();
			e.setCancelled(true);

			//CHECKEN NACH DEN SHOPS

			for(ShopEnums shop : ShopEnums.values()) {
				boolean isnew = true;
				for(Integer i = 0; i < shopList.size(); i++) {
					if(shop.getShopName() == shopList.get(i)) {
						isnew = false;
					}
				}
				if(isnew == true) {
					shopList.add(shop.getShopName());
				}
			}

			//INVENTARGRÖßE BERECHNEN
			Integer invCount = 0;
			Integer invSize = 0;
			for(Integer i = 0; i < shopList.size(); i++) {
				if(v.getCustomName() != null) {
					if(v.getCustomName().equalsIgnoreCase(shopList.get(i)))	{
						for(ShopEnums shop : ShopEnums.values())	{
							if (shop.getShopName() == shopList.get(i))	{
								if(shop.getSlot() >= invCount)
									invCount = shop.getSlot();
							}
							boolean passt = false;
							while(passt == false) {
								if(9*invSize > invCount) {
									passt = true;
								} else {
									invSize += 1;
								}
							}
						}
					}
				}
			}
			//SHOPINVENTAR ERSTELLEN
			for(Integer i = 0; i < shopList.size(); i++)  {
				if(v.getCustomName() != null) {
					if(v.getCustomName().equalsIgnoreCase(shopList.get(i)))	{
						Inventory inv = Bukkit.createInventory(null, 9 * invSize, shopList.get(i));
						for(ShopEnums shop : ShopEnums.values()) {
							if (shop.getShopName() == shopList.get(i))	{
								ItemStack shopIS = new ItemStack(shop.getMaterial());
								ItemMeta im = shopIS.getItemMeta();
								
								
								ArrayList<String> lore = new ArrayList<>();
								lore.add("§6Price: " + shop.getMoney());
								if(PlayerConfig.hasItemInv(e.getPlayer(), shop)) {
									lore.add(">>§cBOUGHT");
								}
								im.setLore(lore);
								shopIS.setItemMeta(im);


								inv.setItem(shop.getSlot(), shopIS);
							}
						}
						e.getPlayer().openInventory(inv);
					}
				}
			}
		}
	}

	@EventHandler
	public void onINVclick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		
		for(ShopEnums shop : ShopEnums.values()) {
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(shop.getName())) {
							PlayerConfig.giveItemInv(p.getPlayer(), shop);
						ItemStack BoughtItem = new ItemStack(shop.getMaterial(), 1);
						ItemMeta im = BoughtItem.getItemMeta();
						im.setDisplayName(shop.getName());
						if(shop.getColor().getBlue() != 20)	{
							LeatherArmorMeta meta1 = (LeatherArmorMeta)im;
								meta1.setColor(shop.getColor());
								BoughtItem.setItemMeta(meta1);
							}
							BoughtItem.setItemMeta(im);
							p.getInventory().addItem(BoughtItem);
							p.closeInventory();
							p.sendMessage("§aYou bought: §6" + shop.getName());
						

					
						
					p.closeInventory();
				}
				e.setCancelled(true);
			}
		
	
	}
}


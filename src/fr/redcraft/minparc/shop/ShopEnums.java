package fr.redcraft.minparc.shop;

import org.bukkit.Color;
import org.bukkit.Material;

public enum ShopEnums {
	SabreLaser("Emporium", 1, "Sabrelaser", Material.STICK, 0, "Gadgets", 0, 0, 20),
	PaintGun("Emporium", 2, "PaintGun", Material.STONE, 0, "Gadgets", 0, 0, 20);


	
	String shopname;
	int slot;
	String name;
	Material material;
	int money;
	String type;
	int R;
	int G;
	int B;

	private ShopEnums(String shopname,int slot, String name, Material material, int money, String type, int R, int G, int B) {
		this.shopname = shopname;
		this.slot = slot;
		this.name = name;
		this.material = material;
		this.money = money;
		this.type = type;
		this.R = R;
		this.G = G;
		this.B = B;
	}
	
	public String getShopName() {
		return shopname;
	}
	
	public int getSlot() {
		return slot;
	}
	
	public String getName() {
		return name;
	}
	
	public Material getMaterial() {
		return material;
	}	
	
	public int getMoney() {
		return money;
	}	
	public String getType() {
		return type;
	}
	
	public Color getColor() {
		return Color.fromRGB(R, G, B);
	}
}
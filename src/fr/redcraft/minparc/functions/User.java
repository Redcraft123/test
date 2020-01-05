package fr.redcraft.minparc.functions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.data.SpawnData;
import fr.redcraft.minparc.data.UserData;
import fr.redcraft.minparc.utils.Date;
import fr.redcraft.minparc.utils.LocationUtils;

import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle.EnumTitleAction;



public class User extends UserData {

	public static HashMap<String, User> userhash = new HashMap<String, User>();
	public static BanList bannedPlayers = Bukkit.getServer().getBanList(BanList.Type.NAME);

	private String user;
	private boolean vanish;
	private boolean tptoggle;
	private boolean god;
	private String playerReply;
	private Location loc;
	private Player tparequest;
	private boolean tpa;
	private String requestType;
	private Date joinDate;

	public User(String name) {
		super(name);
		this.user = name;
		this.vanish = false;
		this.tptoggle = false;
		this.god = false;
		this.playerReply = null;
		SpawnData spawn = new SpawnData();
		if (spawn.thereIsSpawn()) {
			this.loc = spawn.getSpawn();
		} else {
			this.loc = null;
		}
		this.tpa = false;
		this.tparequest = null;
		this.requestType = null;
		this.setJoinDate(new Date());
	}

	@SuppressWarnings("deprecation")
	public boolean hasItems(int number, Material type, int data) {
		int cont = 0;
		for (ItemStack item : this.getPlayer().getInventory().getContents()) {
			if (item == null)
				continue;
			if (item.getType().equals(type) && item.getData().getData() == data) {
				cont += item.getAmount();
			}
		}
		if (cont >= number)
			return true;
		return false;
	}

	@SuppressWarnings("deprecation")
	public void removeItems(int number, Material type, int data) {
		int cont = 0;
		for (ItemStack item : this.getPlayer().getInventory().getContents()) {
			if (item == null)
				continue;
			if (item.getType().equals(type) && item.getData().getData() == data) {
				cont += item.getAmount();
			}
		}
		if (cont == number) {
			for (int i = 0; i < this.getPlayer().getInventory().getSize(); i++) {
				if (this.getPlayer().getInventory().getItem(i) == null)
					continue;
				if (this.getPlayer().getInventory().getItem(i).getType().equals(type)
						&& this.getPlayer().getInventory().getItem(i).getData().getData() == data) {
					this.getPlayer().getInventory().setItem(i, null);
				}
			}
			this.getPlayer().updateInventory();
			return;
		}

		if (cont > number) {
			for (int i = 0; i < this.getPlayer().getInventory().getSize(); i++) {
				if (this.getPlayer().getInventory().getItem(i) == null)
					continue;
				if (this.getPlayer().getInventory().getItem(i).getType().equals(type)
						&& this.getPlayer().getInventory().getItem(i).getData().getData() == data) {
					this.getPlayer().getInventory().setItem(i, null);
				}
			}
			ItemStack item = new ItemStack(type, cont - number, (short) data);
			this.getPlayer().getInventory().addItem(item);
			this.getPlayer().updateInventory();
			return;
		}
	}

	public void launchFireBall() {
		this.getPlayer().launchProjectile(Fireball.class);
	}

	public boolean hasNightVision() {
		return this.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION);
	}

	public void setNightVision(boolean night) {
		if (!night && this.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION))
			this.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
		else if (night && !this.getPlayer().hasPotionEffect(PotionEffectType.NIGHT_VISION))
			this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));
	}

	public List<String> getNearPlayers(int radius) {
		List<String> list = new ArrayList<String>();
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (p.getName().equals(this.user))
				continue;
			if (p.getLocation().distance(this.getPlayer().getLocation()) <= radius)
				list.add(p.getName());
		}
		return list;
	}

	public boolean hasEmptyInventory() {
		boolean t = false;
		for (ItemStack item : this.getPlayer().getInventory().getContents()) {
			if (item != null && item.getType() != Material.AIR)
				t = true;
		}
		if (!t)
			return true;
		return false;
	}

	public void setJoinDate() {
		this.joinDate = new Date();
	}

	public boolean isOnline() {
		return Bukkit.getServer().getPlayer(this.user) != null;
	}

	public Player getPlayer() {
		return Bukkit.getServer().getPlayer(this.user);
	}

	@SuppressWarnings("deprecation")
	public OfflinePlayer getOffline() {
		return Bukkit.getServer().getOfflinePlayer(this.user);
	}

	public void setGamemode(GameMode gm) {
		this.getPlayer().setGameMode(gm);
	}

	public boolean hasFly() {
		return this.getPlayer().getAllowFlight();
	}

	public void setFly(boolean fly) {
		this.getPlayer().setAllowFlight(fly);
	}

	public boolean hasVanish() {
		return this.vanish;
	}

	@SuppressWarnings("deprecation")
	public void setVanish(boolean vanish) {
		this.vanish = vanish;
		if (!vanish) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.showPlayer(this.getPlayer());
			}
		} else {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.hidePlayer(this.getPlayer());
			}
		}
	}



	public void addItem(ItemStack item) {
		HashMap<Integer, ItemStack> leftOver = new HashMap<Integer, ItemStack>();
		leftOver.putAll((this.getPlayer().getInventory().addItem(item)));

		if (!leftOver.isEmpty()) {
			Location loc = this.getPlayer().getLocation();
			this.getPlayer().getWorld().dropItem(loc, item);
		}
	}

	public int getExp() {
		return this.getPlayer().getTotalExperience();
	}

	public void setExp(int exp) {
		this.getPlayer().setTotalExperience(exp);
	}

	public boolean isToggled() {
		return this.tptoggle;
	}

	public void setToggled(boolean tp) {
		this.tptoggle = tp;
	}


	public boolean teleported(Location loc) {
		if (this.tptoggle)
			return false;
		User.getUser(this.user).saveLastLocation();
		this.getPlayer().teleport(loc);
		
		
		return true;
	}

	public void setDay() {
		getPlayer().setPlayerTime(0L, true);
	}
	
	public void setNight() {
		getPlayer().setPlayerTime(14000L, true);
	}

	public void setHealth(double h) {
		this.getPlayer().setHealth(h);
	}

	public void onTpall() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (User.getUser(p.getName()).isToggled())
				continue;
			p.teleport(this.getPlayer());
		}
	}


	public String getName() {
		return this.user;
	}

	public boolean isGod() {
		return this.god;
	}

	public void setGod(boolean god) {
		this.god = god;
	}

	public void newUser() throws IOException {
		if (!fileExists()) {

			super.yml.createNewFile();
			super.get().createSection("userdata");
			super.get().set("userdata.username", this.user);
			super.get().set("userdata.UUID", this.getPlayer().getUniqueId().toString());
			super.get().set("userdata.IP",
					this.getPlayer().getAddress().getHostString() + ":" + this.getPlayer().getAddress().getPort());
			super.get().set("userdata.firstlogin",
					LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/"
							+ LocalDate.now().getYear() + " " + LocalTime.now().getHour() + ":"
							+ LocalTime.now().getMinute());
			super.get().set("userdata.others-ips", new ArrayList<String>());
			super.save();
		}
	}


	@SuppressWarnings("deprecation")
	public static void vanish(Player p) {
		for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
			if (User.getUser(pl.getName()).hasVanish()) {
				p.hidePlayer(pl);
			}
		}
	}

	public static User getUser(String user) {
		return userhash.get(user.toLowerCase());
	}



	@SuppressWarnings("deprecation")
	public void setItem(ItemStack item) {
		this.getPlayer().getInventory().setItemInHand(item);
		this.getPlayer().updateInventory();
	}

	public void setItem(ItemStack item, int pos) {
		this.getPlayer().getInventory().setItem(pos, item);
		this.getPlayer().updateInventory();
	}

	@SuppressWarnings("deprecation")
	public ItemStack getItem() {
		return this.getPlayer().getItemInHand();
	}

	public ItemStack getItem(int pos) {
		return this.getPlayer().getInventory().getItem(pos);
	}

	public void clear() {
		this.getPlayer().getInventory().clear();
		this.getPlayer().getInventory().setChestplate(null);
		this.getPlayer().getInventory().setBoots(null);
		this.getPlayer().getInventory().setLeggings(null);
		this.getPlayer().getInventory().setHelmet(null);
		this.getPlayer().updateInventory();
	}

	public void sendMessage(String str) {
		this.getPlayer().sendMessage(str);
	}

	public boolean isUntouch() {
		return this.getPlayer() != null && this.getPlayer().hasPermission("main.untouchable");
	}

	public void feed() {
		this.getPlayer().setFoodLevel(20);
	}

	public void heal() {
		this.getPlayer().setHealth(20);
		this.getPlayer().setFoodLevel(20);
	}



	public void removeHat() {
		this.addItem(this.getPlayer().getInventory().getHelmet());
		this.getPlayer().getInventory().setHelmet(null);
		this.getPlayer().updateInventory();
	}

	public void setWalkSpeed(int val) {

		float v = 0;

		switch (val) {

		case 0:
			v = (float) 0.0;
			break;
		case 1:
			v = (float) 0.2;
			break;
		case 2:
			v = (float) 0.3;
			break;
		case 3:
			v = (float) 0.4;
			break;
		case 4:
			v = (float) 0.5;
			break;
		case 5:
			v = (float) 0.6;
			break;
		case 6:
			v = (float) 0.7;
			break;
		case 7:
			v = (float) 0.8;
			break;
		case 8:
			v = (float) 0.9;
			break;
		case 9:
			v = (float) 0.95;
			break;
		case 10:
			v = (float) 1.0;
			break;
		default:
			v = (float) 0.2;
			break;
		}

		this.getPlayer().setWalkSpeed(v);
	}

	public void setFlySpeed(int val) {

		float v = 0;

		switch (val) {

		case 0:
			v = (float) 0.0;
			break;
		case 1:
			v = (float) 0.2;
			break;
		case 2:
			v = (float) 0.3;
			break;
		case 3:
			v = (float) 0.4;
			break;
		case 4:
			v = (float) 0.5;
			break;
		case 5:
			v = (float) 0.6;
			break;
		case 6:
			v = (float) 0.7;
			break;
		case 7:
			v = (float) 0.8;
			break;
		case 8:
			v = (float) 0.9;
			break;
		case 9:
			v = (float) 0.95;
			break;
		case 10:
			v = (float) 1.0;
			break;
		default:
			v = (float) 0.2;
			break;
		}

		this.getPlayer().setFlySpeed(v);
	}

	public void openCraft() {
		this.getPlayer().openWorkbench(this.getPlayer().getLocation(), true);
	}

	public Player getReply() {
		return Bukkit.getPlayer(this.playerReply);
	}

	public void setReply(String name) {
		this.playerReply = name;
	}

	public void saveLastLocation() {
		this.loc = this.getPlayer().getLocation();
	}

	public Location getLastLocation() {
		return this.loc;
	}

	public boolean hasTpaRequest() {
		return this.tparequest != null;
	}

	public boolean askTpa() {
		return this.tpa;
	}

	public void setRequest(Player from) {
		this.tparequest = from;
	}

	public void setSendTpa(boolean tpa) {
		this.tpa = tpa;
	}

	public Player getRequest() {
		return this.tparequest;
	}

	public void setRequestType(int type) {
		switch (type) {
		case 0:
			this.requestType = "TPA";
			break;
		case 1:
			this.requestType = "TPAHERE";
			break;
		default:
			this.requestType = null;
			break;
		}
	}

	public String getRequestType() {
		return this.requestType;
	}


	public void saveQuitLocation() {
		LocationUtils loc = new LocationUtils(this.getPlayer().getLocation());
		super.get().set("userdata.lastlocation", loc.toString() + " " + loc.getWorld());
		super.save();
	}




	public static void sendAllMessage(String str) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(str);
		}
	}
	
	  public void sendTitle(Player player,String title,int fadein,int stay,int fadeout) {
	        PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.TITLE,ChatSerializer.a("{\"text\": \""+title+"\"}"),fadein*20,stay*20,fadeout*20);
	        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	    }
	   
	    public void sendSubtitle(Player player,String subtitle,int fadein,int stay,int fadeout) {
	        PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,ChatSerializer.a("{\"text\": \""+subtitle+"\"}"),fadein*20,stay*20,fadeout*20);
	        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	    }
	   
	    public void sendFullTitle(Player player,String title,String subtitle,int fadein,int stay,int fadeout) {
	        sendTitle(player, title, fadein, stay, fadeout);
	        sendSubtitle(player, subtitle, fadein, stay, fadeout);
	    }

	
	
	    




	public static List<String> getUserList() {
		List<String> fileList = new ArrayList<String>();
		File userdir = new File(Core.getInstance().getDataFolder() + "/data/users/");
		for (File f : userdir.listFiles()) {
			fileList.add(f.getName().replaceAll(".yml", ""));
		}
		return fileList;
	}

	public static List<User> getUsers() {
		List<User> fileList = new ArrayList<User>();
		File userdir = new File(Core.getInstance().getDataFolder() + "/data/users/");
		for (File f : userdir.listFiles()) {
			fileList.add(new User(f.getName().replaceAll(".yml", "")));
		}
		return fileList;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}


}
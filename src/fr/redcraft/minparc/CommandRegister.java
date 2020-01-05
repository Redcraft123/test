package fr.redcraft.minparc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import fr.redcraft.minparc.commands.AutoMessageCMD;
import fr.redcraft.minparc.commands.GamemodeCMD;
import fr.redcraft.minparc.commands.SpawnCMD;
import fr.redcraft.minparc.commands.TimeCMD;
import fr.redcraft.minparc.commands.UtilityCMD;
import fr.redcraft.minparc.commands.WarpCMD;



public class CommandRegister {

	CommandMap commandMap;
	Field knownCommands;

	public CommandRegister() {
		this.commandMap = null;
		try {
			final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			f.setAccessible(true);
			this.commandMap = (CommandMap) f.get(Bukkit.getServer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getPluginCommands() {
		List<String> cmdlist = new ArrayList<String>();
		
		for (String cmd : GamemodeCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		
		for (String cmd : WarpCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		
		for (String cmd : SpawnCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		
		for (String cmd : TimeCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		
		for (String cmd : UtilityCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}

		for (String cmd : AutoMessageCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		
		return cmdlist;
	}

	private PluginCommand[] getCommands() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<PluginCommand> list = new ArrayList<PluginCommand>();
		List<String> cmdlist = new ArrayList<String>();
		
		for (String cmd : GamemodeCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		

		for (String cmd : WarpCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		for (String cmd : SpawnCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		
		for (String cmd : TimeCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		
		for (String cmd : UtilityCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		for (String cmd : AutoMessageCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		for (String cmd : cmdlist) {

			if (isPluginCommand(cmd))
				continue;

			if (isBukkitCommand(cmd))
				removeDefault(cmd);

			for (String a : getAliases(cmd)) {
				if (isBukkitCommand(a))
					removeDefault(a);
			}

			PluginCommand pcommand;
			Constructor<PluginCommand> constructor;
			constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			constructor.setAccessible(true);
			pcommand = constructor.newInstance(cmd, Core.getInstance());
			list.add(pcommand);
			constructor.setAccessible(false);
		}
		for (PluginCommand cmd : list) {

			
			if (isCommandOf(GamemodeCMD.getCommands(), cmd)) {
				cmd.setExecutor(new GamemodeCMD());
				continue;
			}
			
			if (isCommandOf(WarpCMD.getCommands(), cmd)) {
				cmd.setExecutor(new WarpCMD());
				continue;
			}
			
			if (isCommandOf(SpawnCMD.getCommands(), cmd)) {
				cmd.setExecutor(new SpawnCMD());
				continue;
			}

			if (isCommandOf(TimeCMD.getCommands(), cmd)) {
				cmd.setExecutor(new TimeCMD());
				continue;
			}
			
			if (isCommandOf(UtilityCMD.getCommands(), cmd)) {
				cmd.setExecutor(new UtilityCMD());
				continue;
			}
			if (isCommandOf(AutoMessageCMD.getCommands(), cmd)) {
				cmd.setExecutor(new AutoMessageCMD());
				continue;
			}
			
		}
		return list.toArray(new PluginCommand[0]);

	}

	private boolean isCommandOf(String[] array, PluginCommand cmd) {
		int i = 0;
		boolean t = false;
		while (i < array.length && !t) {
			if (array[i].equals(cmd.getName())) {
				t = true;
			} else {
				i++;
			}
		}
		return t;
	}

	public void loadCommands() {
		try {
			for (PluginCommand cmd : getCommands()) {

				if (!getAliases(cmd.getName()).isEmpty()) {
					cmd.setAliases(getAliases(cmd.getName()));
				}

				this.commandMap.register("MinparcCore", cmd);
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	private List<String> getAliases(String cmd) {
		List<String> list = new ArrayList<String>();
		switch (cmd) {
		case "gmc":
			list.add("c");
			list.add("creative");
			break;
		case "gms":
			list.add("s");
			list.add("survival");
			break;
		case "gma":
			list.add("a");
			list.add("adventure");
			break;
		case "gmsp":
			list.add("sp");
			list.add("spectateur");
			break;
		case "gamemode":
			list.add("gm");
			break;
	
		case "vanish":
			list.add("v");
			break;
		case "item":
			list.add("i");
			break;
		case "enchant":
			list.add("enchantment");
			break;
		case "xp":
			list.add("exp");
			break;
		case "feed":
			list.add("eat");
			break;
		case "god":
			list.add("godmode");
			list.add("tgm");
			break;
		case "hat":
			list.add("head");
			break;
		case "walkspeed":
			list.add("ws");
			list.add("wspeed");
			break;
		case "flyspeed":
			list.add("fs");
			list.add("fspeed");
			break;
		case "repair":
			list.add("fix");
			break;
		case "workbench":
			list.add("craft");
			list.add("wb");
			list.add("wbench");
			break;
		case "me":
			list.add("description");
		break;
		case "invsee":
			list.add("inventorysee");
			list.add("invs");
			list.add("inventorys");
			list.add("isee");
			break;
		case "enderchest":
			list.add("endersee");
			list.add("enderchestsee");
			list.add("esee");
			list.add("enders");
			list.add("ec");
			break;
		case "broadcast":
			list.add("bc");
			list.add("broadc");
			list.add("bcast");
			break;
		case "getpos":
			list.add("pos");
			list.add("getp");
			break;
		case "thor":
			list.add("strike");
			list.add("linghting");
			break;
		case "helpstaff":
			list.add("hstaff");
			list.add("msgstaff");
			list.add("helps");
			list.add("msghelp");
			list.add("staffmessage");
			list.add("report");
			break;
		case "tell":
			list.add("m");
			list.add("msg");
			list.add("pm");
			list.add("whisper");
			list.add("w");
			list.add("playermessage");
			list.add("pmessage");
			list.add("pmsg");
			break;
		case "reply":
			list.add("r");
			break;
		case "tpaccept":
			list.add("tpyes");
			break;
		
		case "realname":
			list.add("realnick");
			list.add("real");
			list.add("name");
			break;
		case "socialspy":
			list.add("chatspy");
			list.add("spy");
			break;
		case "mail":
			list.add("email");
			break;
		case "spymail":
			list.add("spyemail");
			list.add("smail");
			list.add("adminmail");
			list.add("amail");
			break;
		case "adminhome":
			list.add("ahome");
			list.add("adminh");
			break;
		case "tempmute":
			list.add("tmute");
			break;
		case "tempban":
			list.add("tban");
			break;
		case "createjail":
			list.add("cjail");
			list.add("cj");
			list.add("createj");
			break;
		case "deljail":
			list.add("djail");
			list.add("dj");
			list.add("deletej");
			list.add("deletejail");
			break;
		case "lookup":
			list.add("look");
			list.add("checkban");
			list.add("checkmute");
			list.add("check");
			break;
		case "banip":
			list.add("ban-ip");
			list.add("ipban");
			break;
		case "unbanip":
			list.add("unban-ip");
			list.add("unipban");
			list.add("ipunban");
			break;
		case "seen":
			list.add("ip");
			list.add("iplookup");
			break;
		case "setlockaccount":
			list.add("setkeyword");
			break;
		case "unlockaccount":
			list.add("keyword");
			break;
		case "history":
			list.add("bandata");
			list.add("punish");
			list.add("punishdata");
			break;
		case "balance":
			list.add("money");
			list.add("bal");
			break;
		case "eco":
			list.add("economy");
			break;
		case "firstlogin":
			list.add("first");
			list.add("flogin");
			break;
		case "lastlocation":
			list.add("last");
			list.add("lastloc");
			break;
		case "kitcreator":
			list.add("kitc");
			list.add("kitcrea");
			break;
		case "ptime":
			list.add("playertime");
			break;
		case "nightvision":
			list.add("nv");
			list.add("nvision");
			break;
		case "fireball":
			list.add("fire");
			list.add("fball");
			break;
		default:
			return list;
		}
		return list;
	}

	private boolean isPluginCommand(String cmd) {
		if (this.commandMap.getCommand(cmd) == null)
			return false;
		boolean flag = false;
		for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
			if (this.commandMap.getCommand(p.getName() + ":" + cmd) != null) {
				if (p.getName().equals("WorldGuard") || p.getName().equals("WorldEdit")) {
					removeDefault(cmd);
					continue;
				} else {
					flag = true;
				}
			}
		}
		return flag;
	}

	private boolean isBukkitCommand(String cmd) {
		boolean flag = true;
		for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
			if (this.commandMap.getCommand(p.getName() + ":" + cmd) != null) {
				flag = false;
			}
		}
		return flag;
	}

	private void removeDefault(String cmd) {

		Field[] fs = SimpleCommandMap.class.getDeclaredFields();

		if ((Core.version == 13 && Core.release >= 2) || Core.version > 13) {
			try {
				checkCast(fs[0], cmd);
			} catch (ClassCastException e) {
				checkCast(fs[1], cmd);
			}
		} else
			checkCast(fs[1], cmd);

	}

	@SuppressWarnings("unchecked")
	private void checkCast(Field f, String cmd) throws ClassCastException {
		boolean b = f.isAccessible();
		f.setAccessible(true);
		Object result;
		try {
			result = f.get(this.commandMap);
			f.setAccessible(b);
			HashMap<String, Command> knownCommands = (HashMap<String, Command>) result;
			knownCommands.remove(cmd);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public List<String> getPluginsByCommand(String cmd) {
		List<String> list = new ArrayList<String>();
		for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
			if (this.commandMap.getCommand(p.getName() + ":" + cmd) != null) {
				list.add(p.getName());
			}
		}
		if (list.isEmpty() && this.commandMap.getCommand(cmd) != null) {

			if (getPluginCommands().contains(cmd))
				list.add("MinparcCore");

			if (this.commandMap.getCommand("bukkit:" + cmd) != null
					|| this.commandMap.getCommand("minecraft:" + cmd) != null)
				list.add("Bukkit");
		}
		return list;
	}

}
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

import fr.redcraft.minparc.commands.MinparcCMD;
import fr.redcraft.minparc.commands.AttractionCMD;
import fr.redcraft.minparc.commands.CoinsCMD;
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

		for (String cmd : MinparcCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		for (String cmd : AttractionCMD.getCommands()) {
			cmdlist.add(cmd);
			cmdlist.addAll(getAliases(cmd));
		}
		for (String cmd : CoinsCMD.getCommands()) {
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
		for (String cmd : MinparcCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		
		for (String cmd : AttractionCMD.getCommands()) {
			cmdlist.add(cmd);
		}
		
		for (String cmd : CoinsCMD.getCommands()) {
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
			if (isCommandOf(MinparcCMD.getCommands(), cmd)) {
				cmd.setExecutor(new MinparcCMD());
				continue;
			}
			
			if (isCommandOf(AttractionCMD.getCommands(), cmd)) {
				cmd.setExecutor(new AttractionCMD());
				continue;
			}
			
			if (isCommandOf(CoinsCMD.getCommands(), cmd)) {
				cmd.setExecutor(new CoinsCMD());
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
		
		case "vanish":
			list.add("v");
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
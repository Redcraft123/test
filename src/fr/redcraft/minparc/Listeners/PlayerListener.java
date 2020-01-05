package fr.redcraft.minparc.Listeners;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import fr.redcraft.minparc.Core;
import fr.redcraft.minparc.functions.User;
import fr.redcraft.minparc.utils.AFK;
import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event) throws IOException {
		Player player = event.getPlayer();
		User user = new User(player.getName());
		event.setJoinMessage("§8[§a+§8] " + user.getName() + " §5a rejoint MinParc§9 Resort");
		user.sendFullTitle(user.getPlayer(), "Bienvenue " + user.getName(), "sur MinParc Resort", 30, 30, 30);
		user.setHealth(20);
		if(!user.exists()) {
			user.newUser();
			user.save();
			Core.getInstance().getLogger().info("Création du fichier joueur de " + user.getName() + "...");
		}
		if(!player.hasPlayedBefore()) {

			User.sendAllMessage("§6Souhaitons la bienvenue à §e" + player.getName() + "§6 sur MinParc Resort ");
			if(!user.exists()) {
				user.newUser();
				Core.getInstance().getLogger().info("Création du fichier joueur de " + user.getName() + "...");

			}
		}
	}

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Player player = e.getPlayer();
		User user = new User(player.getName());
		user.saveLastLocation();
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		AFK afk = new AFK(event.getPlayer());
		if (!afk.isAfk()) {
			afk.autoAfk(event.getPlayer().getLocation());
			return;
		}
	}


	@EventHandler
	public void onLeft(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		User user = new User(player.getName());
		user.save();
		user.saveLastLocation();
		event.setQuitMessage("§8[§c-§8] " + user.getName() + " §5a quitté MinParc §9Resort");
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(player.hasPermission("MinParc.Colo")) {
			event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		}
	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String sender = player.getName();
		String message = event.getMessage();

		for(Player pls : Bukkit.getOnlinePlayers()) {
			if(pls.hasPermission("MinParc.SocialSpy")) {
				pls.sendMessage("§4" + sender + "§8§l>> §6" + message);
			}
		}
	}

	@EventHandler
	public void onTP(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		User user = new User(player.getName());
		user.saveLastLocation();
	}

}

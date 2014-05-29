package com.jumanjicraft.BungeeChatClient;

import com.dthielke.herochat.Channel;
import com.dthielke.herochat.Herochat;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeChatListener implements PluginMessageListener {
	
	static BungeeChatClient plugin = null;

	public BungeeChatListener(BungeeChatClient plugin) {
		BungeeChatListener.plugin = plugin;
		Bukkit.getMessenger().registerIncomingPluginChannel(plugin,	"BungeeChat", this);
		Bukkit.getMessenger().registerOutgoingPluginChannel(plugin,	"BungeeChat");
	}

	public static void TransmitChatMessage(String message, String chatchannel) {
		
		if (Bukkit.getOnlinePlayers().length == 0)
			return;
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF(chatchannel);
		out.writeUTF(message);
		Bukkit.getOnlinePlayers()[0].sendPluginMessage(plugin, "BungeeChat", out.toByteArray());
		
	}

	public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
		if (!s.equalsIgnoreCase("BungeeChat")) {
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
		String chatchannel = in.readUTF();
		String message = in.readUTF();
		Channel channel = Herochat.getChannelManager().getChannel(chatchannel);
		if (channel == null) {
			Bukkit.getLogger().log(Level.WARNING,"Channel {0} doesn't exist, but a message was receieved on it.  Your Herochat configs are probably not the same on each server.", chatchannel);
			return;
		}
		
		channel.sendRawMessage(ChatColor.DARK_GRAY + "<> " + ChatColor.RESET + message);
	}
}
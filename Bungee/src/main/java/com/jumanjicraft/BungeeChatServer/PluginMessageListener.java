package com.jumanjicraft.BungeeChatServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PluginMessageListener implements Listener {
	private BungeeChatServer plugin;

	public PluginMessageListener(BungeeChatServer plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void receievePluginMessage(PluginMessageEvent event)	throws IOException {
		
		if (!event.getTag().equalsIgnoreCase("BungeeChat")) {
			return;
		}

		InetSocketAddress addr = event.getSender().getAddress();
		Collection<ServerInfo> servers = this.plugin.getProxy().getServers().values();
		
		for (ServerInfo server : servers) {
			// Don't send the message back to the server that
			if (server.getAddress().equals(addr))
				continue;
			
			// Only send the message to a server if there are players on it
			if (server.getPlayers().size() != 0) {
				server.sendData("BungeeChat", event.getData());
			}
		}
	}
}
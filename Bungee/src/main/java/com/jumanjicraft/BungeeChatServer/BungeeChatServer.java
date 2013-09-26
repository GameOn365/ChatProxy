package com.jumanjicraft.BungeeChatServer;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeChatServer extends Plugin {

	private ProxyServer proxy = null;
	
	public void onEnable() {	
		proxy = getProxy();
		proxy.registerChannel("BungeeChat");
		proxy.getPluginManager().registerListener(this,	new PluginMessageListener(this));
	}

	public void onDisable() {
		proxy.unregisterChannel("BungeeChat");
	}
	
}
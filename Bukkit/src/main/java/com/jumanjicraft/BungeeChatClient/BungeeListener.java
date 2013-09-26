package com.jumanjicraft.BungeeChatClient;

import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Chatter.Result;
import com.dthielke.herochat.Herochat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class BungeeListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChannelChat(ChannelChatEvent event) {
		
		if (event.getResult() != Result.ALLOWED)
			return;
		
		if (event.getFormat().equalsIgnoreCase(Herochat.getChannelManager().getConversationFormat()))
			return;
		
		BungeeChatListener.TransmitChatMessage(
				ChatColor.RESET
						+ event.getSender().getPlayer()
								.getDisplayName()
						+ event.getChannel().getColor() + ": "
						+ event.getMessage(), event.getChannel()
						.getName());
	}
}
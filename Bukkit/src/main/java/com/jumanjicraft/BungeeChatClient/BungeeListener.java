package com.jumanjicraft.BungeeChatClient;

import com.dthielke.herochat.Channel;
import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Chatter.Result;
import com.dthielke.herochat.Herochat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class BungeeListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChannelChat(ChannelChatEvent event) {
		
		// We only care about allowed chat events
		if (event.getResult() != Result.ALLOWED)
			return;
		
		// We don't currently support PM's
		if (event.getFormat().equalsIgnoreCase(Herochat.getChannelManager().getConversationFormat()))
			return;
		
		Player player = event.getSender().getPlayer();
		Channel channel = event.getChannel();
		String format = channel.applyFormat(event.getFormat(), event.getBukkitFormat(), player);
		String chatMessage = String.format(format, new Object[] { player.getDisplayName(), event.getMessage() });
		
		BungeeChatListener.TransmitChatMessage(chatMessage, channel.getName());
	}
}
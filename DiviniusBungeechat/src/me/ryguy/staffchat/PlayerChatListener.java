package me.ryguy.staffchat;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;



public class PlayerChatListener
  implements Listener
{
  @EventHandler
  public void onPlayerChat(ChatEvent e) {
    if (!(e.getSender() instanceof ProxiedPlayer) || e.getMessage().startsWith("/"))
      return;  ProxiedPlayer sender = (ProxiedPlayer)e.getSender();
    if (e.getMessage().startsWith("@")) {
      if (!sender.hasPermission("adminc.staffchat"))
        return;  e.setCancelled(true);
      String format = StaffChat.getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", sender.getName());
      format = format.replace("%message%", e.getMessage());
      format = format.replace("@", "");
      for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(new TextComponent(StringUtils.colorize(format)));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
      return;
    } 
    if (StaffChat.getStaffChatPlayers().contains(sender.getName())) {
      e.setCancelled(true);
      String format = StaffChat.getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", sender.getName());
      format = format.replace("%message%", e.getMessage());
      for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(new TextComponent(StringUtils.colorize(format)));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
      return;
    } 
  }
}
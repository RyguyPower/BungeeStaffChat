package me.ryguy.staffchat;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;




public class StaffChatCommand
  extends Command
{
  public StaffChatCommand() { super("staffchat", "adminc.staffchat", new String[] { "sc" }); }


  
  public void execute(CommandSender sender, String[] args) {
    if (sender instanceof net.md_5.bungee.command.ConsoleCommandSender) {
      if (args.length == 0) {
        sender.sendMessage(new TextComponent("/staffchat <message>"));
        return;
      } 
      String message = String.join(" ", args);
      String format = StaffChat.getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", "CONSOLE");
      format = format.replace("%message%", message);
      for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(new TextComponent(StringUtils.colorize(format)));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
      return;
    } 
    if (!sender.hasPermission("adminc.staffchat")) {
      sender.sendMessage(new TextComponent(StringUtils.colorize("&cNo permission.")));
      return;
    } 
    if (args.length == 0) {
      if (!StaffChat.getStaffChatPlayers().contains(sender.getName())) {
        StaffChat.getStaffChatPlayers().add(sender.getName());
        String msg = StaffChat.getConfig().getString("staff-chat-toggle-on-msg");
        sender.sendMessage(new TextComponent(StringUtils.colorize(msg)));
      } else {
        StaffChat.getStaffChatPlayers().remove(sender.getName());
        String msg = StaffChat.getConfig().getString("staff-chat-toggle-off-msg");
        sender.sendMessage(new TextComponent(StringUtils.colorize(msg)));
      } 
    } else {
      String message = String.join(" ", args);
      String format = StaffChat.getConfig().getString("staff-chat-format");
      format = format.replace("%sender-name%", sender.getName());
      format = format.replace("%message%", message);
      
      for (ProxiedPlayer all : BungeeCord.getInstance().getPlayers()) {
        if (all.hasPermission("adminc.staffchat")) {
          all.sendMessage(new TextComponent(StringUtils.colorize(format)));
        }
      } 
      System.out.println(ChatColor.stripColor(StringUtils.colorize(format)));
    } 
  }
}
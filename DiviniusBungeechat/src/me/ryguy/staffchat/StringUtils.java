package me.ryguy.staffchat;

import net.md_5.bungee.api.ChatColor;


public class StringUtils
{
  public static String colorize(String input) { return ChatColor.translateAlternateColorCodes('&', input); }
}

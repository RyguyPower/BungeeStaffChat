package me.ryguy.staffchat;

import java.io.File;
import java.util.ArrayList;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;







public class StaffChat
  extends Plugin
{
  private static StaffChat instance;
  private static ArrayList<String> staffChatPlayers;
  private static Configuration config;
  
  public void onEnable() {
    getProxy().getPluginManager().registerListener(this, new PlayerChatListener());
    getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
    instance = this;
    staffChatPlayers = new ArrayList<String>();
    getLogger().info("Registered 1 command and 1 event!");
    try {
      if (!getDataFolder().exists()) {
        getDataFolder().mkdir();
      }
      File file = new File(getDataFolder(), "config.yml");
      
      if (!file.exists()) {
        getLogger().info("There was an error while creating the config.yml!");
      }

      
      config = ConfigurationProvider.getProvider(net.md_5.bungee.config.YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
      getLogger().info("Loaded the config.yml!");
    } catch (Exception ex) {
      getLogger().info("There was an error while loading the config.yml!");
      return;
    } 
    getLogger().info("Finished setting up!");
  }
  
  public static StaffChat getInstance() { return instance; }
  public static ArrayList<String> getStaffChatPlayers() { return staffChatPlayers; }
  public static Configuration getConfig() { return config; }
  
  public void saveConfig() {
    try {
      ConfigurationProvider.getProvider(net.md_5.bungee.config.YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
    } catch (Exception ex) {
      getLogger().info("There was an error while saving the config.yml!");
      return;
    } 
  }
}

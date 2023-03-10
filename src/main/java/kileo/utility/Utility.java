package kileo.utility;

import kileo.utility.commands.Fly;
import kileo.utility.commands.God;
import kileo.utility.listeners.PlayerInteract;
import kileo.utility.listeners.PlayerQuit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Utility extends JavaPlugin {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Register Events
        registerEvents();

        // Register Commands
        registerCommands();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.GOLD + "shutting down! Bye bye.");
    }

    public static Plugin getPlugin() { return plugin; }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
    }

    private void registerCommands() {
        PluginCommand fly = this.getCommand("fly");
        if (fly != null) fly.setExecutor(new Fly());

        PluginCommand god = this.getCommand("god");
        if (god != null) god.setExecutor(new God());
    }
}
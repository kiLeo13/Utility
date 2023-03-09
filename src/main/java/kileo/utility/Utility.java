package kileo.utility;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import kileo.utility.commands.Fly;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Utility extends JavaPlugin {
    private static Plugin plugin;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        // Register Events
        registerEvents();
        registerProtocolLibListeners();

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
    }

    private void registerCommands() {
        PluginCommand fly = this.getCommand("fly");
        if (fly != null) fly.setExecutor(new Fly());
    }

    private void registerProtocolLibListeners() {
        protocolManager.addPacketListener(new PlayerDoubleJump(this));
    }
}
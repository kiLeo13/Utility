package kileo.utility;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import org.bukkit.plugin.Plugin;

public class PlayerDoubleJump extends PacketAdapter {

    public PlayerDoubleJump(Plugin plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.PLAYER_INFO);
    }
}
package kileo.utility.commands;

import kileo.utility.util.Completers;
import kileo.utility.util.Message;
import kileo.utility.util.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class God implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
            return true;
        }

        /* God yourself */
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
                return true;
            }

            Player player = (Player) sender;
            boolean isGod = player.isInvulnerable();

            player.setInvulnerable(!isGod);
            player.sendRichMessage(godMessage(player));

            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            sender.sendRichMessage(Message.HELP_COMMAND_GOD.get());
            return true;
        }

        /* God Others */
        if (args.length == 1) {
            if (!sender.hasPermission(Permission.COMMAND_GOD_ALL.get()) && !args[0].equals(sender.getName())) {
                sender.sendRichMessage(Message.ERROR_COMMAND_NO_PERMISSION.get());
                return true;
            }

            if (args[0].equalsIgnoreCase("*")) {
                Collection<? extends Player> players = Bukkit.getOnlinePlayers();

                players.forEach(player -> {
                    boolean isGod = player.isInvulnerable();

                    player.sendRichMessage(godMessage(player));
                    player.setInvulnerable(!isGod);
                });

                sender.sendRichMessage("<green>Toggled god for every online player!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendRichMessage(Message.ERROR_PLAYER_NOT_FOUND.get());
                return true;
            }

            boolean isGod = target.isInvulnerable();

            target.setInvulnerable(!isGod);

            target.sendRichMessage(targetGodMessage(target));
            sender.sendRichMessage(senderGodMessage(target));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return Completers.suggestPlayers(args[0], false, true, true);

        return new ArrayList<>();
    }

    private String godMessage(Player player) {
        return player.isInvulnerable()
                ? "<green>God has been enabled."
                : "<red>God has been disabled.";
    }

    private String targetGodMessage(Player target) {
        return target.isInvulnerable()
                ? "<green>God has been enabled."
                : "<red>God has been disabled";
    }

    private String senderGodMessage(Player target) {
        return target.isInvulnerable()
                ? "<green>God for <gold>" + target.getName() + "<green> has been enabled."
                : "<red>God for <gold>" + target.getName() + "<red> has been disabled";
    }
}
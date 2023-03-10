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

public class Fly implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
            return true;
        }

        /* Fly yourself */
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
                return true;
            }

            Player player = (Player) sender;
            boolean canFly = player.getAllowFlight();

            player.setAllowFlight(!canFly);

            player.sendRichMessage(flyMessage(player));

            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            sender.sendRichMessage(Message.HELP_COMMAND_FLY.get());
            return true;
        }

        /* Fly Others */
        if (args.length == 1) {
            if (!sender.hasPermission(Permission.COMMAND_FLY_ALL.get()) && !args[0].equals(sender.getName())) {
                sender.sendRichMessage(Message.ERROR_COMMAND_NO_PERMISSION.get());
                return true;
            }

            if (args[0].equalsIgnoreCase("*")) {
                Collection<? extends Player> players = Bukkit.getOnlinePlayers();

                players.forEach(player -> {
                    boolean canFly = player.getAllowFlight();

                    player.sendRichMessage(flyMessage(player));
                    player.setAllowFlight(!canFly);
                });

                sender.sendRichMessage("<green>Toggled fly for every online player!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendRichMessage(Message.ERROR_PLAYER_NOT_FOUND.get());
                return true;
            }

            boolean targetCanFly = target.getAllowFlight();

            target.setAllowFlight(!targetCanFly);

            target.sendRichMessage(targetFlyMessage(target));
            sender.sendRichMessage(senderFlyMessage(target));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return Completers.suggestPlayers(args[0], false, true, true);

        return new ArrayList<>();
    }

    private String flyMessage(Player player) {
        return player.getAllowFlight()
                ? "<green>Fly has been enabled."
                : "<red>Fly has been disabled.";
    }

    private String targetFlyMessage(Player target) {
        return target.getAllowFlight()
                ? "<green>Fly has been enabled."
                : "<red>Fly has been disabled";
    }

    private String senderFlyMessage(Player target) {
        return target.getAllowFlight()
                ? "<green>Fly for <gold>" + target.getName() + "<green> has been enabled."
                : "<red>Fly for <gold>" + target.getName() + "<red> has been disabled.";
    }
}
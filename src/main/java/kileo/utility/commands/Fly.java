package kileo.utility.commands;

import kileo.utility.util.Message;
import kileo.utility.util.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
import java.util.stream.Collectors;

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
            sender.sendRichMessage(Message.COMMAND_FLY_HELP.get());
            return true;
        }

        /* Fly Others */
        if (args.length == 1) {
            OfflinePlayer offTarget = Bukkit.getOfflinePlayer(args[0]);

            if (offTarget.getPlayer() == null) {
                sender.sendRichMessage(Message.ERROR_PLAYER_NOT_FOUND.get());
                return true;
            }

            if (!sender.hasPermission(Permission.COMMAND_FLY_OTHERS.get()) && !args[0].equals(sender.getName())) {
                sender.sendRichMessage(Message.ERROR_COMMAND_PERMISSION.get());
                return true;
            }

            Player target = offTarget.getPlayer();
            boolean targetCanFly = target.getAllowFlight();

            target.setAllowFlight(!targetCanFly);

            target.sendRichMessage(targetFlyMessage(target));
            sender.sendRichMessage(senderFlyMessage(target));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return firstTabComplete(args[0]);

        return new ArrayList<>();
    }

    private List<String> firstTabComplete(String arg) {
        List<String> suggestion = new ArrayList<>();
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        players.forEach(player -> suggestion.add(player.getName()));
        suggestion.add("help");

        return suggestion.stream()
                .filter(e -> e.toLowerCase()
                .startsWith(arg))
                .collect(Collectors.toList());
    }

    private String flyMessage(Player player) {
        return player.getAllowFlight()
                ? "<green>Fly enabled."
                : "<red>Fly disabled.";
    }

    private String targetFlyMessage(Player target) {
        return target.getAllowFlight()
                ? "<green>Fly enabled."
                : "<red>Fly disabled";
    }

    private String senderFlyMessage(Player target) {
        return target.getAllowFlight()
                ? "<green>Fly for <gold>" + target.getName() + "<green> has been enabled."
                : "<red>Fly for <gold>" + target.getName() + "<red> has been disabled.";
    }
}
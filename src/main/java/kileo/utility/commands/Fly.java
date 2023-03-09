package kileo.utility.commands;

import kileo.utility.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Fly implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
            return true;
        }

        if (args.length == 0) {
            sender.sendRichMessage(Message.COMMAND_FLY_INCORRECT_USAGE.get());
            return true;
        }

        /* Fly yourself */
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
                return true;
            }

            Player player = (Player) sender;
            boolean canFly = player.getAllowFlight();

            player.setAllowFlight(!canFly);
            player.sendRichMessage("<green>Fly enabled.");

            return true;
        }

        if (args.length == 2) {

        }




        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return null;

        return new ArrayList<>();
    }
}
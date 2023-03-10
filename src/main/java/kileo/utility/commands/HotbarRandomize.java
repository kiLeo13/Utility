package kileo.utility.commands;

import kileo.utility.Utility;
import kileo.utility.util.Completers;
import kileo.utility.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HotbarRandomize implements TabExecutor {
    private final Utility plugin;

    public HotbarRandomize(Utility plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendRichMessage(Message.COMMAND_SENDER_NOT_ALLOWED.get());
            return true;
        }

        if (args.length != 3 || args[0].equalsIgnoreCase("help")) {
            sender.sendRichMessage(Message.HELP_COMMAND_HOTBAR_RANDOMIZE.get());
            return true;
        }

        int duration;
        int frequency;
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null && !args[0].equals("*")) {
            sender.sendRichMessage(Message.ERROR_PLAYER_NOT_FOUND.get());
            return true;
        }

        try {
            duration = Integer.parseInt(args[1]);
            frequency = Integer.parseInt(args[2]);

            if (duration < 1 || duration > 60) throw new NumberFormatException("Duration cannot be less than 1 or greater than 60");
            if (frequency < 0 || frequency > 5) throw new NumberFormatException("Frequency cannot be less than 0 or greater than 5");

        } catch (NumberFormatException e) {
            sender.sendRichMessage(Message.EXCEPTION_NUMBER.get());
            e.printStackTrace();

            return true;
        }

        Timer timer = new Timer(duration, frequency, target);
        timer.runTaskTimer(plugin, 0, 0);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return Completers.suggestPlayers(args[0], false, true, true);
        if (args[0].equalsIgnoreCase("help")) return new ArrayList<>();

        // Duration
        if (args.length == 2) return Completers.suggestNumbers(1, 60, args[1]);

        // Frequency
        if (args.length == 3) return Completers.suggestNumbers(0, 5, args[2]);

        return new ArrayList<>();
    }

    private static class Timer extends BukkitRunnable {
        private int count;
        private final int duration;
        private final int frequency;
        private final Player randomizingPlayer;
        private final Collection<? extends Player> players;

        private Timer(int duration, int frequency, @Nullable Player player) {
            this.count = 0;
            this.duration = duration * 20;
            this.frequency = frequency * 20;
            this.randomizingPlayer = player;

            this.players = Bukkit.getOnlinePlayers();
        }

        @Override
        public void run() {
            if (count == duration-1) {
                cancel();
            }

            if (frequency == 0 || (count % frequency) == 0) {
                if (randomizingPlayer != null) randomizingPlayer.getInventory().setHeldItemSlot(randomNumber());
                else players.forEach(player -> player.getInventory().setHeldItemSlot(randomNumber()));
            }

            count++;
        }

        private int randomNumber() {
            return (int) Math.floor(Math.random() * 9);
        }
    }
}
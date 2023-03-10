package kileo.utility.commands;

import kileo.utility.util.Completers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HotBarRandomize implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {














        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) return Completers.suggestPlayers(args[0], false, true);
        if (args.length == 2) return randomizeDuration(args[1]);
    }

    private List<String> randomizeDuration(String arg) {
        List<String> suggestion = new ArrayList<>();

        

        return suggestion;
    }
}
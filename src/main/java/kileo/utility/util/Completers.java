package kileo.utility.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Completers {
    private static Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
    private static OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();

    private Completers() {}

    public static List<String> suggestPlayers(String arg, boolean includeOffline, boolean includeEveryone, boolean includeHelp) {
        updatePlayers();
        List<String> suggest = new ArrayList<>();

        if (includeOffline) {
            for (OfflinePlayer i : offlinePlayers) suggest.add(i.getName());
        } else {
            for (Player i : onlinePlayers) suggest.add(i.getName());
        }

        if (includeHelp) suggest.add("help");
        if (includeEveryone) suggest.add("*");

        return suggest.stream()
                .filter(i -> i.toLowerCase(Locale.ROOT).startsWith(arg.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<String> suggestNumbers(int starting, int ending, String arg) {
        List<String> suggestion = new ArrayList<>();
        int i = starting;

        for (; i <= ending; i++)
            suggestion.add(String.valueOf(i));

        return suggestion.stream()
                .filter(f -> f.startsWith(arg))
                .collect(Collectors.toList());
    }

    private static void updatePlayers() {
        onlinePlayers = Bukkit.getOnlinePlayers();
        offlinePlayers = Bukkit.getOfflinePlayers();
    }
}
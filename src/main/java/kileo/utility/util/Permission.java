package kileo.utility.util;

public enum Permission {
    COMMAND_FLY_ALL("utility.fly.all"),
    COMMAND_GOD_ALL("utility.god.all");

    final String permission;

    Permission(String permission) { this.permission = permission; }

    public String get() { return permission; }
}
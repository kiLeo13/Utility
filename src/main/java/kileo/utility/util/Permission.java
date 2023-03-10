package kileo.utility.util;

public enum Permission {
    COMMAND_FLY_OTHERS("utility.fly.others");

    final String permission;

    Permission(String permission) { this.permission = permission; }

    public String get() { return permission; }
}
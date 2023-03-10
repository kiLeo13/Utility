package kileo.utility.util;

public enum Message {
    ERROR_COMMAND_PERMISSION("<red>You do not have permission to use this command."),
    ERROR_PLAYER_NOT_FOUND("<red>Player not found."),

    COMMAND_SENDER_NOT_ALLOWED("<red>You cannot run this command"),

    COMMAND_FLY_HELP("<green>Check command usage: <yellow>/fly <gold>[<yellow>player<gold>]");

    final String message;

    Message(String message) { this.message = message; }

    public String get() { return message; }
}
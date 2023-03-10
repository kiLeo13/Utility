package kileo.utility.util;

public enum Message {
    EXCEPTION_NUMBER("<red>Invalid number input, please enter a valid one. Check console for errors."),

    ERROR_COMMAND_NO_PERMISSION("<red>You do not have permission to use this command."),
    ERROR_PLAYER_NOT_FOUND("<red>Player not found."),

    COMMAND_SENDER_NOT_ALLOWED("<red>You cannot run this command."),

    HELP_COMMAND_FLY("<green>Check command usage: <yellow>/fly <gold>[<yellow>player<gold>]"),
    HELP_COMMAND_GOD("<green>Check command usage: <yellow>/fly <gold>[<yellow>player<gold>]"),
    HELP_COMMAND_HOTBAR_RANDOMIZE("<green>Check command usage: <yellow>/randomhotbar <gold><<yellow>player<gold>> <<yellow>duration<gold>> <<yellow>frequency<gold>>");

    final String message;

    Message(String message) { this.message = message; }

    public String get() { return message; }
}
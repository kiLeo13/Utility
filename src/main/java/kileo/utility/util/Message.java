package kileo.utility.util;

public enum Message {
    COMMAND_SENDER_NOT_ALLOWED("<red>You cannot run this command"),

    COMMAND_FLY_INCORRECT_USAGE("<red>Incorrect usage! See: <yellow>/fly <gold>[<yellow>player<gold>]");

    final String message;

    Message(String message) { this.message = message; }

    public String get() { return message; }
}
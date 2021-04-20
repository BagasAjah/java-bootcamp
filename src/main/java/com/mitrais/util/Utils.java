package com.mitrais.util;

public class Utils {
    public final static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "clear").inheritIO().start().waitFor();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}

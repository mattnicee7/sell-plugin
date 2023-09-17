package com.github.mattnicee7.sellplugin.util.chat;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}

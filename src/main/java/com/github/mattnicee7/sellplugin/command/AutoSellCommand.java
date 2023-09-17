package com.github.mattnicee7.sellplugin.command;

import com.github.mattnicee7.sellplugin.SellPlugin;
import com.github.mattnicee7.sellplugin.util.chat.ChatUtils;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AutoSellCommand implements CommandExecutor {

    private final SellPlugin plugin;

    public AutoSellCommand(SellPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("autosell").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender)
            return false;

        val player = (Player) sender;

        if (!plugin.getConfig().getBoolean("auto-sell")) {
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("system-unavailable"))
            );
            return false;
        }

        if (!(player.hasPermission("sellplugin.autosell"))) {
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("no-permission"))
            );
            return false;
        }

        if (plugin.getSellManager().getAutoSellPlayers().contains(player)) {
            plugin.getSellManager().getAutoSellPlayers().remove(player);
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("auto-sell-disabled"))
            );
        } else {
            plugin.getSellManager().getAutoSellPlayers().add(player);
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("auto-sell-activated"))
            );
        }

        return false;
    }

}

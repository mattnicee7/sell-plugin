package com.github.mattnicee7.sellplugin.command;

import com.github.mattnicee7.sellplugin.SellPlugin;
import com.github.mattnicee7.sellplugin.util.chat.ChatUtils;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ShiftSellCommand implements CommandExecutor {

    private final SellPlugin plugin;

    public ShiftSellCommand(SellPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("shiftsell").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender)
            return false;

        val player = (Player) sender;

        if (!plugin.getConfig().getBoolean("shift-sell")) {
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("system-unavailable"))
            );
            return false;
        }

        if (!(sender.hasPermission("sellplugin.shiftsell"))) {
            sender.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("no-permission"))
            );
            return false;
        }

        if (plugin.getSellManager().getShiftPlayers().contains(player)) {
            plugin.getSellManager().getShiftPlayers().remove(player);
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("shift-sell-disabled"))
            );
        } else {
            plugin.getSellManager().getShiftPlayers().add(player);
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("shift-sell-activated"))
            );
        }

        return false;
    }

}


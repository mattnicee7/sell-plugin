package com.github.mattnicee7.sellplugin.command;

import com.cryptomorin.xseries.messages.ActionBar;
import com.github.mattnicee7.sellplugin.SellPlugin;
import com.github.mattnicee7.sellplugin.util.chat.ChatUtils;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SellCommand implements CommandExecutor {

    private final SellPlugin plugin;

    public SellCommand(SellPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("sell").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender)
            return false;

        val player = (Player) sender;

        if (!(player.hasPermission("sellplugin.sell"))) {
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("no-permission"))
            );
            return false;
        }

        if (!(plugin.getSellManager().contains(player.getInventory()))) {
            ActionBar.sendActionBar(
                    player,
                    ChatUtils.colorize(plugin.getConfig().getString("you-dont-have-item-to-sell"))
            );
            return false;
        }

        double bonusPercentage = 0;

        val bonusOptional = player.getEffectivePermissions()
                .stream()
                .filter(it -> it.getPermission().startsWith("sellplugin.bonus"))
                .mapToDouble(it -> Double.parseDouble(it.getPermission().split("\\.")[2]))
                .max();

        if (bonusOptional.isPresent())
            bonusPercentage = bonusOptional.getAsDouble();

        plugin.getSellManager().sellItems(player, bonusPercentage);

        return false;
    }

}

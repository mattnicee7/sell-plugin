package com.github.mattnicee7.sellplugin.task;

import com.github.mattnicee7.sellplugin.SellPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSellTask extends BukkitRunnable {

    private final SellPlugin plugin;

    public AutoSellTask(SellPlugin plugin) {
        this.plugin = plugin;
        this.runTaskTimerAsynchronously(plugin, 20, plugin.getConfig().getInt("auto-sell-delay"));
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!plugin.getSellManager().getAutoSellPlayers().contains(player))
                continue;

            Bukkit.dispatchCommand(player, "sell");
        }
    }

}


package com.github.mattnicee7.sellplugin.listener;

import com.github.mattnicee7.sellplugin.SellPlugin;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ShiftSellListener implements Listener {

    private final SellPlugin plugin;

    public ShiftSellListener(SellPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        val player = event.getPlayer();

        if (!(plugin.getSellManager().getShiftPlayers().contains(player)))
            return;

        Bukkit.dispatchCommand(player, "sell");
    }

}

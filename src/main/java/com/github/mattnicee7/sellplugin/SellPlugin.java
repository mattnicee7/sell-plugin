package com.github.mattnicee7.sellplugin;

import com.github.mattnicee7.sellplugin.command.AutoSellCommand;
import com.github.mattnicee7.sellplugin.command.SellCommand;
import com.github.mattnicee7.sellplugin.command.ShiftSellCommand;
import com.github.mattnicee7.sellplugin.listener.ShiftSellListener;
import com.github.mattnicee7.sellplugin.manager.SellManager;
import com.github.mattnicee7.sellplugin.task.AutoSellTask;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SellPlugin extends JavaPlugin {

    @Getter
    private SellManager sellManager;

    @Getter
    private Economy economy = null;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            Bukkit.getConsoleSender().sendMessage("Economy plugin not found. Disabling this plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }

        this.loadConfig();

        this.sellManager = new SellManager(this);

        new AutoSellTask(this);

        this.registerCommands();
        this.registerEvents();

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;

        final RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;

        economy = rsp.getProvider();
        return economy != null;
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        this.reloadConfig();
    }

    private void registerCommands() {
        new SellCommand(this);
        new ShiftSellCommand(this);
        new AutoSellCommand(this);
    }

    private void registerEvents() {
        new ShiftSellListener(this);
    }

}

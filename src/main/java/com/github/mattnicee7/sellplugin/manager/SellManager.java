package com.github.mattnicee7.sellplugin.manager;

import com.cryptomorin.xseries.messages.ActionBar;
import com.github.mattnicee7.sellplugin.SellPlugin;
import com.github.mattnicee7.sellplugin.model.ItemSell;
import com.github.mattnicee7.sellplugin.util.chat.ChatUtils;
import lombok.Getter;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SellManager {

    private final SellPlugin plugin;

    @Getter
    private final Set<ItemSell> itemSells = new HashSet<>();

    @Getter
    private final List<Player> shiftPlayers = new ArrayList<>();

    @Getter
    private final List<Player> autoSellPlayers = new ArrayList<>();

    public SellManager(SellPlugin plugin) {
        this.plugin = plugin;

        this.loadItems();
    }

    private void loadItems() {
        itemSells.clear();
        val section = plugin.getConfig().getConfigurationSection("items");

        for (String key : section.getKeys(false)) {
            val itemSection = section.getConfigurationSection(key);

            this.itemSells.add(
                    new ItemSell(
                            new ItemStack(Material.getMaterial(itemSection.getInt("id")), 1, (short) itemSection.getInt("data")),
                            itemSection.getDouble("price")
                    ));

        }

    }

    public boolean contains(PlayerInventory inventory) {
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null || itemStack.getType() == Material.AIR)
                continue;


            for (ItemSell itemSell : itemSells) {
                if (itemSell.getItemStack().getType() == itemStack.getType() && itemSell.getItemStack().getData().getData() == itemStack.getData().getData())
                    return true;
            }
        }

        return false;
    }

    public void sellItems(Player player, double bonusPercentage) {
        int totalAmount = 0;
        double totalPrice = 0;

        for (ItemSell itemSell : itemSells) {
            int amount = 0;
            int count = 0;

            for (ItemStack itemStack : player.getInventory()) {
                count++;
                if (itemStack == null || itemStack.getType() == Material.AIR)
                    continue;

                if (itemStack.getType() == itemSell.getItemStack().getType() && itemSell.getItemStack().getData().getData() == itemStack.getData().getData()) {
                    amount += itemStack.getAmount();
                    totalAmount += itemStack.getAmount();
                    player.getInventory().setItem(count - 1, null);
                }
            }

            totalPrice += amount * itemSell.getPrice();

        }

        ActionBar.sendActionBar(
                player,
                ChatUtils.colorize(plugin.getConfig().getString("sell-message")
                        .replace("{amount}", String.valueOf(totalAmount))
                        .replace("{price}", String.format("%.2f", totalPrice)))
        );

        double bonusValue = 0;

        if (bonusPercentage > 0) {
            bonusValue = totalPrice * bonusPercentage / 100;
            player.sendMessage(
                    ChatUtils.colorize(plugin.getConfig().getString("bonus-message")
                            .replace("{percentage}", String.format("%.2f", bonusPercentage))
                            .replace("{price}", String.format("%.2f", bonusValue)))
            );
        }

        plugin.getEconomy().depositPlayer(player, totalPrice + bonusValue);
    }

}

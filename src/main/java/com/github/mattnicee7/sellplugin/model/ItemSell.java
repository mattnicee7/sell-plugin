package com.github.mattnicee7.sellplugin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class ItemSell {

    private ItemStack itemStack;
    private Double price;

}

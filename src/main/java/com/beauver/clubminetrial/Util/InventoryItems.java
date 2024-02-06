package com.beauver.clubminetrial.Util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.w3c.dom.CDATASection;
import xyz.xenondevs.inventoryaccess.component.ComponentWrapper;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.builder.SkullBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.util.MojangApiUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryItems {

    public static ItemStack playerSkull(String playerName){
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        skullMeta.displayName(Component.text(playerName));
        skullItem.setItemMeta(skullMeta);
        skullMeta.setCustomModelData(1);
        return skullItem;
    }

    public static Item staffSkull(String playerName, String rank, String dateJoined) throws MojangApiUtils.MojangApiException, IOException {
        return new SimpleItem(new SkullBuilder(playerName)
                .setDisplayName(playerName)
                .addLoreLines("Rank: " + rank)
                .addLoreLines("Date Hired: " + dateJoined));
    }

    public static ItemStack playerStats(String playerName){
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        skullMeta.displayName(Component.text("Player Stats"));
        skullMeta.setCustomModelData(1);
        skullItem.setItemMeta(skullMeta);
        return skullItem;
    }

    public static ItemStack socialItem(){
        ItemStack item = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Socials"));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack staffItem(){
        ItemStack item = new ItemStack(Material.OAK_SIGN);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Staff"));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemProvider getStatItem(String statName, int statistic, Material material){
        ItemProvider itemProvider = new ItemBuilder(material)

                .setDisplayName(statName)
                .addLoreLines("- " + statistic)
                .setCustomModelData(1);
        return itemProvider;
    }

    public static ItemProvider getStatItem(String statName, String statistic, Material material){

        ItemProvider itemProvider = new ItemBuilder(material)
                .setDisplayName(statName)
                .addLoreLines("- " + statistic)
                .setCustomModelData(1);
        return itemProvider;
    }

}

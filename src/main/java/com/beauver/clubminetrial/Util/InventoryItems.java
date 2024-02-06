package com.beauver.clubminetrial.Util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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

    public static ItemStack playerStats(String playerName){
        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
        skullMeta.displayName(Component.text("Player Stats"));
        skullMeta.setCustomModelData(1);
        skullItem.setItemMeta(skullMeta);
        return skullItem;
    }

    public static ItemStack discordItem(){
        ItemStack item = new ItemStack(Material.LIGHT_BLUE_DYE);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Discord"));
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

    public static ItemStack closeGui(){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Close"));
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        return item;
    }

}

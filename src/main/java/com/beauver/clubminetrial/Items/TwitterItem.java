package com.beauver.clubminetrial.Items;

import com.beauver.clubminetrial.ClubmineTrial;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class TwitterItem extends AbstractItem {


    final String twitter = ClubmineTrial.getPlugin().getConfig().getString("twitterLink");
    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.BLACK_DYE).setDisplayName("Twitter (X)").setCustomModelData(1);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        event.getInventory().close();
        event.getWhoClicked().sendRichMessage(String.format("<blue>You can check out our twitter here:<br><click:open_url:%1$s>%1$s</click></blue>",twitter));
    }

}

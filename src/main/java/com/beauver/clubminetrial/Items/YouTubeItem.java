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

public class YouTubeItem extends AbstractItem {

    final String yt = ClubmineTrial.getPlugin().getConfig().getString("youtubeLink");

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.RED_DYE).setDisplayName("YouTube").setCustomModelData(1);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        event.getInventory().close();
        event.getWhoClicked().sendRichMessage(String.format("<red>You can check out our YouTube here:<br><click:open_url:%1$s>%1$s</click></red>",yt));
    }

}

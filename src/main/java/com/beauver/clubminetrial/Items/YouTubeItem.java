package com.beauver.clubminetrial.Items;

import com.beauver.clubminetrial.Clubmine_Trial;
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

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.RED_DYE).setDisplayName("YouTube").setCustomModelData(1);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        event.getInventory().close();
        Clubmine_Trial plugin = Clubmine_Trial.getPlugin();
        event.getWhoClicked().sendMessage(Component.text("You can check out our youtube here:\n" + plugin.getConfig().getString("youtubeLink")).color(TextColor.fromHexString(plugin.getConfig().getString("textColorHex"))).clickEvent(ClickEvent.openUrl(plugin.getConfig().getString("youtubeLink"))));
    }

}

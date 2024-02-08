package com.beauver.clubminetrial.Items;

import com.beauver.clubminetrial.ClubmineTrial;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class DiscordItem extends AbstractItem {

    private final String discordInvite = ClubmineTrial.getPlugin().getConfig().getString("discordInviteCode");

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.LIGHT_BLUE_DYE).setDisplayName("Discord").setCustomModelData(1);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        event.getInventory().close();
        //minimessage
        event.getWhoClicked().sendRichMessage(String.format("<blue>Join our discord server here:<br><click:open_url:%1$s>%1$s</click></blue>",discordInvite));
    }
}

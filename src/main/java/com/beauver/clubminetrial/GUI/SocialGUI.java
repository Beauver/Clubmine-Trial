package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.Items.DiscordItem;
import com.beauver.clubminetrial.Items.StoreItem;
import com.beauver.clubminetrial.Items.TwitterItem;
import com.beauver.clubminetrial.Items.YouTubeItem;
import com.beauver.clubminetrial.Util.InventoryItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public class SocialGUI {

    public void openSocials(Player player){
        //Creates the GuiBuilder for a normal GUI
        Gui gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# D . T . Y . S #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('D', new DiscordItem())
                .addIngredient('T', new TwitterItem())
                .addIngredient('Y', new YouTubeItem())
                .addIngredient('S', new StoreItem())
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Clubmine's Socials")
                .setGui(gui)
                .build();
        window.open();
    }
}

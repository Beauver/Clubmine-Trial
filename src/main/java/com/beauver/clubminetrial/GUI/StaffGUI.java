package com.beauver.clubminetrial.GUI;

import com.beauver.clubminetrial.ClubmineTrial;
import com.beauver.clubminetrial.Items.BackItem;
import com.beauver.clubminetrial.Items.ForwardItem;
import com.beauver.clubminetrial.Util.InventoryItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.Item;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.util.MojangApiUtils;
import xyz.xenondevs.invui.window.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffGUI {

    public static void openStaffGUI(Player player) throws MojangApiUtils.MojangApiException, IOException {

        List<?> rawList = ClubmineTrial.getPlugin().getConfig().getList("staffMembers");
        List<Item> staffMembers = new ArrayList<>();

        if (rawList != null) {
            for (Object obj : rawList) {
                if (obj instanceof String) {
                    String staffMemberString = (String) obj;
                    String[] parts = staffMemberString.split(", ");

                    // Ensure that the array has at least three parts
                    if (parts.length >= 3) {
                        String username = parts[0];
                        String rank = parts[1];
                        String hiredDate = parts[2];
                        staffMembers.add(InventoryItems.staffSkull(username, rank, hiredDate));
                    }
                } else {
                    System.out.println("Unexpected type found in staffMembers list: " + obj.getClass().getSimpleName());
                }
            }
        }

        // create the gui
        Gui gui = PagedGui.items()
                .setStructure(
                        "# # # # # # # # #",
                        "# x x x x x x x #",
                        "# x x x x x x x #",
                        "# # # < # > # # #")
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('<', new BackItem())
                .addIngredient('>', new ForwardItem())
                .setContent(staffMembers)
                .build();

        Window window = Window.single()
                .setViewer(player)
                .setTitle("Staff Members")
                .setGui(gui)
                .build();

        window.open();
    }

}

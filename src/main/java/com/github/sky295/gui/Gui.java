package com.github.sky295.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Gui {

    public static void CreateInv(Inventory inv) {
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setDurability((short) 15);
        meta.setDisplayName("§b§l边框");
        itemStack.setItemMeta(meta);
        int[] glass = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        for (int i : glass) {
            inv.setItem(i, itemStack);
        }
        ItemStack itemStack1 = new ItemStack(Material.WOOL);
        ItemMeta meta1 = itemStack1.getItemMeta();
        itemStack1.setDurability((short) 4);
        meta1.setDisplayName("§6强化");
        itemStack1.setItemMeta(meta1);
        inv.setItem(39, itemStack1);
        itemStack1.setDurability((short) 3);
        meta1.setDisplayName("§b保存/使用垫子");
        itemStack1.setItemMeta(meta1);
        inv.setItem(13, itemStack1);
        itemStack1.setDurability((short) 14);
        meta1.setDisplayName("§c强突");
        itemStack1.setItemMeta(meta1);
        inv.setItem(41, itemStack1);
        itemStack.setDurability((short) 4);
        meta.setDisplayName("§b§l左边放材料，右边放装备，中间放巴尔克斯的忠告");
        itemStack.setItemMeta(meta);
        int[] Glass = {10, 11, 12, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 29, 30, 32, 33, 37, 38, 40, 42, 43};
        for (int j : Glass) {
            inv.setItem(j, itemStack);
        }
    }

    public static void createGui(Inventory inv) {
        ItemStack itemStack = new ItemStack(Material.valueOf("BLACK_STAINED_GLASS_PANE"));
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§b§l边框");
        itemStack.setItemMeta(meta);
        int[] glass = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};
        for (int i : glass) {
            inv.setItem(i, itemStack);
        }

        itemStack = new ItemStack(Material.valueOf("YELLOW_WOOL"));
        meta = itemStack.getItemMeta();
        meta.setDisplayName("§6强化");
        itemStack.setItemMeta(meta);
        inv.setItem(39, itemStack);

        itemStack = new ItemStack(Material.valueOf("BLUE_WOOL"));
        meta = itemStack.getItemMeta();
        meta.setDisplayName("§b保存/使用垫子");
        itemStack.setItemMeta(meta);
        inv.setItem(13, itemStack);

        itemStack = new ItemStack(Material.valueOf("RED_WOOL"));
        meta = itemStack.getItemMeta();
        meta.setDisplayName("§c强突");
        itemStack.setItemMeta(meta);
        inv.setItem(41, itemStack);

        itemStack = new ItemStack(Material.valueOf("YELLOW_STAINED_GLASS_PANE"));
        meta = itemStack.getItemMeta();
        meta.setDisplayName("§b§l左边放材料，右边放装备，中间放巴尔克斯的忠告");
        itemStack.setItemMeta(meta);
        int[] Glass = {10, 11, 12, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 29, 30, 32, 33, 37, 38, 40, 42, 43};
        for (int j : Glass) {
            inv.setItem(j, itemStack);
        }
    }
}

package com.github.sky295.util;

import com.github.sky295.BlackDesertPro;
import com.github.sky295.api.Context;
import com.github.sky295.data.Setting;
import com.github.sky295.i18n.BKI18n;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class OtherUtil {
    public static String equipmentType(ItemStack itemStack) {
        int EquId = itemStack.getTypeId();
        List<Integer> WeaponId = BlackDesertPro.instance.getConfig().getIntegerList("BlackDesertPro.Type.Weapon");
        List<Integer> ArromId = BlackDesertPro.instance.getConfig().getIntegerList("BlackDesertPro.Type.Arrom");
        for (Iterator Iterator = WeaponId.iterator(); Iterator.hasNext(); ) {
            int Weapon = (int) Iterator.next();
            if (EquId == Weapon) {
                return "Weapon";
            }
        }
        for (Iterator Iterator = ArromId.iterator(); Iterator.hasNext(); ) {
            int Arrom = (int) Iterator.next();
            if (EquId == Arrom) {
                return "Arrom";
            }
        }
        return null;
    }

    public static String equipmentType1(ItemStack itemStack) {
        String EnglistType = String.valueOf(itemStack.getType());
        List<String> WeaponId = BlackDesertPro.instance.getConfig().getStringList("BlackDesertPro.EnglishType.Weapon");
        List<String> ArromId = BlackDesertPro.instance.getConfig().getStringList("BlackDesertPro.EnglishType.Arrom");
        for (Iterator Iterator = WeaponId.iterator(); Iterator.hasNext(); ) {
            String Weapon = (String) Iterator.next();
            if (EnglistType.equals(Weapon)) {
                return "Weapon";
            }
        }
        for (Iterator Iterator = ArromId.iterator(); Iterator.hasNext(); ) {
            String Arrom = (String) Iterator.next();
            if (EnglistType.equals(Arrom)) {
                return "Arrom";
            }
        }
        return null;
    }

    public static void addLore(List<String> loreList, String startLore, String endLore,
                               List<String> LevelString, ItemMeta meta, ItemStack itemStack, int level) {
        loreList.add(startLore);
        loreList.addAll(LevelString);
        loreList.add(BKI18n.get("level", new Locale(Util.langPerfix(), Util.langSu())) + level);
        loreList.add(endLore);
        meta.setLore(loreList);
        itemStack.setItemMeta(meta);
    }

    public static void addlore(ItemMeta meta, List<String> LevelString, int level, ItemStack itemStack, Player player) {
        if (!meta.hasLore()) {
            LevelString.add(0, Setting.startLore());
            LevelString.add(BKI18n.get("level", new Locale(Util.langPerfix(), Util.langSu())) + level);
            LevelString.add(LevelString.size(), Setting.endLore());
            meta.setLore(LevelString);
            itemStack.setItemMeta(meta);
            if (Setting.isBroadcastdmsg(level)) {
                OtherUtil.broadcastMsg(meta, itemStack, player, level);
            } else {
                player.sendMessage(BKI18n.get("SuccessMessage", new Locale(Util.langPerfix(), Util.langSu())));
            }
            if (level == 1) {
                return;
            }
        }
        OtherUtil.lore(meta, LevelString, level, itemStack, player);
    }

    public static void setlore(ItemMeta meta, List<String> LevelString, int level, ItemStack itemStack, Player player) {
        List<String> list = meta.getLore();
        if (level <= 1) {
            for (String str : list) {
                if (str.contains(BKI18n.get(Context.MAT, new Locale(Util.langPerfix(), Util.langSu())))) {
                    int dzIndex = BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())).length();
                    int index = Integer.parseInt(str.substring(dzIndex));
                    list.remove(list.indexOf(BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + index));
                    meta.setLore(list);
                    itemStack.setItemMeta(meta);
                    break;
                }
            }
            OtherUtil.qh(Setting.isBroardMsg(level), list, Setting.startLore(), Setting.endLore(), LevelString, meta, itemStack, player, level);
        }
        if (level >= Context.TWO) {
            int x = list.indexOf(Setting.endLore()) - list.indexOf(Setting.startLore()) + 1;
            int y = list.indexOf(Setting.startLore());
            for (int i = 0; i < x; i++) {
                list.remove(y);
            }
            OtherUtil.qh(Setting.isBroardMsg(level), list, Setting.startLore(), Setting.endLore(), LevelString, meta, itemStack, player, level);
        }
    }

    public static void lore(ItemMeta meta, List<String> LevelString, int level, ItemStack itemStack, Player player) {
        List<String> list = meta.getLore();
        int x = list.indexOf(Setting.endLore()) - list.indexOf(Setting.startLore()) + 1;
        int y = list.indexOf(Setting.startLore());
        if (level == 1) {
            OtherUtil.qh(Setting.isBroadcastdmsg(level), list, Setting.startLore(), Setting.endLore(), LevelString, meta, itemStack, player, level);
        }
        if (level >= Context.TWO) {
            for (int i = 0; i < x; i++) {
                list.remove(y);
            }
            OtherUtil.qh(Setting.isBroadcastdmsg(level), list, Setting.startLore(), Setting.endLore(), LevelString, meta, itemStack, player, level);
        }
    }

    public static void qh(boolean isBroadcastdmsg, List<String> loreList, String startLore, String endLore,
                          List<String> LevelString, ItemMeta meta, ItemStack itemStack, Player player, int level) {
        if (isBroadcastdmsg) {
            OtherUtil.addLore(loreList, startLore, endLore, LevelString, meta, itemStack, level);
            OtherUtil.broadcastMsg(meta, itemStack, player, level);
        } else {
            OtherUtil.addLore(loreList, startLore, endLore, LevelString, meta, itemStack, level);
            player.sendMessage(BKI18n.get("SuccessMessage", new Locale(Util.langPerfix(), Util.langSu())));
        }
    }

    public static void dz(ItemMeta meta, ItemStack itemStack) {
        if (Util.faillevel(meta) - 1 >= Setting.maxconunt(Util.sucesslevel(meta))) {
            return;
        }
        if (Util.sucesslevel(meta) >= Context.SEVEN) {
            List<String> list = meta.getLore();
            int index = list.indexOf(Setting.endLore());
            if (list.contains(BKI18n.get(Context.MAT, new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) - 1))) {
                if (Util.level(meta) >= Context.FIFTEEN) {
                    if (Util.level(meta) == Context.FIFTEEN) {
                        list.set(index - 1, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) + 1));
                    } else {
                        list.set(index - 1, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) + Setting.ExtraBalks(Util.level(meta))));
                    }
                } else {
                    list.set(index - 1, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + Util.faillevel(meta));
                }
            } else {
                if (Util.level(meta) >= Context.FIFTEEN) {
                    if (Util.level(meta) == Context.FIFTEEN) {
                        list.add(index, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) + 1));
                    }else {
                        list.add(index, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) + Setting.ExtraBalks(Util.level(meta))));
                    }

                } else {
                    list.add(index, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + Util.faillevel(meta));
                }
            }
            meta.setLore(list);
            itemStack.setItemMeta(meta);
        }
    }

    public static void setSafeAmount(Inventory inventory, int level) {
        ItemStack itemStack = inventory.getItem(28);
        int amount = itemStack.getAmount();
        itemStack.setAmount(amount - Setting.safeStone(level));
    }

    public static void setPuremagicBlackstoneAmount(Inventory inventory, int level) {
        ItemStack itemStack = inventory.getItem(28);
        int amount = itemStack.getAmount();
        itemStack.setAmount(amount - Setting.PuremagicBlackstoneAmonut(level));
    }

    public static void setAmount(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(28);
        int amount = itemStack.getAmount();
        itemStack.setAmount(amount - 1);
    }

    public static boolean isWeaponStoneLore(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(28);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getLore().contains(Setting.WeaponStoneLore())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isArromStoneLore(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(28);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getLore().contains(Setting.ArromStoneLore())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isArromStoneLore1(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(28);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getLore().contains(Setting.ArromStoneLore1())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isWeaponStoneLore1(Inventory inventory) {
        ItemStack itemStack = inventory.getItem(28);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getLore().contains(Setting.WeaponStoneLore1())) {
            return true;
        } else {
            return false;
        }
    }

    public static void useStone(ItemStack itemStack, Inventory inventory, Player player, ItemMeta meta, List<String> LevelString) {
        if (Context.WEAPON.equals(Util.TypeId(itemStack))) {
            if (Setting.requireConcentratedStones(Util.sucesslevel(meta))) {
                if (OtherUtil.isWeaponStoneLore1(inventory)) {
                    OtherUtil.failLore(meta, LevelString, itemStack, player, inventory);
                } else {
                    player.sendMessage(BKI18n.get("BlackStoneWeapon", new Locale(Util.langPerfix(), Util.langSu())));
                }
            } else {
                if (OtherUtil.isWeaponStoneLore(inventory)) {
                    OtherUtil.failLore(meta, LevelString, itemStack, player, inventory);
                } else {
                    player.sendMessage(BKI18n.get("BlackStoneWeapon", new Locale(Util.langPerfix(), Util.langSu())));
                }
            }
        } else if (Context.ARROM.equals(Util.TypeId(itemStack))) {
            if (Setting.requireConcentratedStones(Util.sucesslevel(meta))) {
                if (OtherUtil.isArromStoneLore1(inventory)) {
                    OtherUtil.failLore(meta, LevelString, itemStack, player, inventory);
                } else {
                    player.sendMessage(BKI18n.get("BlackStoneArrom", new Locale(Util.langPerfix(), Util.langSu())));
                }
            } else {
                if (OtherUtil.isArromStoneLore(inventory)) {
                    OtherUtil.failLore(meta, LevelString, itemStack, player, inventory);
                } else {
                    player.sendMessage(BKI18n.get("BlackStoneArrom", new Locale(Util.langPerfix(), Util.langSu())));
                }
            }
        }
    }

    public static void failLore(ItemMeta meta, List<String> LevelString, ItemStack itemStack, Player player, Inventory inventory) {
        if (Util.sucesslevel(meta) >= Context.EIGHTEEN) {
            LevelString = BlackDesertPro.instance.getConfig().getStringList("BlackDesertPro.Plan." + Util.dropLevel(meta) + "." + Util.TypeId(itemStack) + ".lore");
            OtherUtil.dropLore(Util.dropLevel(meta), meta, Setting.startLore(), Setting.endLore(), itemStack, LevelString);
            OtherUtil.setAmount(inventory);
            OtherUtil.dz(meta, itemStack);
            player.sendMessage(BKI18n.get("LevelDown", new Locale(Util.langPerfix(), Util.langSu())));
        } else {
            OtherUtil.setAmount(inventory);
            OtherUtil.dz(meta, itemStack);
            player.sendMessage(BKI18n.get("FailMessage", new Locale(Util.langPerfix(), Util.langSu())));
        }
    }

    public static void dropLore(int level, ItemMeta meta, String startLore, String endLore, ItemStack itemStack, List<String> LevelString) {
        List<String> list = meta.getLore();
        int x = list.indexOf(Setting.endLore()) - list.indexOf(Setting.startLore()) + 1;
        int y = list.indexOf(Setting.startLore());
        if (level >= Context.TWO) {
            for (int i = 0; i < x; i++) {
                list.remove(y);
            }
            list.add(startLore);
            list.addAll(LevelString);
            list.add(BKI18n.get("level", new Locale(Util.langPerfix(), Util.langSu())) + level);
            list.add(BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + (Util.level(meta) - 16));
            list.add(endLore);
            meta.setLore(list);
            itemStack.setItemMeta(meta);
        }
    }

    public static void setAmount(ItemStack itemStack, ItemStack itemStack1) {
        itemStack.setAmount(itemStack.getAmount() - 1);
        itemStack1.setAmount(itemStack1.getAmount() - 1);
    }

    public static void broadcastMsg(ItemMeta meta, ItemStack itemStack, Player player, int level) {
        if (meta.getDisplayName().isEmpty()) {
            Bukkit.broadcastMessage(BKI18n.get("Congratulations", new Locale(Util.langPerfix(), Util.langSu())) + player.getDisplayName() +
                    BKI18n.get("To", new Locale(Util.langPerfix(), Util.langSu())) + itemStack.getType() + BKI18n.get("BreakThroughTo", new Locale(Util.langPerfix(), Util.langSu())) + level);
        } else {
            Bukkit.broadcastMessage(BKI18n.get("Congratulations", new Locale(Util.langPerfix(), Util.langSu())) + player.getDisplayName() +
                    BKI18n.get("To", new Locale(Util.langPerfix(), Util.langSu())) + meta.getDisplayName() + BKI18n.get("BreakThroughTo", new Locale(Util.langPerfix(), Util.langSu())) + level);
        }
    }
}

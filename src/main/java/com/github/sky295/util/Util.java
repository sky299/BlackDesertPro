package com.github.sky295.util;

import com.github.sky295.data.Setting;
import com.github.sky295.i18n.BKI18n;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static Double rand() {
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(Math.random() * 100);
        return Double.valueOf(str);
    }

    public static String TypeId(ItemStack itemStack) {
        try {
            return OtherUtil.equipmentType(itemStack);
        } catch (NoSuchMethodError error) {
            return OtherUtil.equipmentType1(itemStack);
        }
    }

    public static int sucesslevel(ItemMeta meta) {
        int level = Util.level(meta);
        level++;
        return level;
    }

    public static int faillevel(ItemMeta meta) {
        int level = Util.failLevel(meta);
        level++;
        return level;
    }

    public static int failLevel(ItemMeta meta) {
        int level = 0;
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        if (!lore.isEmpty()) {
            for (String line : lore) {
                if (line.contains(BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())))) {
                    level = Integer.valueOf(line.substring((line.indexOf("：") + 1)));
                }
            }
        }
        return level;
    }

    public static int dropLevel(ItemMeta meta) {
        int level = Util.level(meta);
        level--;
        return level;
    }

    public static int level(ItemMeta meta) {
        int level = 0;
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        if (!lore.isEmpty()) {
            for (String line : lore) {
                if (line.contains(BKI18n.get("level", new Locale(Util.langPerfix(), Util.langSu())))) {
                    level = Integer.parseInt(line.substring((line.indexOf("：") + 1)));
                }
            }
        }
        return level;
    }

    public static Double chance(int level, ItemMeta meta) {
        return (Setting.evevyLevelchance(level) * Util.failLevel(meta));
    }

    public static Double ornamentsChance(int level, ItemMeta meta) {
        return (Setting.ornamentsEverylevel(level) * Util.failLevel(meta));
    }

    public static String langPerfix() {
        String[] str = Setting.lang().split("_");
        return str[0];
    }

    public static String langSu() {
        String[] str = Setting.lang().split("_");
        return str[1];
    }

    public static double getStringNum(String s, String s1) {
        String[] strings = s.split(s1);
        String[] strings1 = strings[1].split("%");
        String[] strings2 = strings1[0].split(" §b");
        return Double.parseDouble(strings2[1]);
    }
}

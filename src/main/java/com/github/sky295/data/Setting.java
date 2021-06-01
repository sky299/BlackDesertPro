package com.github.sky295.data;

import com.github.sky295.BlackDesertPro;

public class Setting {
    public static String startLore() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.startLore").replace("&", "§");
    }

    public static String endLore() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.endLore").replace("&", "§");
    }

    public static Double rd(int level) {
        return BlackDesertPro.instance.getConfig().getDouble("BlackDesertPro.Plan." + level + ".rand");
    }

    public static boolean isBroadcastdmsg(int level) {
        return BlackDesertPro.instance.getConfig().getBoolean("BlackDesertPro.Plan." + level + ".isBroadcastdMsg");
    }

    public static int maxconunt(int level) {
        return BlackDesertPro.instance.getConfig().getInt("BlackDesertPro.Plan." + level + ".maxCount");
    }

    public static Double evevyLevelchance(int level) {
        return BlackDesertPro.instance.getConfig().getDouble("BlackDesertPro.Plan." + level + ".evevyLevelchance");
    }

    public static int safeStone(int level) {
        return BlackDesertPro.instance.getConfig().getInt("BlackDesertPro.Plan." + level + ".safeStone");
    }

    public static String WeaponStoneLore() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.WeaponStoneLore").replace("&", "§");
    }

    public static String ArromStoneLore() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.ArromStoneLore").replace("&", "§");
    }

    public static String ArromStoneLore1() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.ArromStoneLore1").replace("&", "§");
    }

    public static String WeaponStoneLore1() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.WeaponStoneLore1").replace("&", "§");
    }

    public static boolean requireConcentratedStones(int level) {
        return BlackDesertPro.instance.getConfig().getBoolean("BlackDesertPro.Plan." + level + ".requireConcentratedStones");
    }

    public static int ExtraBalks(int level) {
        return BlackDesertPro.instance.getConfig().getInt("BlackDesertPro.Plan." + level + ".ExtraBalks");
    }

    public static Double ornamentsRand(int level) {
        return BlackDesertPro.instance.getConfig().getDouble("BlackDesertPro.ornamentsPlan." + level + ".rand");
    }

    public static int ornamentsPlanMaxCoount(int level) {
        return BlackDesertPro.instance.getConfig().getInt("BlackDesertPro.ornamentsPlan." + level + ".maxCount");
    }

    public static Double ornamentsEverylevel(int level) {
        return BlackDesertPro.instance.getConfig().getDouble("BlackDesertPro.ornamentsPlan." + level + ".evevyLevelchance");
    }

    public static boolean isBroardMsg(int level) {
        return BlackDesertPro.instance.getConfig().getBoolean("BlackDesertPro.ornamentsPlan." + level + ".isBroadcastdMsg");
    }

    public static String ornamentsLore() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.ornamentsLore").replace("&", "§");
    }

    public static String PuremagicBlackstone() {
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.PuremagicBlackstone").replace("&", "§");
    }

    public static int PuremagicBlackstoneAmonut(int level) {
        return BlackDesertPro.instance.getConfig().getInt("BlackDesertPro.Plan." + level + ".PuremagicBlackstoneAmonut");
    }
    public static String lang(){
        return BlackDesertPro.instance.getConfig().getString("BlackDesertPro.Languages");
    }
}

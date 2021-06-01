package com.github.sky295.listener;

import com.github.sky295.BlackDesertPro;
import com.github.sky295.api.Context;
import com.github.sky295.data.Setting;
import com.github.sky295.i18n.BKI18n;
import com.github.sky295.util.OtherUtil;
import com.github.sky295.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class BlackDesertProEvent implements Listener {
    @EventHandler
    public void onPlayerClickInv(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        if (event.getView().getTitle().equalsIgnoreCase(BKI18n.get(Context.BLACKDESERTPRO, new Locale(Util.langPerfix(), Util.langSu())))) {
            if (event.getRawSlot() != Context.TWENTYEIGHT && event.getRawSlot() != Context.THIRTYONE && event.getRawSlot() != Context.THIRTYFOUR && event.getRawSlot() < Context.FIFTYFOUR) {
                event.setCancelled(true);
            }
            try {
                if (event.getRawSlot() == Context.THIRTYNINE) {
                    ItemStack itemStack = inventory.getItem(34);
                    ItemMeta meta = itemStack.getItemMeta();
                    List<String> LevelString;
                    if (inventory.getItem(Context.TWENTYEIGHT) == null) {
                        player.sendMessage(BKI18n.get("LackOfMaterials", new Locale(Util.langPerfix(), Util.langSu())));
                        return;
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (itemStack.getType() != Material.AIR) {
                                ItemStack itemStack2 = inventory.getItem(39);
                                ItemMeta meta2 = itemStack2.getItemMeta();
                                try {
                                    if (Context.WEAPON.equals(Util.TypeId(itemStack)) || Context.ARROM.equals(Util.TypeId(itemStack))) {
                                        meta2.setLore(Arrays.asList(BKI18n.get("NextProbabilityLevel", new Locale(Util.langPerfix(), Util.langSu())) + " §b" + (Setting.rd(Util.sucesslevel(meta)) + Util.chance(Util.sucesslevel(meta), meta)) + "%"));
                                        itemStack2.setItemMeta(meta2);
                                        cancel();
                                    }
                                } catch (NullPointerException e) {
                                    if (meta.hasLore() && meta.getLore().contains(Setting.ornamentsLore())) {
                                        meta2.setLore(Arrays.asList(BKI18n.get("NextProbabilityLevel", new Locale(Util.langPerfix(), Util.langSu())) + " §b" + (Setting.ornamentsRand(Util.sucesslevel(meta)) + Util.ornamentsChance(Util.sucesslevel(meta), meta)) + "%"));
                                        itemStack2.setItemMeta(meta2);
                                        cancel();
                                    }
                                }
                            }
                        }
                    }.runTaskTimerAsynchronously(BlackDesertPro.instance, 0L, 20L);
                    if (meta.hasLore() && meta.getLore().contains(Setting.ornamentsLore())) {
                        ItemStack itemStack1 = inventory.getItem(28);
                        ItemMeta meta1 = itemStack1.getItemMeta();
                        LevelString = BlackDesertPro.instance.getConfig().getStringList("BlackDesertPro.ornamentsPlan." + Util.sucesslevel(meta) + ".lore");
                        if (LevelString.isEmpty()) {
                            player.sendMessage(BKI18n.get("NoEquipment", new Locale(Util.langPerfix(), Util.langSu())));
                        } else {
                            if (!meta1.getLore().contains(BKI18n.get(Context.LEVEL, new Locale(Util.langPerfix(), Util.langSu())) + Util.level(meta1))) {
                                if (meta1.hasLore() && meta1.getLore().contains(Setting.ornamentsLore())) {
                                    if (Util.rand() > 0 && Util.rand() < (Setting.ornamentsRand(Util.sucesslevel(meta)) + Util.ornamentsChance(Util.sucesslevel(meta), meta))) {
                                        itemStack1.setAmount(itemStack1.getAmount() - 1);
                                        OtherUtil.setlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                                    } else {
                                        OtherUtil.setAmount(itemStack, itemStack1);
                                        player.sendMessage(BKI18n.get("FailMessage", new Locale(Util.langPerfix(), Util.langSu())));
                                    }
                                } else {
                                    player.sendMessage(BKI18n.get("WrongMaterial", new Locale(Util.langPerfix(), Util.langSu())));
                                }
                            } else {
                                player.sendMessage(BKI18n.get("CantEquip", new Locale(Util.langPerfix(), Util.langSu())));
                            }
                        }
                    } else {
                        if (Util.TypeId(itemStack) == null) {
                            player.sendMessage(BKI18n.get("CantEquip", new Locale(Util.langPerfix(), Util.langSu())));
                            return;
                        }
                        LevelString = BlackDesertPro.instance.getConfig().getStringList("BlackDesertPro.Plan." + Util.sucesslevel(meta) + "." + Util.TypeId(itemStack) + ".lore");
                        if (LevelString.isEmpty()) {
                            player.sendMessage(BKI18n.get("NoEquipment", new Locale(Util.langPerfix(), Util.langSu())));
                        } else {
                            if (Util.rand() > 0 && Util.rand() < (Setting.rd(Util.sucesslevel(meta)) + Util.chance(Util.sucesslevel(meta), meta))) {
                                if (Context.WEAPON.equals(Util.TypeId(itemStack))) {
                                    if (Setting.requireConcentratedStones(Util.sucesslevel(meta))) {
                                        if (OtherUtil.isWeaponStoneLore1(inventory)) {
                                            OtherUtil.addlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                                            OtherUtil.setAmount(inventory);
                                        } else {
                                            player.sendMessage(BKI18n.get("BlackStoneWeapon", new Locale(Util.langPerfix(), Util.langSu())));
                                        }
                                    } else {
                                        if (OtherUtil.isWeaponStoneLore(inventory)) {
                                            OtherUtil.addlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                                            OtherUtil.setAmount(inventory);
                                        } else {
                                            player.sendMessage(BKI18n.get("BlackStoneWeapon", new Locale(Util.langPerfix(), Util.langSu())));
                                        }
                                    }
                                } else if (Context.ARROM.equals(Util.TypeId(itemStack))) {
                                    if (Setting.requireConcentratedStones(Util.sucesslevel(meta))) {
                                        if (OtherUtil.isArromStoneLore1(inventory)) {
                                            OtherUtil.addlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                                            OtherUtil.setAmount(inventory);
                                        } else {
                                            player.sendMessage(BKI18n.get("BlackStoneArrom", new Locale(Util.langPerfix(), Util.langSu())));
                                        }
                                    } else {
                                        if (OtherUtil.isArromStoneLore(inventory)) {
                                            OtherUtil.addlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                                            OtherUtil.setAmount(inventory);
                                        } else {
                                            player.sendMessage(BKI18n.get("BlackStoneArrom", new Locale(Util.langPerfix(), Util.langSu())));
                                        }
                                    }
                                }
                            } else {
                                OtherUtil.useStone(itemStack, inventory, player, meta, LevelString);
                            }
                        }
                    }
                } else if (event.getRawSlot() == Context.FORTYONE) {
                    ItemStack itemStack = inventory.getItem(34);
                    ItemMeta meta = itemStack.getItemMeta();
                    ItemStack itemStack1 = inventory.getItem(28);
                    ItemMeta meta1 = itemStack1.getItemMeta();
                    List<String> LevelString = BlackDesertPro.instance.getConfig().getStringList("BlackDesertPro.Plan." + Util.sucesslevel(meta) + "." + Util.TypeId(itemStack) + ".lore");
                    if (LevelString.isEmpty()) {
                        player.sendMessage(BKI18n.get("NoEquipment", new Locale(Util.langPerfix(), Util.langSu())));
                    } else if (Util.sucesslevel(meta) >= Context.SEVEN && Util.sucesslevel(meta) < Context.SIXTEEN) {
                        if (meta1.getLore().contains(Setting.PuremagicBlackstone()) && Util.sucesslevel(meta) >= Context.THIRTEEN && Util.sucesslevel(meta) < Context.SIXTEEN) {
                            if (inventory.getItem(Context.TWENTYEIGHT).getAmount() >= Setting.PuremagicBlackstoneAmonut(Util.sucesslevel(meta))) {
                                OtherUtil.setPuremagicBlackstoneAmount(inventory, Util.sucesslevel(meta));
                                OtherUtil.addlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                            } else {
                                player.sendMessage(BKI18n.get("Force", new Locale(Util.langPerfix(), Util.langSu())));
                            }
                        } else {
                            if (meta1.getLore().contains(Setting.WeaponStoneLore()) && Util.sucesslevel(meta) < Context.THIRTEEN) {
                                if (inventory.getItem(Context.TWENTYEIGHT).getAmount() >= Setting.safeStone(Util.sucesslevel(meta))) {
                                    OtherUtil.setSafeAmount(inventory, Util.sucesslevel(meta));
                                    OtherUtil.addlore(meta, LevelString, Util.sucesslevel(meta), itemStack, player);
                                } else {
                                    player.sendMessage(BKI18n.get("Force", new Locale(Util.langPerfix(), Util.langSu())));
                                }
                            } else {
                                player.sendMessage(BKI18n.get("NoUseMaterial", new Locale(Util.langPerfix(), Util.langSu())));
                            }
                        }
                    }
                } else if (event.getRawSlot() == Context.THIRTEEN) {
                    ItemStack itemStack = inventory.getItem(34);
                    ItemMeta meta = itemStack.getItemMeta();
                    List<String> lore = meta.getLore();
                    if (itemStack.getAmount() > 1) {
                        player.sendMessage(BKI18n.get("stack", new Locale(Util.langPerfix(), Util.langSu())));
                        return;
                    }
                    if (inventory.getItem(Context.THIRTYONE) != null && inventory.getItem(Context.THIRTYONE).getItemMeta().getDisplayName().contains(BKI18n.get(Context.BALKS, new Locale(Util.langPerfix(), Util.langSu())))) {
                        ItemStack itemStack1 = inventory.getItem(31);
                        ItemMeta itemMeta = itemStack1.getItemMeta();
                        int index = lore.indexOf(Setting.endLore());
                        String zg = itemMeta.getDisplayName();
                        int zgcs = Integer.parseInt(zg.substring((zg.indexOf("+") + 1)));
                        if (lore.contains(Setting.ornamentsLore())) {
                            if (Util.level(meta) == 0) {
                                if (zgcs > Setting.ornamentsPlanMaxCoount(Util.faillevel(meta))) {
                                    player.sendMessage(BKI18n.get("gtMatNumber", new Locale(Util.langPerfix(), Util.langSu())));
                                    return;
                                }
                            } else if (Util.level(meta) >= 1) {
                                if (zgcs > Setting.ornamentsPlanMaxCoount((Util.level(meta) + 1))) {
                                    player.sendMessage(BKI18n.get("gtMatNumber", new Locale(Util.langPerfix(), Util.langSu())));
                                    return;
                                }
                            }
                        } else {
                            if (Util.level(meta) >= Context.SEVEN) {
                                if (zgcs > Setting.maxconunt((Util.level(meta) + 1))) {
                                    player.sendMessage(BKI18n.get("gtMatNumber", new Locale(Util.langPerfix(), Util.langSu())));
                                    return;
                                }
                            }
                        }
                        if (!lore.contains(BKI18n.get(Context.MAT, new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) - 1))) {
                            if (lore.contains(BKI18n.get(Context.MAT, new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) - 1))) {
                                lore.set(index - 1, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + zgcs);
                            } else {
                                if (index == -1) {
                                    lore.add(BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + zgcs);
                                } else {
                                    lore.add(index, BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + zgcs);
                                }
                            }
                            meta.setLore(lore);
                            itemStack.setItemMeta(meta);
                            inventory.removeItem(itemStack1);
                            player.sendMessage(BKI18n.get("UseBalks", new Locale(Util.langPerfix(), Util.langSu())) + zgcs);
                        } else {
                            player.sendMessage(BKI18n.get("Current", new Locale(Util.langPerfix(), Util.langSu())));
                        }
                    } else {
                        if (lore.contains(BKI18n.get(Context.MAT, new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) - 1))) {
                            String dz = lore.get(lore.indexOf(BKI18n.get("mat", new Locale(Util.langPerfix(), Util.langSu())) + (Util.faillevel(meta) - 1)));
                            int dzcs = Integer.parseInt(dz.substring((dz.indexOf("：") + 1)));
                            ItemStack itemStack1 = new ItemStack(Material.BOOK, 1);
                            ItemMeta meta1 = itemStack1.getItemMeta();
                            meta1.setDisplayName(BKI18n.get("balks", new Locale(Util.langPerfix(), Util.langSu())) + dzcs);
                            itemStack1.setItemMeta(meta1);
                            lore.remove(dz);
                            meta.setLore(lore);
                            itemStack.setItemMeta(meta);
                            player.getInventory().addItem(itemStack1);
                            player.sendMessage(BKI18n.get("SaveIt", new Locale(Util.langPerfix(), Util.langSu())) + dzcs);
                        }
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    @EventHandler
    public void onPlayerCloseInv(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        if (event.getView().getTitle().equalsIgnoreCase(BKI18n.get(Context.BLACKDESERTPRO, new Locale(Util.langPerfix(), Util.langSu())))) {
            if (inventory.getItem(Context.THIRTYFOUR) != null) {
                player.getInventory().addItem(inventory.getItem(34));
            }
            if (inventory.getItem(Context.THIRTYONE) != null) {
                player.getInventory().addItem(inventory.getItem(31));
            }
            if (inventory.getItem(Context.TWENTYEIGHT) != null) {
                player.getInventory().addItem(inventory.getItem(28));
            }
        }
    }
}

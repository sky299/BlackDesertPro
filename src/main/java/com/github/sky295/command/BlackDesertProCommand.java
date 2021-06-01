package com.github.sky295.command;

import com.github.sky295.api.Context;
import com.github.sky295.gui.Gui;
import com.github.sky295.i18n.BKI18n;
import com.github.sky295.util.Util;
import com.github.sky295.util.configManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.inventivetalent.reflection.minecraft.Minecraft;

import java.util.Locale;
import java.util.Set;

public class BlackDesertProCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 1 && player.isOp() && Context.RELOAD.equalsIgnoreCase(strings[0])) {
            new configManager(player);
        } else if (strings.length == 1 && Context.MENU.equalsIgnoreCase(strings[0])) {
            Inventory inv = Bukkit.createInventory(null, 54, BKI18n.get("blackdesertpro", new Locale(Util.langPerfix(), Util.langSu())));
            if (Minecraft.VERSION.newerThan(Minecraft.Version.v1_13_R1)) {
                Gui.createGui(inv);
            } else {
                Gui.CreateInv(inv);
            }
            player.openInventory(inv);
        }
        return true;
    }
}

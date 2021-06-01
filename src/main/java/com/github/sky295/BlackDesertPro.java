package com.github.sky295;

import com.github.sky295.command.BlackDesertProCommand;
import com.github.sky295.listener.BlackDesertProEvent;
import com.github.sky295.i18n.I18nFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BlackDesertPro extends JavaPlugin {
    public static BlackDesertPro instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        I18nFile.setInstance(this);
        getCommand("bdp").setExecutor(new BlackDesertProCommand());
        Bukkit.getPluginManager().registerEvents(new BlackDesertProEvent(), this);
        Bukkit.getConsoleSender().sendMessage(" §b_____   _           ___   _____   _   _    _____   _____   _____   _____    _____   _____   _____    _____  ");
        Bukkit.getConsoleSender().sendMessage("§b|  _  \\ | |         /   | /  ___| | | / /  |  _  \\ /  ___/ | ____| |  _  \\  |_   _| |  _  \\ |  _  \\  /  _  \\ ");
        Bukkit.getConsoleSender().sendMessage("§b| |_| | | |        / /| | | |     | |/ /   | | | | | |___  | |__   | |_| |    | |   | |_| | | |_| |  | | | | ");
        Bukkit.getConsoleSender().sendMessage("§b|  _  { | |       / / | | | |     | |\\ \\   | | | | \\___  \\ |  __|  |  _  /    | |   |  ___/ |  _  /  | | | | ");
        Bukkit.getConsoleSender().sendMessage("§b| |_| | | |___   / /  | | | |___  | | \\ \\  | |_| |  ___| | | |___  | | \\ \\    | |   | |     | | \\ \\  | |_| | ");
        Bukkit.getConsoleSender().sendMessage("§b|_____/ |_____| /_/   |_| \\_____| |_|  \\_\\ |_____/ /_____/ |_____| |_|  \\_\\   |_|   |_|     |_|  \\_\\ \\_____/ ");

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§b**********************************");
        Bukkit.getConsoleSender().sendMessage("§b                                  ");
        Bukkit.getConsoleSender().sendMessage("§b      感谢使用BlackDesertPro!      ");
        Bukkit.getConsoleSender().sendMessage("§b                                  ");
        Bukkit.getConsoleSender().sendMessage("§b**********************************");
    }
}

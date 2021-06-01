package com.github.sky295.util;

import com.github.sky295.BlackDesertPro;
import com.github.sky295.i18n.BKI18n;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Locale;

public class configManager {
    private static final String configFileName = "config.yml";

    public configManager(Player player) {
        reload(player);
    }

    public void reload(Player player) {
        File file = new File(BlackDesertPro.instance.getDataFolder(), configFileName);
        BlackDesertPro.instance.reloadConfig();
        if (!file.exists()) {
            BlackDesertPro.instance.saveResource(configFileName, false);
        }
        player.sendMessage(BKI18n.get("reload", new Locale(Util.langPerfix(), Util.langSu())));
    }
}

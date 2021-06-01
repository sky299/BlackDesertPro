package com.github.sky295.i18n;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Locale;

public class I18nFile {

    private static JavaPlugin plugin;
    private final Locale locale;
    private final String dir;

    public I18nFile(Locale locale, String dir) {
        this.locale = locale;
        this.dir = dir;
    }

    /**
     * 初始化对象
     *
     * @param dir I18n文件存放的文件夹
     * @return 当前类实例
     */
    public static I18nFile getInstance(String dir) {
        return new I18nFile(Locale.getDefault(), dir);
    }

    /**
     * 初始化对象
     *
     * @param plugin 当前插件实例
     */
    public static void setInstance(JavaPlugin plugin) {
        I18nFile.plugin = plugin;
    }

    /**
     * 初始化对象
     *
     * @param dir    I18n文件存放的文件夹
     * @param locale 当前语言
     * @return 当前类实例
     */
    public static I18nFile getInstance(Locale locale, String dir) {
        return new I18nFile(locale, dir);
    }

    /**
     * 初始化对象
     *
     * @param locale 当前语言
     * @return 当前类实例
     */
    public static I18nFile getInstance(Locale locale) {
        return getInstance(locale, new File(plugin.getDataFolder(), "/message/").getAbsolutePath());
    }

    /**
     * 初始化对象
     *
     * @return 当前类实例
     */
    public static I18nFile getInstance() {
        return getInstance(Locale.getDefault());
    }


    public YamlConfiguration getFile() {
        String lang = locale.getLanguage();
        String country = locale.getCountry();
        String filename = lang + "_" + country + ".yml";
        File dir = new File(this.dir);
        if (this.plugin != null) {
            if (!dir.exists()) dir.mkdirs();
            File file = new File(dir, filename);
            if (file.exists()) {
                return YamlConfiguration.loadConfiguration(file);
            } else {
                try {
                    file.createNewFile();
                    InputStream is = I18nFile.class.getResourceAsStream("/message/" + filename);
                    if (is != null) {
                        FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(is));
                    }
                    return YamlConfiguration.loadConfiguration(file);
                } catch (IOException e) {
                    if (this.locale != Locale.US) {
                        return I18nFile.getInstance(Locale.US).getFile();
                    }
                }
            }

        }
        return null;
    }
}

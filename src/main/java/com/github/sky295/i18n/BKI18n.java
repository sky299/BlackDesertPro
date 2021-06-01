package com.github.sky295.i18n;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Locale;

public class BKI18n {

    /**
     * 返回key对应的值
     * 默认使用本地语言
     *
     * @param key key
     * @return 对应的值
     */
    public static String get(String key) {
        return get(key, Locale.getDefault());
    }

    /**
     * 返回key对应的值
     * 默认使用本地语言
     *
     * @param key key
     * @return 对应的值
     */
    public static String get(String key, String def) {
        String v = get(key, Locale.getDefault());
        return isNullOrEmpty(v) ? def : v;
    }

    public static boolean isNullOrEmpty(String v) {
        return v == null || v.replace(" ", "").equalsIgnoreCase("");
    }

    /**
     * 返回key对应的值
     * 使用locale指定的语言
     *
     * @param key    key
     * @param args   要替换的值
     * @param locale 语言
     * @return 对应的值
     */
    public static String get(String key, Locale locale, String def, String... args) {
        String v = get(key, locale);
        String value = isNullOrEmpty(v) ? def : v;
        for (String arg : args) {
            value = value.replaceFirst("\\{}", arg);
        }
        return value;
    }

    /**
     * 返回key对应的值
     * 使用locale指定的语言
     *
     * @param key    key
     * @param args   要替换的值
     * @param locale 语言
     * @return 对应的值
     */
    public static String get(String key, Locale locale, String... args) {
        String value = get(key, locale);
        for (String arg : args) {
            value = value.replaceFirst("\\{}", arg);
        }
        return value;
    }

    /**
     * 返回key对应的值
     * 默认使用本地语言
     *
     * @param key  key
     * @param args 要替换的值
     * @return 对应的值
     */
    public static String get(String key, String... args) {
        return get(key, Locale.getDefault(), args);
    }

    /**
     * 返回key对应的值
     * 默认使用本地语言
     *
     * @param key  key
     * @param args 要替换的值
     * @return 对应的值
     */
    public static String get(String key, String def, String... args) {
        String v = get(key, Locale.getDefault());
        return isNullOrEmpty(v) ? def : v;
    }

    /**
     * 返回key对应的值
     * 使用locale指定的语言
     *
     * @param key    key
     * @param locale 语言
     * @return 对应的值
     */
    public static String get(String key, Locale locale) {
        return get(key, locale, "");
    }

    /**
     * 返回key对应的值
     * 使用locale指定的语言
     *
     * @param key    key
     * @param locale 语言
     * @return 对应的值
     */
    public static String get(String key, Locale locale, String def) {
        YamlConfiguration yml = I18nFile.getInstance(locale).getFile();
        if (yml != null) {
            String v = yml.getString(key, null);
            return isNullOrEmpty(v) ? def : v;
        }
        return null;
    }
}

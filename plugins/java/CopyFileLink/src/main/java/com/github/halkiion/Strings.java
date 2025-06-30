package com.github.halkiion.plugins;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import android.content.Context;

import com.lytefast.flexinput.R;

public class Strings {
    public static final Map<String, Map<String, String>> STRINGS = new HashMap<>();

    static {
        // English
        put("link_copied", "en", "File link copied to clipboard!");
        // Spanish
        put("link_copied", "es", "¡Enlace de archivo copiado al portapapeles!");
        // French
        put("link_copied", "fr", "Lien de fichier copié dans le presse-papiers !");
        // German
        put("link_copied", "de", "Dateilink in die Zwischenablage kopiert!");
        // Russian
        put("link_copied", "ru", "Ссылка на файл скопирована в буфер обмена!");
        // Chinese (Simplified)
        put("link_copied", "zh", "文件链接已复制到剪贴板！");
        // Japanese
        put("link_copied", "ja", "ファイルリンクがクリップボードにコピーされました！");
        // Korean
        put("link_copied", "ko", "파일 링크가 클립보드에 복사되었습니다!");
        // Portuguese
        put("link_copied", "pt", "Link do arquivo copiado para a área de transferência!");
        // Italian
        put("link_copied", "it", "Link del file copiato negli appunti!");
        // Turkish
        put("link_copied", "tr", "Dosya bağlantısı panoya kopyalandı!");
        // Arabic
        put("link_copied", "ar", "تم نسخ رابط الملف إلى الحافظة!");
        // Hindi
        put("link_copied", "hi", "फ़ाइल लिंक क्लिपबोर्ड पर कॉपी किया गया!");
        // Polish
        put("link_copied", "pl", "Link do pliku skopiowany do schowka!");
        // Dutch
        put("link_copied", "nl", "Bestandslink gekopieerd naar klembord!");
        // Swedish
        put("link_copied", "sv", "Fillänk kopierad till urklipp!");
        // Ukrainian
        put("link_copied", "uk", "Посилання на файл скопійовано в буфер обміну!");
        // Romanian
        put("link_copied", "ro", "Linkul fișierului a fost copiat în clipboard!");
        // Hungarian
        put("link_copied", "hu", "A fájl linkje a vágólapra másolva!");
        // Czech
        put("link_copied", "cs", "Odkaz na soubor zkopírován do schránky!");
        // Finnish
        put("link_copied", "fi", "Tiedoston linkki kopioitu leikepöydälle.");
    }

    private static void put(String key, String lang, String value) {
        STRINGS.computeIfAbsent(key, k -> new HashMap<>()).put(lang, value);
    }

    public static String getString(String key) {
        Map<String, String> translations = STRINGS.get(key);
        if (translations == null) return null;
        return translations.getOrDefault(Locale.getDefault().getLanguage(), translations.get("en"));
    }

    public static String getString(String key, String fallback) {
        Map<String, String> translations = STRINGS.get(key);
        if (translations == null) return fallback;
        return translations.getOrDefault(Locale.getDefault().getLanguage(), fallback);
    }
}
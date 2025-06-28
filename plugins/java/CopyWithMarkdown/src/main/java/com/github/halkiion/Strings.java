package com.github.halkiion.plugins;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Strings {
    public static final Map<String, Map<String, String>> STRINGS = new HashMap<>();

    static {
        // English
        put("copy_with_markdown", "en", "Copy with Markdown");
        put("separate_buttons", "en", "Separate buttons");
        put("separate_buttons_desc", "en", "Create a separate button for copying with markdown");
        put("copied_with_markdown", "en", "Copied with Markdown.");
        put("copied_to_clipboard", "en", "Copied to clipboard.");

        // Spanish
        put("copy_with_markdown", "es", "Copiar con Markdown");
        put("separate_buttons", "es", "Botones separados");
        put("separate_buttons_desc", "es", "Crear un botón separado para copiar con markdown");
        put("copied_with_markdown", "es", "Copiado con Markdown.");
        put("copied_to_clipboard", "es", "Copiado al portapapeles.");

        // French
        put("copy_with_markdown", "fr", "Copier avec Markdown");
        put("separate_buttons", "fr", "Séparer les boutons");
        put("separate_buttons_desc", "fr", "Créer un bouton séparé pour copier avec markdown");
        put("copied_with_markdown", "fr", "Copié avec Markdown.");
        put("copied_to_clipboard", "fr", "Copié dans le presse-papiers.");

        // German
        put("copy_with_markdown", "de", "Mit Markdown kopieren");
        put("separate_buttons", "de", "Schaltflächen trennen");
        put("separate_buttons_desc", "de", "Erstelle eine separate Schaltfläche zum Kopieren mit Markdown");
        put("copied_with_markdown", "de", "Mit Markdown kopiert.");
        put("copied_to_clipboard", "de", "In die Zwischenablage kopiert.");

        // Russian
        put("copy_with_markdown", "ru", "Копировать с Markdown");
        put("separate_buttons", "ru", "Разделить кнопки");
        put("separate_buttons_desc", "ru", "Создать отдельную кнопку для копирования с Markdown");
        put("copied_with_markdown", "ru", "Скопировано с Markdown.");
        put("copied_to_clipboard", "ru", "Скопировано в буфер обмена.");

        // Chinese
        put("copy_with_markdown", "zh", "使用 Markdown 复制");
        put("separate_buttons", "zh", "分离按钮");
        put("separate_buttons_desc", "zh", "为使用 Markdown 复制创建单独的按钮");
        put("copied_with_markdown", "zh", "已用 Markdown 复制。");
        put("copied_to_clipboard", "zh", "已复制到剪贴板。");

        // Japanese
        put("copy_with_markdown", "ja", "Markdownでコピー");
        put("separate_buttons", "ja", "ボタンを分離");
        put("separate_buttons_desc", "ja", "Markdownでコピーするための別のボタンを作成");
        put("copied_with_markdown", "ja", "Markdownでコピーしました。");
        put("copied_to_clipboard", "ja", "クリップボードにコピーしました。");

        // Korean
        put("copy_with_markdown", "ko", "Markdown으로 복사");
        put("separate_buttons", "ko", "버튼 분리");
        put("separate_buttons_desc", "ko", "마크다운으로 복사할 별도의 버튼 만들기");
        put("copied_with_markdown", "ko", "Markdown으로 복사됨.");
        put("copied_to_clipboard", "ko", "클립보드에 복사됨.");

        // Portuguese
        put("copy_with_markdown", "pt", "Copiar com Markdown");
        put("separate_buttons", "pt", "Separar botões");
        put("separate_buttons_desc", "pt", "Criar um botão separado para copiar com markdown");
        put("copied_with_markdown", "pt", "Copiado com Markdown.");
        put("copied_to_clipboard", "pt", "Copiado para a área de transferência.");

        // Italian
        put("copy_with_markdown", "it", "Copia con Markdown");
        put("separate_buttons", "it", "Separa pulsanti");
        put("separate_buttons_desc", "it", "Crea un pulsante separato per copiare con markdown");
        put("copied_with_markdown", "it", "Copiato con Markdown.");
        put("copied_to_clipboard", "it", "Copiato negli appunti.");

        // Turkish
        put("copy_with_markdown", "tr", "Markdown ile kopyala");
        put("separate_buttons", "tr", "Düğmeleri ayır");
        put("separate_buttons_desc", "tr", "Markdown ile kopyalamak için ayrı bir düğme oluştur");
        put("copied_with_markdown", "tr", "Markdown ile kopyalandı.");
        put("copied_to_clipboard", "tr", "Panoya kopyalandı.");

        // Arabic
        put("copy_with_markdown", "ar", "نسخ مع Markdown");
        put("separate_buttons", "ar", "فصل الأزرار");
        put("separate_buttons_desc", "ar", "إنشاء زر منفصل للنسخ باستخدام markdown");
        put("copied_with_markdown", "ar", "تم النسخ باستخدام Markdown.");
        put("copied_to_clipboard", "ar", "تم النسخ إلى الحافظة.");

        // Hindi
        put("copy_with_markdown", "hi", "मार्कडाउन के साथ कॉपी करें");
        put("separate_buttons", "hi", "बटन अलग करें");
        put("separate_buttons_desc", "hi", "मार्कडाउन के साथ कॉपी करने के लिए एक अलग बटन बनाएं");
        put("copied_with_markdown", "hi", "मार्कडाउन के साथ कॉपी किया गया।");
        put("copied_to_clipboard", "hi", "क्लिपबोर्ड में कॉपी किया गया।");

        // Polish
        put("copy_with_markdown", "pl", "Kopiuj z Markdown");
        put("separate_buttons", "pl", "Oddziel przyciski");
        put("separate_buttons_desc", "pl", "Utwórz osobny przycisk do kopiowania z markdown");
        put("copied_with_markdown", "pl", "Skopiowano z Markdown.");
        put("copied_to_clipboard", "pl", "Skopiowano do schowka.");

        // Dutch
        put("copy_with_markdown", "nl", "Kopiëren met Markdown");
        put("separate_buttons", "nl", "Knoppen scheiden");
        put("separate_buttons_desc", "nl", "Maak een aparte knop voor kopiëren met markdown");
        put("copied_with_markdown", "nl", "Gekopieerd met Markdown.");
        put("copied_to_clipboard", "nl", "Gekopieerd naar klembord.");

        // Swedish
        put("copy_with_markdown", "sv", "Kopiera med Markdown");
        put("separate_buttons", "sv", "Separera knappar");
        put("separate_buttons_desc", "sv", "Skapa en separat knapp för att kopiera med markdown");
        put("copied_with_markdown", "sv", "Kopierat med Markdown.");
        put("copied_to_clipboard", "sv", "Kopierat till urklipp.");

        // Ukrainian
        put("copy_with_markdown", "uk", "Копіювати з Markdown");
        put("separate_buttons", "uk", "Відокремити кнопки");
        put("separate_buttons_desc", "uk", "Створити окрему кнопку для копіювання з Markdown");
        put("copied_with_markdown", "uk", "Скопійовано з Markdown.");
        put("copied_to_clipboard", "uk", "Скопійовано в буфер обміну.");

        // Romanian
        put("copy_with_markdown", "ro", "Copiază cu Markdown");
        put("separate_buttons", "ro", "Separă butoanele");
        put("separate_buttons_desc", "ro", "Creează un buton separat pentru copiere cu markdown");
        put("copied_with_markdown", "ro", "Copiat cu Markdown.");
        put("copied_to_clipboard", "ro", "Copiat în clipboard.");

        // Hungarian
        put("copy_with_markdown", "hu", "Másolás Markdown-nal");
        put("separate_buttons", "hu", "Gombok szétválasztása");
        put("separate_buttons_desc", "hu", "Hozzon létre külön gombot a markdown-nal való másoláshoz");
        put("copied_with_markdown", "hu", "Markdown-nal másolva.");
        put("copied_to_clipboard", "hu", "Vágólapra másolva.");

        // Czech
        put("copy_with_markdown", "cs", "Kopírovat s Markdown");
        put("separate_buttons", "cs", "Oddělit tlačítka");
        put("separate_buttons_desc", "cs", "Vytvořte samostatné tlačítko pro kopírování pomocí markdown");
        put("copied_with_markdown", "cs", "Zkopírováno s Markdown.");
        put("copied_to_clipboard", "cs", "Zkopírováno do schránky.");

        // Finnish
        put("copy_with_markdown", "fi", "Kopioi Markdownilla");
        put("separate_buttons", "fi", "Erottele painikkeet");
        put("separate_buttons_desc", "fi", "Luo erillinen painike markdownilla kopiointia varten");
        put("copied_with_markdown", "fi", "Kopioitu Markdownilla.");
        put("copied_to_clipboard", "fi", "Kopioitu leikepöydälle.");
    }

    private static void put(String key, String lang, String value) {
        STRINGS.computeIfAbsent(key, k -> new HashMap<>()).put(lang, value);
    }

    public static String getString(String key) {
        Map<String, String> translations = STRINGS.get(key);
        if (translations == null) return null;
        return translations.getOrDefault(Locale.getDefault().getLanguage(), translations.get("en"));
    }
}
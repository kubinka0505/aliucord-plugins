package com.github.halkiion.plugins;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Strings {
    public static final Map<String, Map<String, String>> STRINGS = new HashMap<>();

    static {
        // English
        put("pronouns_header", "en", "PRONOUNS");
        put("pronouns_hint", "en", "Enter pronouns");
        put("edit_display_name", "en", "Edit Display Name");
        put("display_name", "en", "Display Name");

        // Spanish
        put("pronouns_header", "es", "PRONOMBRES");
        put("pronouns_hint", "es", "Ingrese pronombres");
        put("edit_display_name", "es", "Editar nombre para mostrar");
        put("display_name", "es", "Nombre para mostrar");

        // French
        put("pronouns_header", "fr", "PRONOMS");
        put("pronouns_hint", "fr", "Entrez les pronoms");
        put("edit_display_name", "fr", "Modifier le nom affiché");
        put("display_name", "fr", "Nom affiché");

        // German
        put("pronouns_header", "de", "PRONOMEN");
        put("pronouns_hint", "de", "Pronomen eingeben");
        put("edit_display_name", "de", "Anzeigenamen bearbeiten");
        put("display_name", "de", "Anzeigename");

        // Russian
        put("pronouns_header", "ru", "МЕСТОИМЕНИЯ");
        put("pronouns_hint", "ru", "Введите местоимения");
        put("edit_display_name", "ru", "Изменить отображаемое имя");
        put("display_name", "ru", "Отображаемое имя");

        // Chinese
        put("pronouns_header", "zh", "代词");
        put("pronouns_hint", "zh", "输入代词");
        put("edit_display_name", "zh", "编辑显示名称");
        put("display_name", "zh", "显示名称");

        // Japanese
        put("pronouns_header", "ja", "代名詞");
        put("pronouns_hint", "ja", "代名詞を入力");
        put("edit_display_name", "ja", "表示名を編集");
        put("display_name", "ja", "表示名");

        // Korean
        put("pronouns_header", "ko", "대명사");
        put("pronouns_hint", "ko", "대명사를 입력하세요");
        put("edit_display_name", "ko", "표시 이름 편집");
        put("display_name", "ko", "표시 이름");

        // Portuguese
        put("pronouns_header", "pt", "PRONOMES");
        put("pronouns_hint", "pt", "Insira os pronomes");
        put("edit_display_name", "pt", "Editar nome de exibição");
        put("display_name", "pt", "Nome de exibição");

        // Italian
        put("pronouns_header", "it", "PRONOMI");
        put("pronouns_hint", "it", "Inserisci i pronomi");
        put("edit_display_name", "it", "Modifica nome visualizzato");
        put("display_name", "it", "Nome visualizzato");

        // Turkish
        put("pronouns_header", "tr", "ZAMİRLER");
        put("pronouns_hint", "tr", "Zamirleri girin");
        put("edit_display_name", "tr", "Görünen Adı Düzenle");
        put("display_name", "tr", "Görünen Ad");

        // Arabic
        put("pronouns_header", "ar", "الضمائر");
        put("pronouns_hint", "ar", "أدخل الضمائر");
        put("edit_display_name", "ar", "تحرير اسم العرض");
        put("display_name", "ar", "اسم العرض");

        // Hindi
        put("pronouns_header", "hi", "सर्वनाम");
        put("pronouns_hint", "hi", "सर्वनाम दर्ज करें");
        put("edit_display_name", "hi", "प्रदर्शन नाम संपादित करें");
        put("display_name", "hi", "प्रदर्शन नाम");

        // Polish
        put("pronouns_header", "pl", "ZAIMKI");
        put("pronouns_hint", "pl", "Wprowadź zaimki");
        put("edit_display_name", "pl", "Edytuj nazwę wyświetlaną");
        put("display_name", "pl", "Nazwa wyświetlana");

        // Dutch
        put("pronouns_header", "nl", "VOORNAAMWOORDEN");
        put("pronouns_hint", "nl", "Voer voornaamwoorden in");
        put("edit_display_name", "nl", "Bewerk weergavenaam");
        put("display_name", "nl", "Weergavenaam");

        // Swedish
        put("pronouns_header", "sv", "PRONOMEN");
        put("pronouns_hint", "sv", "Ange pronomen");
        put("edit_display_name", "sv", "Redigera visningsnamn");
        put("display_name", "sv", "Visningsnamn");

        // Ukrainian
        put("pronouns_header", "uk", "ЗАЙМЕННИКИ");
        put("pronouns_hint", "uk", "Введіть займенники");
        put("edit_display_name", "uk", "Редагувати відображуване ім'я");
        put("display_name", "uk", "Відображуване ім'я");

        // Romanian
        put("pronouns_header", "ro", "PRONUME");
        put("pronouns_hint", "ro", "Introduceți pronumele");
        put("edit_display_name", "ro", "Editați numele afișat");
        put("display_name", "ro", "Nume afișat");

        // Hungarian
        put("pronouns_header", "hu", "NÉVMÁSOK");
        put("pronouns_hint", "hu", "Adja meg a névmásokat");
        put("edit_display_name", "hu", "Megjelenített név szerkesztése");
        put("display_name", "hu", "Megjelenített név");

        // Czech
        put("pronouns_header", "cs", "ZÁJMENA");
        put("pronouns_hint", "cs", "Zadejte zájmena");
        put("edit_display_name", "cs", "Upravit zobrazované jméno");
        put("display_name", "cs", "Zobrazované jméno");

        // Finnish
        put("pronouns_header", "fi", "PRONOMINIT");
        put("pronouns_hint", "fi", "Anna pronominit");
        put("edit_display_name", "fi", "Muokkaa näyttönimeä");
        put("display_name", "fi", "Näyttönimi");
    }

    private static void put(String key, String lang, String value) {
        STRINGS.computeIfAbsent(key, k -> new HashMap<>()).put(lang, value);
    }

    public static String getString(String key) {
        Map<String, String> translations = STRINGS.get(key);
        if (translations == null)
            return null;
        return translations.getOrDefault(Locale.getDefault().getLanguage(), translations.get("en"));
    }
}
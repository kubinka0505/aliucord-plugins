package com.kubinka.freedom;

import android.content.Context;

import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.aliucord.patcher.Hook;
import com.aliucord.patcher.PatcherAPI;

import java.util.HashMap;
import java.util.Map;

@AliucordPlugin
public class Freedom extends Plugin {

    private final Map<Character, Character> homoglyphs = new HashMap<Character, Character>() {{
        put('a', 'а'); put('e', 'е'); put('o', 'о'); put('p', 'р');
        put('c', 'с'); put('y', 'у'); put('x', 'х');
        put('A', 'А'); put('E', 'Е'); put('O', 'О'); put('P', 'Р');
        put('C', 'С'); put('X', 'Х');
    }};

    private String replaceHomoglyphs(String input) {
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            sb.append(homoglyphs.getOrDefault(ch, ch));
        }
        return sb.toString();
    }

    @Override
    public void start(Context ctx) throws Exception {
        Class<?> clazz = Class.forName("com.discord.models.domain.ModelMessageSendRequest");

        patcher.after(clazz, "getContent", new Class[0], (Hook) call -> {
            String original = (String) call.getResult();
            String modified = replaceHomoglyphs(original);
            call.setResult(modified);
            return null;
        });
    }

    @Override
    public void stop(Context ctx) {
        patcher.unpatchAll();
    }
}

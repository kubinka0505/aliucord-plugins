package com.github.halkiion.plugins;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class UserValues {
    public static Object RNUserObj = null;

    public static String getPronouns() {
        if (RNUserObj == null)
            return null;
        try {
            Object userProfileData = RNUserObj.getClass().getMethod("getUserProfile").invoke(RNUserObj);
            Field field = userProfileData.getClass().getDeclaredField("pronouns");
            field.setAccessible(true);
            Object pronouns = field.get(userProfileData);
            return pronouns != null ? pronouns.toString() : null;
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String getDisplayName(Object user) {
        try {
            Field f = user.getClass().getDeclaredField("globalName");
            f.setAccessible(true);
            Object val = f.get(user);
            if (val != null && !val.toString().isEmpty())
                return val.toString();
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        try {
            Field f = user.getClass().getDeclaredField("username");
            f.setAccessible(true);
            Object val = f.get(user);
            if (val != null)
                return val.toString();
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return null;
    }
}
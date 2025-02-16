package com.example.vitmessapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String PREF_NAME = "YourAppPreferences";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public static boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public static void clearSessionData() {
        editor.clear();
        editor.apply();
    }
}


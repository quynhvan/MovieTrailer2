//package com.example.movietrailer.utils;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class SharedPreferencesUtils {
//    public static void setInsertState(Context context, int idMovie , boolean isMovieInserted){
//        SharedPreferences sharedpreferences = context.getSharedPreferences("insert_data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putBoolean(String.valueOf(idMovie), isMovieInserted);
//        editor.apply();
//    }
//
//    public static boolean getInsertState(Context context,int idMovie){
//        SharedPreferences sharedpreferences = context.getSharedPreferences("insert_data", Context.MODE_PRIVATE);
//        return sharedpreferences.getBoolean(String.valueOf(idMovie), false);
//    }
//
//    public static void clearSharedPreferences(Context context){
//        context.getSharedPreferences("insert_data", Context.MODE_PRIVATE).edit().clear().apply();
//    }
//}

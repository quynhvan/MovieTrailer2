package com.example.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

    @Database(entities = {Detail.class}, version = 1, exportSchema = false)
    public abstract class MovieDatabase extends RoomDatabase {
        public static MovieDatabase instance;
        public static synchronized MovieDatabase getDatabase(Context context){
            if(instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext()
                        ,MovieDatabase.class, "table_demo")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }
        public abstract MovieDAO MovieDao();
}

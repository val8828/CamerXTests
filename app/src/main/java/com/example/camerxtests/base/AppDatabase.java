package com.example.camerxtests.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.camerxtests.base.dao.AudioEntityDAO;
import com.example.camerxtests.base.dao.PhotoEntityDAO;
import com.example.camerxtests.base.dao.PublicationDAO;
import com.example.camerxtests.base.dao.UserDAO;
import com.example.camerxtests.base.pojo.AudioEntity;
import com.example.camerxtests.base.pojo.PhotoEntity;
import com.example.camerxtests.base.pojo.Publication;
import com.example.camerxtests.base.pojo.User;

@Database(entities = {User.class, Publication.class, AudioEntity.class, PhotoEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "publication";

    private static AppDatabase database;
    public abstract UserDAO userDAO();
    public abstract PublicationDAO publicationDAO();
    public abstract AudioEntityDAO audioEntityDAO();
    public abstract PhotoEntityDAO photoEntityDAO();

    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            }
            return database;
        }
    }
}


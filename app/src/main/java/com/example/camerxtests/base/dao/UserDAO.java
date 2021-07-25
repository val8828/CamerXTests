package com.example.camerxtests.base.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.camerxtests.base.pojo.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(long id);

    @Query("SELECT * FROM user WHERE login LIKE  :login")
    User getByLogin(String login);

    @Insert
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

}

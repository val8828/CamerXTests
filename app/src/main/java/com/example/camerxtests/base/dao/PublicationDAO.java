package com.example.camerxtests.base.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.camerxtests.base.pojo.Publication;

import java.util.List;

@Dao
public interface PublicationDAO {

    @Query("SELECT * FROM publication WHERE id = :id ORDER BY id")
    Publication getById(long id);

    @Query("SELECT * FROM publication WHERE userId = :userId ORDER BY id")
    List<Publication> getByUserId(long userId);

    @Insert
    void insert(Publication publication);

    @Update
    void update(Publication publication);

    @Delete
    void delete(Publication publication);
}

package com.example.camerxtests.base.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.camerxtests.base.pojo.PhotoEntity;

import java.util.List;

@Dao
public interface PhotoEntityDAO {
    @Query("SELECT * FROM photoentity WHERE id = :id ORDER BY id")
    PhotoEntity getById(long id);

    @Query("SELECT * FROM photoentity WHERE publicationId = :publicationId ORDER BY id")
    List<PhotoEntity> getByPublicationId(long publicationId);

    @Insert
    void insert(PhotoEntity photoentity);

    @Update
    void update(PhotoEntity photoentity);

    @Delete
    void delete(PhotoEntity photoentity);
}

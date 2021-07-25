package com.example.camerxtests.base.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.camerxtests.base.pojo.AudioEntity;

import java.util.List;

@Dao
public interface AudioEntityDAO {
    @Query("SELECT * FROM audioentity WHERE id = :id ORDER BY id")
    AudioEntity getById(long id);

    @Query("SELECT * FROM audioentity WHERE publicationId = :publicationId ORDER BY id")
    List<AudioEntity> getByPublicationId(long publicationId);

    @Insert
    void insert(AudioEntity audioentity);

    @Update
    void update(AudioEntity audioentity);

    @Delete
    void delete(AudioEntity audioentity);
}

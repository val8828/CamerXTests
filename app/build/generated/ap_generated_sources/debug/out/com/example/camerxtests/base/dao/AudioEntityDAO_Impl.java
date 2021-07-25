package com.example.camerxtests.base.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.camerxtests.base.pojo.AudioEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AudioEntityDAO_Impl implements AudioEntityDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AudioEntity> __insertionAdapterOfAudioEntity;

  private final EntityDeletionOrUpdateAdapter<AudioEntity> __deletionAdapterOfAudioEntity;

  private final EntityDeletionOrUpdateAdapter<AudioEntity> __updateAdapterOfAudioEntity;

  public AudioEntityDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAudioEntity = new EntityInsertionAdapter<AudioEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `AudioEntity` (`id`,`name`,`publicationId`,`location`,`created`,`type`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AudioEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getPublicationId());
        if (value.getLocation() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLocation());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCreated());
        }
        if (value.getType() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getType());
        }
      }
    };
    this.__deletionAdapterOfAudioEntity = new EntityDeletionOrUpdateAdapter<AudioEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `AudioEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AudioEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAudioEntity = new EntityDeletionOrUpdateAdapter<AudioEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `AudioEntity` SET `id` = ?,`name` = ?,`publicationId` = ?,`location` = ?,`created` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AudioEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getPublicationId());
        if (value.getLocation() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLocation());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCreated());
        }
        if (value.getType() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getType());
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public void insert(final AudioEntity audioentity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAudioEntity.insert(audioentity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final AudioEntity audioentity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAudioEntity.handle(audioentity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final AudioEntity audioentity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAudioEntity.handle(audioentity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public AudioEntity getById(final long id) {
    final String _sql = "SELECT * FROM audioentity WHERE id = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPublicationId = CursorUtil.getColumnIndexOrThrow(_cursor, "publicationId");
      final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final AudioEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new AudioEntity();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final int _tmpPublicationId;
        _tmpPublicationId = _cursor.getInt(_cursorIndexOfPublicationId);
        _result.setPublicationId(_tmpPublicationId);
        final String _tmpLocation;
        if (_cursor.isNull(_cursorIndexOfLocation)) {
          _tmpLocation = null;
        } else {
          _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
        }
        _result.setLocation(_tmpLocation);
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        _result.setCreated(_tmpCreated);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _result.setType(_tmpType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<AudioEntity> getByPublicationId(final long publicationId) {
    final String _sql = "SELECT * FROM audioentity WHERE publicationId = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, publicationId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPublicationId = CursorUtil.getColumnIndexOrThrow(_cursor, "publicationId");
      final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final List<AudioEntity> _result = new ArrayList<AudioEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AudioEntity _item;
        _item = new AudioEntity();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final int _tmpPublicationId;
        _tmpPublicationId = _cursor.getInt(_cursorIndexOfPublicationId);
        _item.setPublicationId(_tmpPublicationId);
        final String _tmpLocation;
        if (_cursor.isNull(_cursorIndexOfLocation)) {
          _tmpLocation = null;
        } else {
          _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
        }
        _item.setLocation(_tmpLocation);
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        _item.setCreated(_tmpCreated);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _item.setType(_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

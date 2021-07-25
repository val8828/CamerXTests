package com.example.camerxtests.base.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.camerxtests.base.pojo.PhotoEntity;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PhotoEntityDAO_Impl implements PhotoEntityDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PhotoEntity> __insertionAdapterOfPhotoEntity;

  private final EntityDeletionOrUpdateAdapter<PhotoEntity> __deletionAdapterOfPhotoEntity;

  private final EntityDeletionOrUpdateAdapter<PhotoEntity> __updateAdapterOfPhotoEntity;

  public PhotoEntityDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPhotoEntity = new EntityInsertionAdapter<PhotoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PhotoEntity` (`id`,`name`,`publicationId`,`location`,`description`,`created`,`type`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PhotoEntity value) {
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
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCreated());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
      }
    };
    this.__deletionAdapterOfPhotoEntity = new EntityDeletionOrUpdateAdapter<PhotoEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `PhotoEntity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PhotoEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPhotoEntity = new EntityDeletionOrUpdateAdapter<PhotoEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `PhotoEntity` SET `id` = ?,`name` = ?,`publicationId` = ?,`location` = ?,`description` = ?,`created` = ?,`type` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PhotoEntity value) {
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
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCreated());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        stmt.bindLong(8, value.getId());
      }
    };
  }

  @Override
  public void insert(final PhotoEntity photoentity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPhotoEntity.insert(photoentity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final PhotoEntity photoentity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPhotoEntity.handle(photoentity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final PhotoEntity photoentity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPhotoEntity.handle(photoentity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public PhotoEntity getById(final long id) {
    final String _sql = "SELECT * FROM photoentity WHERE id = ? ORDER BY id";
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
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final PhotoEntity _result;
      if(_cursor.moveToFirst()) {
        _result = new PhotoEntity();
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
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _result.setDescription(_tmpDescription);
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
  public List<PhotoEntity> getByPublicationId(final long publicationId) {
    final String _sql = "SELECT * FROM photoentity WHERE publicationId = ? ORDER BY id";
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
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final List<PhotoEntity> _result = new ArrayList<PhotoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PhotoEntity _item;
        _item = new PhotoEntity();
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
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
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

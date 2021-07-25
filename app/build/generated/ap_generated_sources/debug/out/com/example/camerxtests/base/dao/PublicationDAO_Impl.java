package com.example.camerxtests.base.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.camerxtests.base.pojo.Publication;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PublicationDAO_Impl implements PublicationDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Publication> __insertionAdapterOfPublication;

  private final EntityDeletionOrUpdateAdapter<Publication> __deletionAdapterOfPublication;

  private final EntityDeletionOrUpdateAdapter<Publication> __updateAdapterOfPublication;

  public PublicationDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPublication = new EntityInsertionAdapter<Publication>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Publication` (`id`,`userId`,`name`,`commonDescription`,`favorite`,`creationDate`,`modificationDate`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Publication value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getCommonDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCommonDescription());
        }
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getCreationDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCreationDate());
        }
        if (value.getModificationDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getModificationDate());
        }
      }
    };
    this.__deletionAdapterOfPublication = new EntityDeletionOrUpdateAdapter<Publication>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Publication` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Publication value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPublication = new EntityDeletionOrUpdateAdapter<Publication>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Publication` SET `id` = ?,`userId` = ?,`name` = ?,`commonDescription` = ?,`favorite` = ?,`creationDate` = ?,`modificationDate` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Publication value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getUserId());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getCommonDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCommonDescription());
        }
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        if (value.getCreationDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCreationDate());
        }
        if (value.getModificationDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getModificationDate());
        }
        stmt.bindLong(8, value.getId());
      }
    };
  }

  @Override
  public void insert(final Publication publication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPublication.insert(publication);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Publication publication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPublication.handle(publication);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Publication publication) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPublication.handle(publication);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Publication getById(final long id) {
    final String _sql = "SELECT * FROM publication WHERE id = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCommonDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "commonDescription");
      final int _cursorIndexOfFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "favorite");
      final int _cursorIndexOfCreationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "creationDate");
      final int _cursorIndexOfModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "modificationDate");
      final Publication _result;
      if(_cursor.moveToFirst()) {
        _result = new Publication();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpCommonDescription;
        if (_cursor.isNull(_cursorIndexOfCommonDescription)) {
          _tmpCommonDescription = null;
        } else {
          _tmpCommonDescription = _cursor.getString(_cursorIndexOfCommonDescription);
        }
        _result.setCommonDescription(_tmpCommonDescription);
        final boolean _tmpFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFavorite);
        _tmpFavorite = _tmp != 0;
        _result.setFavorite(_tmpFavorite);
        final String _tmpCreationDate;
        if (_cursor.isNull(_cursorIndexOfCreationDate)) {
          _tmpCreationDate = null;
        } else {
          _tmpCreationDate = _cursor.getString(_cursorIndexOfCreationDate);
        }
        _result.setCreationDate(_tmpCreationDate);
        final String _tmpModificationDate;
        if (_cursor.isNull(_cursorIndexOfModificationDate)) {
          _tmpModificationDate = null;
        } else {
          _tmpModificationDate = _cursor.getString(_cursorIndexOfModificationDate);
        }
        _result.setModificationDate(_tmpModificationDate);
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
  public List<Publication> getByUserId(final long userId) {
    final String _sql = "SELECT * FROM publication WHERE userId = ? ORDER BY id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfCommonDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "commonDescription");
      final int _cursorIndexOfFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "favorite");
      final int _cursorIndexOfCreationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "creationDate");
      final int _cursorIndexOfModificationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "modificationDate");
      final List<Publication> _result = new ArrayList<Publication>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Publication _item;
        _item = new Publication();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpCommonDescription;
        if (_cursor.isNull(_cursorIndexOfCommonDescription)) {
          _tmpCommonDescription = null;
        } else {
          _tmpCommonDescription = _cursor.getString(_cursorIndexOfCommonDescription);
        }
        _item.setCommonDescription(_tmpCommonDescription);
        final boolean _tmpFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfFavorite);
        _tmpFavorite = _tmp != 0;
        _item.setFavorite(_tmpFavorite);
        final String _tmpCreationDate;
        if (_cursor.isNull(_cursorIndexOfCreationDate)) {
          _tmpCreationDate = null;
        } else {
          _tmpCreationDate = _cursor.getString(_cursorIndexOfCreationDate);
        }
        _item.setCreationDate(_tmpCreationDate);
        final String _tmpModificationDate;
        if (_cursor.isNull(_cursorIndexOfModificationDate)) {
          _tmpModificationDate = null;
        } else {
          _tmpModificationDate = _cursor.getString(_cursorIndexOfModificationDate);
        }
        _item.setModificationDate(_tmpModificationDate);
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

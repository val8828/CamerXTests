package com.example.camerxtests.base;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.camerxtests.base.dao.AudioEntityDAO;
import com.example.camerxtests.base.dao.AudioEntityDAO_Impl;
import com.example.camerxtests.base.dao.PhotoEntityDAO;
import com.example.camerxtests.base.dao.PhotoEntityDAO_Impl;
import com.example.camerxtests.base.dao.PublicationDAO;
import com.example.camerxtests.base.dao.PublicationDAO_Impl;
import com.example.camerxtests.base.dao.UserDAO;
import com.example.camerxtests.base.dao.UserDAO_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDAO _userDAO;

  private volatile PublicationDAO _publicationDAO;

  private volatile AudioEntityDAO _audioEntityDAO;

  private volatile PhotoEntityDAO _photoEntityDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `surname` TEXT, `login` TEXT, `password` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Publication` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `name` TEXT, `commonDescription` TEXT, `favorite` INTEGER NOT NULL, `creationDate` TEXT, `modificationDate` TEXT, FOREIGN KEY(`userId`) REFERENCES `User`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AudioEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `publicationId` INTEGER NOT NULL, `location` TEXT, `created` TEXT, `type` TEXT, FOREIGN KEY(`publicationId`) REFERENCES `Publication`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PhotoEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `publicationId` INTEGER NOT NULL, `location` TEXT, `description` TEXT, `created` TEXT, `type` TEXT, FOREIGN KEY(`publicationId`) REFERENCES `Publication`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '355526ca3b7dc894a30a8c3497e46433')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `Publication`");
        _db.execSQL("DROP TABLE IF EXISTS `AudioEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `PhotoEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(5);
        _columnsUser.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("surname", new TableInfo.Column("surname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("login", new TableInfo.Column("login", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("password", new TableInfo.Column("password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "User(com.example.camerxtests.base.pojo.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsPublication = new HashMap<String, TableInfo.Column>(7);
        _columnsPublication.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPublication.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPublication.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPublication.put("commonDescription", new TableInfo.Column("commonDescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPublication.put("favorite", new TableInfo.Column("favorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPublication.put("creationDate", new TableInfo.Column("creationDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPublication.put("modificationDate", new TableInfo.Column("modificationDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPublication = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPublication.add(new TableInfo.ForeignKey("User", "CASCADE", "NO ACTION",Arrays.asList("userId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPublication = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPublication = new TableInfo("Publication", _columnsPublication, _foreignKeysPublication, _indicesPublication);
        final TableInfo _existingPublication = TableInfo.read(_db, "Publication");
        if (! _infoPublication.equals(_existingPublication)) {
          return new RoomOpenHelper.ValidationResult(false, "Publication(com.example.camerxtests.base.pojo.Publication).\n"
                  + " Expected:\n" + _infoPublication + "\n"
                  + " Found:\n" + _existingPublication);
        }
        final HashMap<String, TableInfo.Column> _columnsAudioEntity = new HashMap<String, TableInfo.Column>(6);
        _columnsAudioEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioEntity.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioEntity.put("publicationId", new TableInfo.Column("publicationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioEntity.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioEntity.put("created", new TableInfo.Column("created", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAudioEntity.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAudioEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAudioEntity.add(new TableInfo.ForeignKey("Publication", "CASCADE", "NO ACTION",Arrays.asList("publicationId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAudioEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAudioEntity = new TableInfo("AudioEntity", _columnsAudioEntity, _foreignKeysAudioEntity, _indicesAudioEntity);
        final TableInfo _existingAudioEntity = TableInfo.read(_db, "AudioEntity");
        if (! _infoAudioEntity.equals(_existingAudioEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "AudioEntity(com.example.camerxtests.base.pojo.AudioEntity).\n"
                  + " Expected:\n" + _infoAudioEntity + "\n"
                  + " Found:\n" + _existingAudioEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsPhotoEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsPhotoEntity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEntity.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEntity.put("publicationId", new TableInfo.Column("publicationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEntity.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEntity.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEntity.put("created", new TableInfo.Column("created", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotoEntity.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPhotoEntity = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPhotoEntity.add(new TableInfo.ForeignKey("Publication", "CASCADE", "NO ACTION",Arrays.asList("publicationId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPhotoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPhotoEntity = new TableInfo("PhotoEntity", _columnsPhotoEntity, _foreignKeysPhotoEntity, _indicesPhotoEntity);
        final TableInfo _existingPhotoEntity = TableInfo.read(_db, "PhotoEntity");
        if (! _infoPhotoEntity.equals(_existingPhotoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "PhotoEntity(com.example.camerxtests.base.pojo.PhotoEntity).\n"
                  + " Expected:\n" + _infoPhotoEntity + "\n"
                  + " Found:\n" + _existingPhotoEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "355526ca3b7dc894a30a8c3497e46433", "fb5e8643f9446feb105ebf2070deac8a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "User","Publication","AudioEntity","PhotoEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `Publication`");
      _db.execSQL("DELETE FROM `AudioEntity`");
      _db.execSQL("DELETE FROM `PhotoEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDAO.class, UserDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(PublicationDAO.class, PublicationDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(AudioEntityDAO.class, AudioEntityDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(PhotoEntityDAO.class, PhotoEntityDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public UserDAO userDAO() {
    if (_userDAO != null) {
      return _userDAO;
    } else {
      synchronized(this) {
        if(_userDAO == null) {
          _userDAO = new UserDAO_Impl(this);
        }
        return _userDAO;
      }
    }
  }

  @Override
  public PublicationDAO publicationDAO() {
    if (_publicationDAO != null) {
      return _publicationDAO;
    } else {
      synchronized(this) {
        if(_publicationDAO == null) {
          _publicationDAO = new PublicationDAO_Impl(this);
        }
        return _publicationDAO;
      }
    }
  }

  @Override
  public AudioEntityDAO audioEntityDAO() {
    if (_audioEntityDAO != null) {
      return _audioEntityDAO;
    } else {
      synchronized(this) {
        if(_audioEntityDAO == null) {
          _audioEntityDAO = new AudioEntityDAO_Impl(this);
        }
        return _audioEntityDAO;
      }
    }
  }

  @Override
  public PhotoEntityDAO photoEntityDAO() {
    if (_photoEntityDAO != null) {
      return _photoEntityDAO;
    } else {
      synchronized(this) {
        if(_photoEntityDAO == null) {
          _photoEntityDAO = new PhotoEntityDAO_Impl(this);
        }
        return _photoEntityDAO;
      }
    }
  }
}

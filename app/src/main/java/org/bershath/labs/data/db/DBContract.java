package org.bershath.labs.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class DBContract {
    private DBContract(){}

    public static class DBDataEntry implements BaseColumns {
        public static final String USER_TABLE_NAME = "glca_user";
        public static final String USER_CONTENT_TABLE_NAME = "glca_content";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_USERNAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_LOCALIZATION = "localization";
        public static final String COLUMN_NAME_YOUNG_OLD = "young";
        public static final String COLUMN_NAME_MULTIMEDIA_CONTENT_ID = "content_id";
        public static final String COLUMN_NAME_MULTIMEDIA_CONTENT = "content";
        public static final String COLUMN_NAME_LIKES = "likes";

    }

    private static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + DBDataEntry.USER_TABLE_NAME + " (" +
                    DBDataEntry.COLUMN_NAME_EMAIL + " TEXT PRIMARY KEY," +
                    DBDataEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    DBDataEntry.COLUMN_NAME_AGE + " INTEGER," +
                    DBDataEntry.COLUMN_NAME_LOCALIZATION + " TEXT," +
                    DBDataEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    DBDataEntry.COLUMN_NAME_MULTIMEDIA_CONTENT_ID + " TEXT," +
                    DBDataEntry.COLUMN_NAME_MULTIMEDIA_CONTENT + " BLOB,"+
                    DBDataEntry.COLUMN_NAME_LIKES + " INTEGER,"+
                    DBDataEntry.COLUMN_NAME_YOUNG_OLD + " TEXT)";


    private static final String SQL_CREATE_USER_CONTENT_ENTRIES =
            "CREATE TABLE " + DBDataEntry.USER_CONTENT_TABLE_NAME + " (" +
                    DBDataEntry.COLUMN_NAME_MULTIMEDIA_CONTENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DBDataEntry.COLUMN_NAME_MULTIMEDIA_CONTENT + " BLOB,"+
                    DBDataEntry.COLUMN_NAME_LIKES + " INTEGER," +
                    DBDataEntry.COLUMN_NAME_EMAIL + " TEXT NOT NULL," +
                    "FOREIGN KEY("+DBDataEntry.COLUMN_NAME_EMAIL+") REFERENCES " + DBDataEntry.USER_TABLE_NAME +"("+DBDataEntry.COLUMN_NAME_EMAIL+"))";

    private static final String SQL_DELETE_USER_CONTENT =
            "DROP TABLE IF EXISTS " + DBDataEntry.USER_CONTENT_TABLE_NAME;

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + DBDataEntry.USER_TABLE_NAME;


    public static class DBHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_USER_ENTRIES);
            db.execSQL(SQL_CREATE_USER_CONTENT_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_USER_CONTENT);
            db.execSQL(SQL_DELETE_USER);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }
}

package com.hy.ggank.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huyin on 2017/7/21.
 * <p>
 * 收藏
 */

public class CollectionDBHelper extends SQLiteOpenHelper {
     private static final String COLLECTION = "collection.db";

     public static final String TABLE_NAME = "collection";

     private static final int DATABASE_VERSION = 1;

     public CollectionDBHelper(Context context) {
          super(context, COLLECTION, null, DATABASE_VERSION);
     }

     @Override
     public void onCreate(SQLiteDatabase sqLiteDatabase) {
          String sql = "create table " + TABLE_NAME + "(id INTEGER primary key not null," +
                    "createdAt text,desc text,publishedAt text,source text,type text," +
                    "url text,who text,image text)";
          sqLiteDatabase.execSQL(sql);
     }

     @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          onCreate(sqLiteDatabase);
     }
}

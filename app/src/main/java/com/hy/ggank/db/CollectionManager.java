package com.hy.ggank.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by huyin on 2017/7/21.
 */

public class CollectionManager {

     private CollectionDBHelper collectionDBHelper = null;

     private SQLiteDatabase db = null;

     public CollectionManager(Context context) {
          if (collectionDBHelper == null) {
               collectionDBHelper = new CollectionDBHelper(context);
          }
     }

     private SQLiteDatabase getdb() {
          if (db == null) {
               db = collectionDBHelper.getWritableDatabase();
          }
          return db;
     }

     /**
      * 添加收藏
      *
      * @param createdAt
      * @param desc
      * @param publishedAt
      * @param source
      * @param type
      * @param who
      * @param url
      * @param image
      * @return
      */
     public boolean addCollection(String createdAt, String desc, String publishedAt,
                                  String source, String type, String who, String url, String image) {
          ContentValues contentValues = new ContentValues();
          contentValues.put("createdAt", createdAt);
          contentValues.put("desc", desc);
          contentValues.put("publishedAt", publishedAt);
          contentValues.put("source", source);
          contentValues.put("type", type);
          contentValues.put("who", who);
          contentValues.put("url", url);
          contentValues.put("image", image);
          getdb();
          long num = db.insert(CollectionDBHelper.TABLE_NAME, null, contentValues);
          if (num > 0)
               return true;
          else
               return false;
     }

     /**
      * 分页查询收藏
      *
      * @param page
      * @return
      */
     public ArrayList<CollectionModel> queryByPage(int page, int capcity) {
          ArrayList<CollectionModel> cLisArrayList = new ArrayList<CollectionModel>();
          getdb();
          Cursor cursor = db.rawQuery("select * from " + CollectionDBHelper.TABLE_NAME + " limit ?,?",
                    new String[]{String.valueOf((page - 1) * capcity), String.valueOf(capcity)});
          if (cursor != null) {
               while (cursor.moveToNext()) {
                    CollectionModel collection = new CollectionModel();
                    collection.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    collection.setCreatedAt(cursor.getString(cursor.getColumnIndex("createdAt")));
                    collection.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                    collection.setPublishedAt(cursor.getString(cursor.getColumnIndex("publishedAt")));
                    collection.setSource(cursor.getString(cursor.getColumnIndex("source")));
                    collection.setType(cursor.getString(cursor.getColumnIndex("type")));
                    collection.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                    collection.setWho(cursor.getString(cursor.getColumnIndex("who")));
                    collection.setImage(cursor.getString(cursor.getColumnIndex("image")));
                    cLisArrayList.add(collection);
               }
               cursor.close();
          }
          db.close();
          db = null;
          return cLisArrayList;
     }

     /**
      * 查询是否收藏
      *
      * @param url
      * @return
      */
     public ArrayList<CollectionModel> queryByUrl(String url) {
          ArrayList<CollectionModel> ccLisArrayList = new ArrayList<CollectionModel>();
          getdb();
          Cursor cursor = db.query(CollectionDBHelper.TABLE_NAME, null,
                    "url=?", new String[]{url}, null, null, null);
          if (cursor != null) {
               while (cursor.moveToNext()) {
                    CollectionModel collection = new CollectionModel();
                    collection.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    collection.setCreatedAt(cursor.getString(cursor.getColumnIndex("createdAt")));
                    collection.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                    collection.setPublishedAt(cursor.getString(cursor.getColumnIndex("publishedAt")));
                    collection.setSource(cursor.getString(cursor.getColumnIndex("source")));
                    collection.setType(cursor.getString(cursor.getColumnIndex("type")));
                    collection.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                    collection.setWho(cursor.getString(cursor.getColumnIndex("who")));
                    collection.setImage(cursor.getString(cursor.getColumnIndex("image")));
                    ccLisArrayList.add(collection);
               }
               cursor.close();
          }
          db.close();
          db = null;
          return ccLisArrayList;
     }

     /**
      * 查询收藏
      *
      * @return
      */
     public ArrayList<CollectionModel> queryCollection() {
          ArrayList<CollectionModel> ccLisArrayList = new ArrayList<CollectionModel>();
          getdb();
          Cursor cursor = db.query(CollectionDBHelper.TABLE_NAME, null, null, null, null, null, null);
          if (cursor != null) {
               while (cursor.moveToNext()) {
                    CollectionModel collection = new CollectionModel();
                    collection.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    collection.setCreatedAt(cursor.getString(cursor.getColumnIndex("createdAt")));
                    collection.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
                    collection.setPublishedAt(cursor.getString(cursor.getColumnIndex("publishedAt")));
                    collection.setSource(cursor.getString(cursor.getColumnIndex("source")));
                    collection.setType(cursor.getString(cursor.getColumnIndex("type")));
                    collection.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                    collection.setWho(cursor.getString(cursor.getColumnIndex("who")));
                    collection.setImage(cursor.getString(cursor.getColumnIndex("image")));
                    ccLisArrayList.add(collection);
               }
               cursor.close();
          }
          db.close();
          db = null;
          return ccLisArrayList;
     }

     /**
      * 删除某条收藏
      *
      * @param url
      * @return
      */
     public boolean delete(String url) {
          db = getdb();
          long rowId = db.delete(CollectionDBHelper.TABLE_NAME, "url=?", new String[]{url});
          if (rowId > 0)
               return true;
          db.close();
          db = null;
          return false;
     }

}

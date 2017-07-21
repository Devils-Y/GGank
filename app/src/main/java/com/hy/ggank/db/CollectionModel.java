package com.hy.ggank.db;

import java.io.Serializable;

/**
 * Created by huyin on 2017/7/21.
 */

public class CollectionModel implements Serializable{
     private int id;
     private String createdAt;
     private String desc;
     private String publishedAt;
     private String source;
     private String type;
     private String url;
     private String who;
     private String image;

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getCreatedAt() {
          return createdAt;
     }

     public String getDesc() {
          return desc;
     }

     public String getPublishedAt() {
          return publishedAt;
     }

     public String getSource() {
          return source;
     }

     public String getType() {
          return type;
     }

     public String getUrl() {
          return url;
     }

     public String getWho() {
          return who;
     }

     public String getImage() {
          return image;
     }

     public CollectionModel setCreatedAt(String createdAt) {
          this.createdAt = createdAt;
          return this;
     }

     public CollectionModel setDesc(String desc) {
          this.desc = desc;
          return this;
     }

     public CollectionModel setPublishedAt(String publishedAt) {
          this.publishedAt = publishedAt;
          return this;
     }

     public CollectionModel setSource(String source) {
          this.source = source;
          return this;
     }

     public CollectionModel setType(String type) {
          this.type = type;
          return this;
     }

     public CollectionModel setUrl(String url) {
          this.url = url;
          return this;
     }

     public CollectionModel setWho(String who) {
          this.who = who;
          return this;
     }

     public CollectionModel setImage(String image) {
          this.image = image;
          return this;
     }
}

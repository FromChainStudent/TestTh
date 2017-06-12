package com.example.testthree;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by YC on 2017/6/1.
 *
 */
@DatabaseTable(tableName = "ImgBean")
public class ImgBean {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnDefinition = "img")
    private String img;
    @DatabaseField(columnDefinition = "title")
    private String title;
    @DatabaseField(columnDefinition = "content")
    private String content;

    @Override
    public String toString() {
        return "ImgBean{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.example.keepaccount.database;

/**
 * 表示收入或者支出具体类型的类
 */

public class TypeBean {
    int id;
    String typename;//类型名称
    int imageID;//被选中的图片的ID
    int sImageID;//未被选中的图片ID
    int kind;//收入-1 支出-0

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getSImageID() {
        return sImageID;
    }

    public void setSImageID(int simageID) {
        this.sImageID = simageID;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public TypeBean() {
    }

    public TypeBean(int id, String typename, int imageID, int sImageID, int kind) {
        this.id = id;
        this.typename = typename;
        this.imageID = imageID;
        this.sImageID = sImageID;
        this.kind = kind;
    }


}

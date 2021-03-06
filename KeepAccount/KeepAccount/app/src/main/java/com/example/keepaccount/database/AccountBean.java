package com.example.keepaccount.database;

/**
 * 描述记录一条数据的相关内容类
 * 点击完成的时候，就会生成这样的一个对象
 */
public class AccountBean {
    int id;
    String typename; //类型
    int imageId; //被选中类型图片
    String remark; //备注
    float money; //钱数
    String time; //保存时间字符串
    int year;
    int month;
    int day;
    int kind; //类型 收入--1，支出--0

    public AccountBean(int id, String typename, int imageId, String remark, float money, String time, int kind) {
    }

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

    public int getImageId() { return imageId; }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public AccountBean() {
    }

    public AccountBean(int id, String typename, int imageId, String remark, float money, String time, int year, int month, int day, int kind) {
        this.id = id;
        this.typename = typename;
        this.imageId = imageId;
        this.remark = remark;
        this.money = money;
        this.time = time;
        this.year = year;
        this.month = month;
        this.day = day;
        this.kind = kind;
    }
}

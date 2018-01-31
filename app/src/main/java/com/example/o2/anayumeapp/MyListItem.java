package com.example.o2.anayumeapp;

import android.util.Log;

/**
 * SelectSheetListViewに必要なデータを取得するクラス
 * MyListItem
 */
public class MyListItem {
    protected int id;           // ID
    protected String date;           // 日付
    protected String dream;   // 品名
    /**
     * MyListItem()
     * @param id      int ID
     * @param date      String date
     * @param dream String dream
     */
    public MyListItem(int id, String date, String dream) {
        this.id = id;
        this.date = date;
        this.dream = dream;
    }

    /**
     * IDを取得
     * getId()
     *
     * @return id int ID
     */
    public int getId() {
        Log.d("取得したID：", String.valueOf(id));
        return id;
    }
    /**
     * dateを取得
     * getDate()
     * @return date String 日付
     */
    public String getDate() {
        Log.d("取得した日付：", String.valueOf(date));
        return date;
    }

    /**
     * 夢を取得
     * getDream()
     * @return dream String 夢
     */
    public String getDream() {
        return dream;
    }
}
package com.yasic.omousql.Javabean;

import io.realm.RealmObject;

/**
 * Created by Yasic on 2016/5/19.
 */
public class NewsBean extends RealmObject{
    private String title;
    private String url;
    private String detail;

    public NewsBean(){

    }

    public NewsBean(String title, String url, String detail) {
        this.title = title;
        this.url = url;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDetail() {
        return detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}


package com.jacob.circle.list;

/**
 * Created by jacob-wj on 2015/4/8.
 */
public class AppBean {

    private String name;
    private int avatar;

    public AppBean(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}

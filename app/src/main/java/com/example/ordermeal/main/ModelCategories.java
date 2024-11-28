package com.example.ordermeal.main;

import java.io.Serializable;

// 定义一个用于表示分类数据的模型类 ModelCategories，实现 Serializable 接口以支持序列化
public class ModelCategories implements Serializable {

    int icon; // 图标资源的整数 ID
    String name; // 分类名称

    public ModelCategories(int iIcon, String strName) {
        this.icon = iIcon;
        this.name = strName;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

}

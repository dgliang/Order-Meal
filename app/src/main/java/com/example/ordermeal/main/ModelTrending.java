package com.example.ordermeal.main;

public class ModelTrending {
    int imgThumb; // 缩略图资源的整数 ID，用于存储流行菜单的图片
    String tvPlaceName; // 地点或菜单名称
    String tvVote; // 投票信息或点赞数量

    public ModelTrending(int imgThumb, String tvPlaceName, String tvVote) {
        this.imgThumb = imgThumb;
        this.tvPlaceName = tvPlaceName;
        this.tvVote = tvVote;
    }

    public int getImgThumb() {
        return imgThumb;
    }

    public String getTvPlaceName() {
        return tvPlaceName;
    }

    public String getTvVote() {
        return tvVote;
    }

}

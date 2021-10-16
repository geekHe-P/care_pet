package com.example.project_1.ui.dynamic;

public class Dynamic {
    private String name; //昵称
    private int iconId; //头像
    private String content; //文字
    private int prictureId; //图片

    //private int time; //时间


    public Dynamic(String name, int iconId, String content, int prictureId) {
        this.name = name;
        this.iconId = iconId;
        this.content = content;
        this.prictureId = prictureId;
    }

    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }

    public String getContent() {
        return content;
    }

    public int getPrictureId() {
        return prictureId;
    }

/*    public int getTime() {
        return time;
    }*/
}

package com.jades.bus.pushservice.model;

/**
 * Created by wangyan on 2017/11/2.
 */

public class PushNotifyModel implements IPushNotifyModel{
    private String mTitle;
    private String mContent;
    private int mIconResId;
    private int mNotifyId;

    public PushNotifyModel(String title, String content, int iconResId, int notifyId) {
        mTitle = title;
        mContent = content;
        mIconResId = iconResId;
        mNotifyId = notifyId;
    }
    public PushNotifyModel(String title, String content, int iconResId) {
        mTitle = title;
        mContent = content;
        mIconResId = iconResId;
        mNotifyId = -1;
    }
    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getContent() {
        return mContent;
    }

    @Override
    public int getIconResId() {
        return mIconResId;
    }

    @Override
    public int getNotifyId() {
        return mNotifyId;
    }
}

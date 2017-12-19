package com.jades.bus.pushservice.model;

import java.io.Serializable;

/**
 * Created by wangyan on 2017/11/2.
 *
 * 通知显示需要的model
 */

public interface IPushNotifyModel extends Serializable{
    String getTitle();
    String getContent();
    int getIconResId();
    int getNotifyId();
}

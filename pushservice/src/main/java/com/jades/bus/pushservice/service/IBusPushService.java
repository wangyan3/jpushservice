package com.jades.bus.pushservice.service;


import com.jades.bus.common.busservice.IBusActionListener;
import com.jades.bus.common.busservice.IBusService;
import com.jades.bus.pushservice.model.IPushNotifyModel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by wangyan on 2017/12/18.
 */

public interface IBusPushService extends IBusService {
    public static final String MESSAGE_RECIEVED = "MESSAGE_RECIEVED";

    /**
     * 初始化推送
     * @param context
     */
    void initPushSdk(Context context);

    /**
     * 开启推送
     */
    void turnOnPush(Context context);

    /**
     * 关闭推送
     */
    void turnOffPush(Context context);


    /**
     * 获取推送状态
     * @return
     */
    boolean getPushStatus();

    /**
     * 上传设备对应的clientId
     * @param clientId
     * @param iBusActionListener
     */
    void onUploadClientId(String clientId, IBusActionListener iBusActionListener);

    /**
     * 接收到消息
     * @param content
     */
    void onReceivedMessage(String content);

    /**
     * 显示消息 传入的参数需要实现IPushNotifyModel
     * @param context
     * @param cls
     * @param iPushNotifyModel
     */
    void onShowPushMessage(Context context, Class<? extends Activity> cls, Bundle notifyBundle, IPushNotifyModel iPushNotifyModel);



    /**
     * 获取消息的配置信息
     * @return
     */
    IPushInfo getPushInfo();

    public interface IPushInfo {
        String getAppId();
        String getAppKey();
        String getAppSecret();
    }
}

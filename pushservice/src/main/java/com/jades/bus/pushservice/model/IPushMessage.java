package com.jades.bus.pushservice.model;


import com.jades.bus.pushservice.service.IBusMessageService;

/**
 * Created by wangyan on 2017/12/7.
 */

public interface IPushMessage extends IBusMessageService.IMessage {
     String getPushConent();
}

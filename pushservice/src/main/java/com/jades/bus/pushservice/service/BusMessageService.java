package com.jades.bus.pushservice.service;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wangyan on 2017/12/18.
 */

public class BusMessageService implements IBusMessageService {

    private SparseArray<List<IMessageProcessor>> mMessagePorcessors = new SparseArray<>();

    private List<IMessage> mIMessages = new ArrayList<>();
    @Override
    public void addMessage(IMessage message, final boolean isKeep) {
        /**
         * 消息的处理需要切换到主线程
         */
        Observable.just(message).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<IMessage>() {
            @Override
            public void call(IMessage iMessage) {
                if(isKeep){
                    mIMessages.add(iMessage);
                }

                int messageTypeClassHashCode = iMessage.getClass().hashCode();

                List<IMessageProcessor> messageProcessors = mMessagePorcessors.get(messageTypeClassHashCode);

                if(messageProcessors!=null && messageProcessors.size()>0){
                    for(IMessageProcessor messageProcessor:messageProcessors){
                        messageProcessor.process(iMessage);

                        if(iMessage.isConsumed()==true){
                            if(isKeep==true){
                                mIMessages.remove(iMessage);
                            }
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public <TMessage extends IMessage> void addMessageProcessor(Class<TMessage> messageClass, IMessageProcessor<TMessage> messageIMessageProcessor) {
        int messageClassHashCode = messageClass.hashCode();
        List<IMessageProcessor> messageProcessors = mMessagePorcessors.get(messageClassHashCode);
        if(messageProcessors == null){

            messageProcessors = new ArrayList<>();
            mMessagePorcessors.put(messageClassHashCode,messageProcessors);
        }
        messageProcessors.add(messageIMessageProcessor);

        for(IMessage message : mIMessages){

            if(message.getClass().hashCode() == messageClassHashCode) {
                messageIMessageProcessor.process((TMessage) message);
            }
        }
    }

    @Override
    public <TMessage extends IMessage> void removeMessageProcessor(Class<TMessage> messageClass, IMessageProcessor<TMessage> messageProcessor) {
        int messageClassHashCode = messageClass.hashCode();
        List<IMessageProcessor> messageProcessors = mMessagePorcessors.get(messageClassHashCode);

        if(messageProcessors != null){

            messageProcessors.remove(messageProcessor);
        }
    }

    @Override
    public String getDesc() {
        return "BusMessageService";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.jades.bus.pushservice.service;


import com.jades.bus.common.busservice.IBusService;

/**
 * message service
 * Created by wangyan on 2017/12/18.
 */

public interface IBusMessageService extends IBusService {
    /**
     * message model
     */
    public interface IMessage{
        /**
         * consumed or not
         * @return
         */
        boolean isConsumed();


        /**
         * consume message
         */
        void consume();
    }

    /**
     * add message
     * @param message
     * @param isKeep
     */
    void addMessage(IMessage message, boolean isKeep);

    /**
     * message processor
     * @param <TMessage>
     */
    interface IMessageProcessor<TMessage extends IMessage>{
        void process(TMessage message);
    }

    /**
     * add message processor
     * @param messageClass
     * @param messageIMessageProcessor
     * @param <TMessage>
     */
    <TMessage extends IMessage>void addMessageProcessor(Class<TMessage> messageClass, IMessageProcessor<TMessage> messageIMessageProcessor);


    /**
     * delete message processor
     */
    <TMessage extends IMessage>void removeMessageProcessor(Class<TMessage> messageClass, IMessageProcessor<TMessage> messageProcessor);
}

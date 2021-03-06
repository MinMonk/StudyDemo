/**
 * 
 * 文件名：JMSListener.java
 * 版权： Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述： ESB管理系统
 */
package com.monk.common.JMS.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Monk
 * @version V1.0
 * @date 2019年1月8日 下午5:37:17
 */
public class JMSListener implements MessageListener {
    
    private static Logger logger = LoggerFactory.getLogger(JMSListener.class);

    public void onMessage(Message message) {
        try {
            logger.info("收到的消息：" + ((TextMessage)message).getText());
        } catch (JMSException e) {
            logger.error("监听消息失败：" + e.getMessage(), e);
        }
        
    }

}

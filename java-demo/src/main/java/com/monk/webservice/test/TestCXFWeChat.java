/**
 * 
 * 文件名：TestWeiChat.java
 * 版权： Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述： ESB管理系统
 */
package com.monk.webservice.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monk.common.utils.ObjectUtils;
import com.monk.webservice.cxf.SapPushWebServiceClient;


/**
 * 企业微信调用测试类
 * @author Monk
 * @version V1.0
 * @date 2019年4月28日 下午5:54:37
 */
public class TestCXFWeChat {

    private static final Logger logger = LoggerFactory.getLogger(TestCXFWeChat.class);

    /**
     * 发送消息成功
     */
    private static final String SEND_SUCCESS = "Y";

    /**
     * 发送消息失败
     */
    private static final String SEND_FAILED = "N";
    
    
    public static void main(String[] args) throws Exception {
        List<AlarmNoticeUser> userList = new ArrayList<AlarmNoticeUser>();
        sendWx("testContent", userList);
    }
    
    private static String sendWx(String content, List<AlarmNoticeUser> userList) {
        String noticeFlag = SEND_SUCCESS;

        try {
            String toUser = getToWeCharUser(userList);
            logger.info("send to weixin user : " + toUser);

            SapPushWebServiceClient client = new SapPushWebServiceClient();
            Map<String, Object> result = client.sendWx(content, toUser);
            if (result == null) {
                noticeFlag = SEND_FAILED;
                logger.warn("invoke send wechat end, but return result is null.");
            } else {
                String errCode = ObjectUtils.praseObjectToString(result.get("errcode"));
                String errMsg = ObjectUtils.praseObjectToString(result.get("errmsg"));
                logger.error("errCode = [" + errCode + "], errMsg = [" + errMsg + "].");
                if (StringUtils.isNotBlank(errCode) && errCode.equals("0")) {
                    if (StringUtils.isNotBlank(errMsg) && errMsg.equals("ok")) {
                        noticeFlag = SEND_SUCCESS;
                        logger.info("send wechat message success!");
                    } else {
                        noticeFlag = SEND_SUCCESS;
                        logger.info("Sending WeChat message partially successful, part of the failure! ");
                    }
                } else {
                    noticeFlag = SEND_FAILED;
                    logger.error("send wechat message failed!");
                }

                validateSendResult(result);
            }
        } catch (Exception e) {
            noticeFlag = SEND_FAILED;
            logger.error("Send WeChat message error. " + e.getMessage());
        }
        return noticeFlag;
    }
    
    /**
     * 校验发送微信通知结果
     * 
     * @param map
     *            通知结果
     * @author Monk
     * @date 2019年5月7日 下午3:38:52
     */
    private static void validateSendResult(Map<String, Object> map) {
        logger.info("Invoke method validateSendResult() start....  map = " + map);
        String invaliduser = ObjectUtils.praseObjectToString(map.get("invaliduser"));
        String invalidparty = ObjectUtils.praseObjectToString(map.get("invalidparty"));
        String invalidtag = ObjectUtils.praseObjectToString(map.get("invalidtag"));
        StringBuffer errorMsg = new StringBuffer();
        if (StringUtils.isNotBlank(invaliduser)) {
            errorMsg.append("invalid user list : [" + invaliduser + "]. ");
        }
        if (StringUtils.isNotBlank(invalidparty)) {
            errorMsg.append("invalid party list : [" + invalidparty + "]. ");
        }
        if (StringUtils.isNotBlank(invalidtag)) {
            errorMsg.append("invalid tag list : [" + invalidtag + "]. ");
        }
        String msg = errorMsg.toString();
        if (msg.length() > 0) {
            logger.error(errorMsg.toString());
        }
        logger.info("Invoke method validateSendResult() end....");
    }

    /*
    *
    * 去掉重复的微信收件人
    * 
    * @param userList
    *            通知用户列表
    * @return 通知用户微信号码
     * @author Monk
     * @date 2019年5月27日 下午4:05:53
     */
    private static String getToWeCharUser(List<AlarmNoticeUser> userList) {
        String receiverUser = "";
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < userList.size(); i++) {
            set.add(userList.get(i).getWeixin());
        }
        for (String string : set) {
            receiverUser += string + "|";
        }
        receiverUser = receiverUser.substring(0, receiverUser.length() - 1);
        return receiverUser;
    }
}

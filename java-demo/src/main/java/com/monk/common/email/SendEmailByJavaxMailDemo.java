/**
 * 
 * 文件名：SendEmailDemo.java
 * 版权： Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述： ESB管理系统
 */
package com.monk.common.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 使用javaxMail发送邮件Demo
 * 
 * @author Monk
 * @version V1.0
 * @date 2019年5月22日 下午5:39:26
 */
public class SendEmailByJavaxMailDemo {

    public static void main(String[] args) {

        List<String> userList = new ArrayList<String>();
        userList.add("wangliang@vispractice.com");
        userList.add("wangliang@vispractice.com");
        userList.add("459506421@qq.com");

        String content = generateEmailContent();

        try {
            sendMail("服务告警通知", content, userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造邮件正文
     * 
     * @return 邮件正文
     * @author Monk
     * @date 2019年5月27日 下午4:05:20
     */
    private static String generateEmailContent() {
        StringBuilder content = new StringBuilder("<html><head></head><body>");
        content.append("<p>您好！海大集团ESB&SOA管控平台发生如下告警，统计区间：");
        content.append("${startTime}至${endTime}（一个周期），请及时登录系统处理：http://soa.haid.com.cn</p>");
        content.append(
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\"  style=\"border:solid 1px #000000;border-collapse: collapse;\">");
        content.append("<tr style=\"background-color: #5A9BD5; color:#000000\">");
        content.append("<th>服务英文名</th><th>服务中文名</th>");
        content.append("<th>异常信息</th><th>提供方</th><th>消费方</th>");
        content.append("</tr>");
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                content.append("<tr style=\"background-color: #DDEBF7; color:#000000\">");
            } else {
                content.append("<tr style=\"background-color: #FFFFFF; color:#000000\">");
            }
            content.append("<td>" + "11111" + "</td>");
            content.append("<td>" + "22222" + "</td>");
            content.append("<td>" + "33333" + "</td>");
            content.append("<td>" + "44444" + "</td>");
            content.append("<td>" + "55555" + "</td>");
            content.append("</tr>");
        }

        content.append("</table>");
        content.append("</body></html>");
        return content.toString();
    }

    /**
     * 发送邮件
     * 
     * @param subject
     *            邮件主题
     * @param content
     *            邮件内容（支持HTML）
     * @param toEmailAddres
     *            收件人
     * @throws Exception
     * @author Monk
     * @date 2019年5月22日 下午6:27:27
     */
    private static void sendMail(String subject, String content, List<String> userList) throws Exception {

        InternetAddress[] toEmailAddres = getToEmailUser(userList);

        String host = "smtp.vispractice.com";
        String port = "25";
        String auth = "false";
        String protocol = "smtp";
        String mailFrom = "soatest1@vispractice.com";
        String personalName = "soa";
        String username = "soatest1@vispractice.com";
        String password = "AAaa12345";
        String mailDebug = "false";
        String contentType = null;

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", auth == null ? "true" : auth);
        props.put("mail.transport.protocol", protocol == null ? "smtp" : protocol);
        props.put("mail.smtp.port", port == null ? "25" : port);
        props.put("mail.debug", mailDebug == null ? "false" : mailDebug);
        Session mailSession = Session.getInstance(props);

        // 设置session,和邮件服务器进行通讯。
        MimeMessage message = new MimeMessage(mailSession);
        // 设置邮件主题
        message.setSubject(subject);
        // 设置邮件正文
        message.setContent(content, contentType == null ? "text/html;charset=UTF-8" : contentType);
        // 设置邮件发送日期
        message.setSentDate(new Date());
        InternetAddress address = new InternetAddress(mailFrom, personalName);
        // 设置邮件发送者的地址
        message.setFrom(address);
        // 设置邮件接收方的地址
        message.setRecipients(Message.RecipientType.TO, toEmailAddres);
        Transport transport = null;
        transport = mailSession.getTransport();
        message.saveChanges();

        transport.connect(host, username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private static InternetAddress[] getToEmailUser(List<String> userList) {
        // List<InternetAddress> list = new ArrayList<InternetAddress>();
        InternetAddress[] list = null;
        try {
            Set<String> set = new HashSet<String>();
            for (int i = 0; i < userList.size(); i++) {
                set.add(userList.get(i));
            }
            list = new InternetAddress[set.size()];
            int i = 0;
            for (Iterator iterator = set.iterator(); iterator.hasNext();) {
                String string = (String) iterator.next();
                InternetAddress address = new InternetAddress(string);
                list[i] = address;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }

        return list;
    }

}

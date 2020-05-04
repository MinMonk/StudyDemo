package com.monk.security.propertites;

/**
 * @ClassName SocialProperties
 * @Description: TODO
 * @Author Monk
 * @Date 2020/5/3
 * @Version V1.0
 **/
public class SocialProperties {

    private String processesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    public String getProcessesUrl() {
        return processesUrl;
    }

    public void setProcessesUrl(String processesUrl) {
        this.processesUrl = processesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}

package com.monk.webservice.cxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>sappushwebservice complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="sappushwebservice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pushaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="touser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toparty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textcontent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textcardtitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textcarddescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textcardurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="textcardbtntxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="safe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sappushwebservice", propOrder = {
    "pushaddress",
    "touser",
    "toparty",
    "totag",
    "msgtype",
    "textcontent",
    "textcardtitle",
    "textcarddescription",
    "textcardurl",
    "textcardbtntxt",
    "safe"
})
public class Sappushwebservice_Type {

    protected String pushaddress;
    protected String touser;
    protected String toparty;
    protected String totag;
    protected String msgtype;
    protected String textcontent;
    protected String textcardtitle;
    protected String textcarddescription;
    protected String textcardurl;
    protected String textcardbtntxt;
    protected String safe;

    /**
     * 获取pushaddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPushaddress() {
        return pushaddress;
    }

    /**
     * 设置pushaddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPushaddress(String value) {
        this.pushaddress = value;
    }

    /**
     * 获取touser属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTouser() {
        return touser;
    }

    /**
     * 设置touser属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTouser(String value) {
        this.touser = value;
    }

    /**
     * 获取toparty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToparty() {
        return toparty;
    }

    /**
     * 设置toparty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToparty(String value) {
        this.toparty = value;
    }

    /**
     * 获取totag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotag() {
        return totag;
    }

    /**
     * 设置totag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotag(String value) {
        this.totag = value;
    }

    /**
     * 获取msgtype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * 设置msgtype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgtype(String value) {
        this.msgtype = value;
    }

    /**
     * 获取textcontent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextcontent() {
        return textcontent;
    }

    /**
     * 设置textcontent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextcontent(String value) {
        this.textcontent = value;
    }

    /**
     * 获取textcardtitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextcardtitle() {
        return textcardtitle;
    }

    /**
     * 设置textcardtitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextcardtitle(String value) {
        this.textcardtitle = value;
    }

    /**
     * 获取textcarddescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextcarddescription() {
        return textcarddescription;
    }

    /**
     * 设置textcarddescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextcarddescription(String value) {
        this.textcarddescription = value;
    }

    /**
     * 获取textcardurl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextcardurl() {
        return textcardurl;
    }

    /**
     * 设置textcardurl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextcardurl(String value) {
        this.textcardurl = value;
    }

    /**
     * 获取textcardbtntxt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextcardbtntxt() {
        return textcardbtntxt;
    }

    /**
     * 设置textcardbtntxt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextcardbtntxt(String value) {
        this.textcardbtntxt = value;
    }

    /**
     * 获取safe属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSafe() {
        return safe;
    }

    /**
     * 设置safe属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSafe(String value) {
        this.safe = value;
    }

}

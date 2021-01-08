/**
 * 
 * 文件名：TestFormatDeciaml.java
 * 版权： Copyright 2017-2022 CMCC All Rights Reserved.
 * 描述： ESB管理系统
 */
package com.monk.test;

import java.text.DecimalFormat;

/**
 *
 * @author Monk
 * @version V1.0
 * @date 2021年1月5日 上午9:12:58
 */
public class TestFormatDeciaml {
    public static void main(String[] args) {
        
        String value = null;
        Double val = Double.valueOf(value);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(val/1024) + "GB");;
    }

}

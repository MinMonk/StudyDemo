package com.monk.test;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.predic8.wsdl.Binding;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wstool.creator.RequestCreator;
import com.predic8.wstool.creator.RequestTemplateCreator;
import com.predic8.wstool.creator.SOARequestCreator;

import groovy.xml.MarkupBuilder;

public class TestStr {
    
    private static final Logger logger = LoggerFactory.getLogger(TestStr.class);
    
    public static void main(String[] args) {
        String wsdlUrl = "http://10.204.105.128:8011/SOA/OSB_BP_SOA_HQ_ImportPickingOrderRouteSrv.v0/proxy/OSB_BP_SOA_HQ_ImportPickingOrderRouteSrv?wsdl";
        wsdlUrl = "http://10.204.105.129:9011/DAS/services/CommonDAS?wsdl";
        test1(wsdlUrl);
    }


    /**
     * @param wsdlUrl
     * @author Monk
     * @date 2020年9月11日 下午6:00:17
     */
    private static void test1(String wsdlUrl) {
        WSDLParser parser = new WSDLParser();
        Definitions wsdl = parser.parse(wsdlUrl);
        StringWriter writer = new StringWriter();
        SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestTemplateCreator(), new MarkupBuilder(writer));

        // 针对new RequestCreator()可以赋值参数
        creator.setCreator(new RequestCreator());
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("xpath:/InputParameters/SERVICE_NAME_EN", "?");
        formParams.put("xpath:/InputParameters/MAJOR_VERSION", "?");
        formParams.put("xpath:/InputParameters/DATA_CONFIG", "?");
        formParams.put("xpath:/InputParameters/CONFIG_VERSION", "?");
        formParams.put("xpath:/InputParameters/INPUT_JSON", "?");
        formParams.put("xpath:/InputParameters/PAGE_SIZE", "?");
        formParams.put("xpath:/InputParameters/CURRENT_PAGE", "?");
        formParams.put("xpath:/InputParameters/TOTAL_RECORD", "?");
        creator.setFormParams(formParams);

        for (Service service : wsdl.getServices()) {
            for (Port port : service.getPorts()) {
                Binding binding = port.getBinding();
                PortType portType = binding.getPortType();
                for (Operation op : portType.getOperations()) {
                    creator.createRequest(port.getName(), op.getName(), binding.getName());
                    System.out.println(writer);
                    writer.getBuffer().setLength(0);
                }
            }
        }
    }
}
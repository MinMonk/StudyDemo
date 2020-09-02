package com.monk.webservice.cxf;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2019-05-14T14:22:31.813+08:00
 * Generated source version: 3.0.2
 * 
 */
@WebServiceClient(name = "sapPushWebServiceImplService", 
                  wsdlLocation = "http://qy.haid.com.cn/qywxWSServer/webservice/sapPushWebService?wsdl",
                  targetNamespace = "http://server.qywxWSServer.haid.com.cn/") 
public class SapPushWebServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://server.qywxWSServer.haid.com.cn/", "sapPushWebServiceImplService");
    public final static QName SapPushWebServiceImplPort = new QName("http://server.qywxWSServer.haid.com.cn/", "sapPushWebServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://qy.haid.com.cn/qywxWSServer/webservice/sapPushWebService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SapPushWebServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://qy.haid.com.cn/qywxWSServer/webservice/sapPushWebService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SapPushWebServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SapPushWebServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SapPushWebServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SapPushWebServiceImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SapPushWebServiceImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SapPushWebServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns SapPushWebService
     */
    @WebEndpoint(name = "sapPushWebServiceImplPort")
    public SapPushWebService getSapPushWebServiceImplPort() {
        return super.getPort(SapPushWebServiceImplPort, SapPushWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SapPushWebService
     */
    @WebEndpoint(name = "sapPushWebServiceImplPort")
    public SapPushWebService getSapPushWebServiceImplPort(WebServiceFeature... features) {
        return super.getPort(SapPushWebServiceImplPort, SapPushWebService.class, features);
    }

}

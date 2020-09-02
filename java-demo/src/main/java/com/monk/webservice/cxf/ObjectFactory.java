
package com.monk.webservice.cxf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.haida.qywxWSServer.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Sappushwebservice_QNAME = new QName("http://server.qywxWSServer.haid.com.cn/", "sappushwebservice");
    private final static QName _SappushwebserviceResponse_QNAME = new QName("http://server.qywxWSServer.haid.com.cn/", "sappushwebserviceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.haida.qywxWSServer.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SappushwebserviceResponse }
     * 
     */
    public SappushwebserviceResponse createSappushwebserviceResponse() {
        return new SappushwebserviceResponse();
    }

    /**
     * Create an instance of {@link Sappushwebservice_Type }
     * 
     */
    public Sappushwebservice_Type createSappushwebservice_Type() {
        return new Sappushwebservice_Type();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sappushwebservice_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.qywxWSServer.haid.com.cn/", name = "sappushwebservice")
    public JAXBElement<Sappushwebservice_Type> createSappushwebservice(Sappushwebservice_Type value) {
        return new JAXBElement<Sappushwebservice_Type>(_Sappushwebservice_QNAME, Sappushwebservice_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SappushwebserviceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.qywxWSServer.haid.com.cn/", name = "sappushwebserviceResponse")
    public JAXBElement<SappushwebserviceResponse> createSappushwebserviceResponse(SappushwebserviceResponse value) {
        return new JAXBElement<SappushwebserviceResponse>(_SappushwebserviceResponse_QNAME, SappushwebserviceResponse.class, null, value);
    }

}

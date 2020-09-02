
package com.monk.commondas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.haida.soa.commondas package. 
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

    private final static QName _OutputParameters_QNAME = new QName("http://soa.haid.com/CommonDAS", "OutputParameters");
    private final static QName _InputParameters_QNAME = new QName("http://soa.haid.com/CommonDAS", "InputParameters");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.haida.soa.commondas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OutputParameters }
     * 
     */
    public OutputParameters createOutputParameters() {
        return new OutputParameters();
    }

    /**
     * Create an instance of {@link InputParameters }
     * 
     */
    public InputParameters createInputParameters() {
        return new InputParameters();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutputParameters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soa.haid.com/CommonDAS", name = "OutputParameters")
    public JAXBElement<OutputParameters> createOutputParameters(OutputParameters value) {
        return new JAXBElement<OutputParameters>(_OutputParameters_QNAME, OutputParameters.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InputParameters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soa.haid.com/CommonDAS", name = "InputParameters")
    public JAXBElement<InputParameters> createInputParameters(InputParameters value) {
        return new JAXBElement<InputParameters>(_InputParameters_QNAME, InputParameters.class, null, value);
    }

}

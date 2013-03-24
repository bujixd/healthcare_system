//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.30 at 06:20:11 ���� EST 
//


package edu.stevens.cs548.clinic.service.web.rest.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for specialization.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="specialization">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="surgery"/>
 *     &lt;enumeration value="radiology"/>
 *     &lt;enumeration value="oncology"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "specialization")
@XmlEnum
public enum Specialization {

    @XmlEnumValue("surgery")
    SURGERY("surgery"),
    @XmlEnumValue("radiology")
    RADIOLOGY("radiology"),
    @XmlEnumValue("oncology")
    ONCOLOGY("oncology");
    private final String value;

    Specialization(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Specialization fromValue(String v) {
        for (Specialization c: Specialization.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
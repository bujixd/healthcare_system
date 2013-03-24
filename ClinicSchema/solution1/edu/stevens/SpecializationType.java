//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.15 at 05:39:56 PM EDT 
//


package edu.stevens;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SpecializationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SpecializationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="raidology"/>
 *     &lt;enumeration value="surgery"/>
 *     &lt;enumeration value="oncology"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SpecializationType", namespace = "http://www.example.org/schema/clinic/provider")
@XmlEnum
public enum SpecializationType {

    @XmlEnumValue("raidology")
    RAIDOLOGY("raidology"),
    @XmlEnumValue("surgery")
    SURGERY("surgery"),
    @XmlEnumValue("oncology")
    ONCOLOGY("oncology");
    private final String value;

    SpecializationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpecializationType fromValue(String v) {
        for (SpecializationType c: SpecializationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

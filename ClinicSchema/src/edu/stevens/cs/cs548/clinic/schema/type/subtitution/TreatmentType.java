//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.16 at 09:04:05 PM EDT 
//


package edu.stevens.cs.cs548.clinic.schema.type.subtitution;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TreatmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TreatmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provide-id" type="{http://www.example.org/schema/clinic/clinic_ids}ProvideIdType"/>
 *         &lt;element name="diagnosis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TreatmentType", propOrder = {
    "provideId",
    "diagnosis"
})
@XmlSeeAlso({
    RadiologyType.class,
    DrugTreatmentType.class,
    SurgeryType.class
})
public class TreatmentType {

    @XmlElement(name = "provide-id")
    protected long provideId;
    @XmlElement(required = true)
    protected String diagnosis;

    /**
     * Gets the value of the provideId property.
     * 
     */
    public long getProvideId() {
        return provideId;
    }

    /**
     * Sets the value of the provideId property.
     * 
     */
    public void setProvideId(long value) {
        this.provideId = value;
    }

    /**
     * Gets the value of the diagnosis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the value of the diagnosis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosis(String value) {
        this.diagnosis = value;
    }

}
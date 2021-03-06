//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.16 at 10:14:26 AM EDT 
//


package edu.stevens.cs.cs548.clinic.schemathird;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.stevens.cs.cs548.clinic.schemathird package. 
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

    private final static QName _TreatmentType_QNAME = new QName("http://www.example.org/Patient", "treatmentType");
    private final static QName _RadiologyTreatmentType_QNAME = new QName("http://www.example.org/Patient", "radiologyTreatmentType");
    private final static QName _Clinic_QNAME = new QName("http://www.example.org/Clinic", "clinic");
    private final static QName _DrugTreatmentType_QNAME = new QName("http://www.example.org/Patient", "drugTreatmentType");
    private final static QName _SurgeryTreatmentType_QNAME = new QName("http://www.example.org/Patient", "surgeryTreatmentType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.stevens.cs.cs548.clinic.schemathird
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ClinicType }
     * 
     */
    public ClinicType createClinicType() {
        return new ClinicType();
    }

    /**
     * Create an instance of {@link SurgeryTreatmentType }
     * 
     */
    public SurgeryTreatmentType createSurgeryTreatmentType() {
        return new SurgeryTreatmentType();
    }

    /**
     * Create an instance of {@link DrugTreatmentType }
     * 
     */
    public DrugTreatmentType createDrugTreatmentType() {
        return new DrugTreatmentType();
    }

    /**
     * Create an instance of {@link RadiologyTreatmentType }
     * 
     */
    public RadiologyTreatmentType createRadiologyTreatmentType() {
        return new RadiologyTreatmentType();
    }

    /**
     * Create an instance of {@link TreatmentType }
     * 
     */
    public TreatmentType createTreatmentType() {
        return new TreatmentType();
    }

    /**
     * Create an instance of {@link PatientType }
     * 
     */
    public PatientType createPatientType() {
        return new PatientType();
    }

    /**
     * Create an instance of {@link ProviderType }
     * 
     */
    public ProviderType createProviderType() {
        return new ProviderType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TreatmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Patient", name = "treatmentType")
    public JAXBElement<TreatmentType> createTreatmentType(TreatmentType value) {
        return new JAXBElement<TreatmentType>(_TreatmentType_QNAME, TreatmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RadiologyTreatmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Patient", name = "radiologyTreatmentType")
    public JAXBElement<RadiologyTreatmentType> createRadiologyTreatmentType(RadiologyTreatmentType value) {
        return new JAXBElement<RadiologyTreatmentType>(_RadiologyTreatmentType_QNAME, RadiologyTreatmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClinicType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Clinic", name = "clinic")
    public JAXBElement<ClinicType> createClinic(ClinicType value) {
        return new JAXBElement<ClinicType>(_Clinic_QNAME, ClinicType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DrugTreatmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Patient", name = "drugTreatmentType")
    public JAXBElement<DrugTreatmentType> createDrugTreatmentType(DrugTreatmentType value) {
        return new JAXBElement<DrugTreatmentType>(_DrugTreatmentType_QNAME, DrugTreatmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurgeryTreatmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/Patient", name = "surgeryTreatmentType")
    public JAXBElement<SurgeryTreatmentType> createSurgeryTreatmentType(SurgeryTreatmentType value) {
        return new JAXBElement<SurgeryTreatmentType>(_SurgeryTreatmentType_QNAME, SurgeryTreatmentType.class, null, value);
    }

}

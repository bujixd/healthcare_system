/**
 * GetTreatmentsResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.stevens.cs548.clinic.service.web.soap;

public class GetTreatmentsResponseReturn  implements java.io.Serializable {
    private java.lang.String diagnosis;

    private edu.stevens.cs548.clinic.service.web.soap.DrugTreatmentType drugTreatment;

    private java.util.Date[] radiology;

    private edu.stevens.cs548.clinic.service.web.soap.SurgeryType surgery;

    public GetTreatmentsResponseReturn() {
    }

    public GetTreatmentsResponseReturn(
           java.lang.String diagnosis,
           edu.stevens.cs548.clinic.service.web.soap.DrugTreatmentType drugTreatment,
           java.util.Date[] radiology,
           edu.stevens.cs548.clinic.service.web.soap.SurgeryType surgery) {
           this.diagnosis = diagnosis;
           this.drugTreatment = drugTreatment;
           this.radiology = radiology;
           this.surgery = surgery;
    }


    /**
     * Gets the diagnosis value for this GetTreatmentsResponseReturn.
     * 
     * @return diagnosis
     */
    public java.lang.String getDiagnosis() {
        return diagnosis;
    }


    /**
     * Sets the diagnosis value for this GetTreatmentsResponseReturn.
     * 
     * @param diagnosis
     */
    public void setDiagnosis(java.lang.String diagnosis) {
        this.diagnosis = diagnosis;
    }


    /**
     * Gets the drugTreatment value for this GetTreatmentsResponseReturn.
     * 
     * @return drugTreatment
     */
    public edu.stevens.cs548.clinic.service.web.soap.DrugTreatmentType getDrugTreatment() {
        return drugTreatment;
    }


    /**
     * Sets the drugTreatment value for this GetTreatmentsResponseReturn.
     * 
     * @param drugTreatment
     */
    public void setDrugTreatment(edu.stevens.cs548.clinic.service.web.soap.DrugTreatmentType drugTreatment) {
        this.drugTreatment = drugTreatment;
    }


    /**
     * Gets the radiology value for this GetTreatmentsResponseReturn.
     * 
     * @return radiology
     */
    public java.util.Date[] getRadiology() {
        return radiology;
    }


    /**
     * Sets the radiology value for this GetTreatmentsResponseReturn.
     * 
     * @param radiology
     */
    public void setRadiology(java.util.Date[] radiology) {
        this.radiology = radiology;
    }


    /**
     * Gets the surgery value for this GetTreatmentsResponseReturn.
     * 
     * @return surgery
     */
    public edu.stevens.cs548.clinic.service.web.soap.SurgeryType getSurgery() {
        return surgery;
    }


    /**
     * Sets the surgery value for this GetTreatmentsResponseReturn.
     * 
     * @param surgery
     */
    public void setSurgery(edu.stevens.cs548.clinic.service.web.soap.SurgeryType surgery) {
        this.surgery = surgery;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetTreatmentsResponseReturn)) return false;
        GetTreatmentsResponseReturn other = (GetTreatmentsResponseReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.diagnosis==null && other.getDiagnosis()==null) || 
             (this.diagnosis!=null &&
              this.diagnosis.equals(other.getDiagnosis()))) &&
            ((this.drugTreatment==null && other.getDrugTreatment()==null) || 
             (this.drugTreatment!=null &&
              this.drugTreatment.equals(other.getDrugTreatment()))) &&
            ((this.radiology==null && other.getRadiology()==null) || 
             (this.radiology!=null &&
              java.util.Arrays.equals(this.radiology, other.getRadiology()))) &&
            ((this.surgery==null && other.getSurgery()==null) || 
             (this.surgery!=null &&
              this.surgery.equals(other.getSurgery())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDiagnosis() != null) {
            _hashCode += getDiagnosis().hashCode();
        }
        if (getDrugTreatment() != null) {
            _hashCode += getDrugTreatment().hashCode();
        }
        if (getRadiology() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRadiology());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRadiology(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSurgery() != null) {
            _hashCode += getSurgery().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetTreatmentsResponseReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://cs548.stevens.edu/clinic/service/web/soap", ">getTreatmentsResponse>return"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diagnosis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "diagnosis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("drugTreatment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "drug-treatment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://cs548.stevens.edu/clinic/service/web/soap", "DrugTreatmentType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("radiology");
        elemField.setXmlName(new javax.xml.namespace.QName("", "radiology"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "date"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surgery");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surgery"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://cs548.stevens.edu/clinic/service/web/soap", "SurgeryType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

/**
 * PatientDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.stevens.cs548.clinic.service.web.soap;

public class PatientDTO  implements java.io.Serializable {
    private long id;

    private long patientId;

    private java.lang.String name;

    private java.util.Calendar dob;

    private java.lang.Long[] treatments;

    public PatientDTO() {
    }

    public PatientDTO(
           long id,
           long patientId,
           java.lang.String name,
           java.util.Calendar dob,
           java.lang.Long[] treatments) {
           this.id = id;
           this.patientId = patientId;
           this.name = name;
           this.dob = dob;
           this.treatments = treatments;
    }


    /**
     * Gets the id value for this PatientDTO.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this PatientDTO.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the patientId value for this PatientDTO.
     * 
     * @return patientId
     */
    public long getPatientId() {
        return patientId;
    }


    /**
     * Sets the patientId value for this PatientDTO.
     * 
     * @param patientId
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }


    /**
     * Gets the name value for this PatientDTO.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PatientDTO.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the dob value for this PatientDTO.
     * 
     * @return dob
     */
    public java.util.Calendar getDob() {
        return dob;
    }


    /**
     * Sets the dob value for this PatientDTO.
     * 
     * @param dob
     */
    public void setDob(java.util.Calendar dob) {
        this.dob = dob;
    }


    /**
     * Gets the treatments value for this PatientDTO.
     * 
     * @return treatments
     */
    public java.lang.Long[] getTreatments() {
        return treatments;
    }


    /**
     * Sets the treatments value for this PatientDTO.
     * 
     * @param treatments
     */
    public void setTreatments(java.lang.Long[] treatments) {
        this.treatments = treatments;
    }

    public java.lang.Long getTreatments(int i) {
        return this.treatments[i];
    }

    public void setTreatments(int i, java.lang.Long _value) {
        this.treatments[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PatientDTO)) return false;
        PatientDTO other = (PatientDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            this.patientId == other.getPatientId() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.dob==null && other.getDob()==null) || 
             (this.dob!=null &&
              this.dob.equals(other.getDob()))) &&
            ((this.treatments==null && other.getTreatments()==null) || 
             (this.treatments!=null &&
              java.util.Arrays.equals(this.treatments, other.getTreatments())));
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
        _hashCode += new Long(getId()).hashCode();
        _hashCode += new Long(getPatientId()).hashCode();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDob() != null) {
            _hashCode += getDob().hashCode();
        }
        if (getTreatments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTreatments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTreatments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PatientDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://cs548.stevens.edu/clinic/service/web/soap", "patientDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("patientId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "patient-id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dob");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dob"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("treatments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "treatments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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

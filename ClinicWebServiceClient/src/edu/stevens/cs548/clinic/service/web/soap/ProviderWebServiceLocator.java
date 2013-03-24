/**
 * ProviderWebServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.stevens.cs548.clinic.service.web.soap;

public class ProviderWebServiceLocator extends org.apache.axis.client.Service implements edu.stevens.cs548.clinic.service.web.soap.ProviderWebService {

    public ProviderWebServiceLocator() {
    }


    public ProviderWebServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProviderWebServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProviderPort
    private java.lang.String ProviderPort_address = "http://ec2-67-202-33-210.compute-1.amazonaws.com:8080/ClinicWebService/ProviderWebService";

    public java.lang.String getProviderPortAddress() {
        return ProviderPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProviderPortWSDDServiceName = "ProviderPort";

    public java.lang.String getProviderPortWSDDServiceName() {
        return ProviderPortWSDDServiceName;
    }

    public void setProviderPortWSDDServiceName(java.lang.String name) {
        ProviderPortWSDDServiceName = name;
    }

    public edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort getProviderPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProviderPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProviderPort(endpoint);
    }

    public edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort getProviderPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            edu.stevens.cs548.clinic.service.web.soap.ProviderPortBindingStub _stub = new edu.stevens.cs548.clinic.service.web.soap.ProviderPortBindingStub(portAddress, this);
            _stub.setPortName(getProviderPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProviderPortEndpointAddress(java.lang.String address) {
        ProviderPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort.class.isAssignableFrom(serviceEndpointInterface)) {
                edu.stevens.cs548.clinic.service.web.soap.ProviderPortBindingStub _stub = new edu.stevens.cs548.clinic.service.web.soap.ProviderPortBindingStub(new java.net.URL(ProviderPort_address), this);
                _stub.setPortName(getProviderPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ProviderPort".equals(inputPortName)) {
            return getProviderPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://cs548.stevens.edu/clinic/service/web/soap", "ProviderWebService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://cs548.stevens.edu/clinic/service/web/soap", "ProviderPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProviderPort".equals(portName)) {
            setProviderPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

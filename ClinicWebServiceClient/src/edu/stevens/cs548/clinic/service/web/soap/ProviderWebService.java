/**
 * ProviderWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.stevens.cs548.clinic.service.web.soap;

public interface ProviderWebService extends javax.xml.rpc.Service {
    public java.lang.String getProviderPortAddress();

    public edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort getProviderPort() throws javax.xml.rpc.ServiceException;

    public edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort getProviderPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

/**
 * IProviderWebPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.stevens.cs548.clinic.service.web.soap;

public interface IProviderWebPort extends java.rmi.Remote {
    public long createProvider(long arg0, java.lang.String arg1) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.ProviderServiceExn;
    public edu.stevens.cs548.clinic.service.web.soap.ProviderDTO getProviderByNPI(long arg0) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.ProviderNotFoundExn, edu.stevens.cs548.clinic.service.web.soap.ProviderServiceExn;
    public java.lang.String siteInfo() throws java.rmi.RemoteException;
    public edu.stevens.cs548.clinic.service.web.soap.GetTreatmentDtoIdsResponseReturn[] getTreatmentDtoIds(java.lang.Long[] arg0) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.TreatmentNotFoundExn;
    public java.lang.Long[] getTreatmentIds() throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.ProviderNotFoundExn;
    public edu.stevens.cs548.clinic.service.web.soap.ProviderDTO[] getProviderByName(java.lang.String arg0) throws java.rmi.RemoteException;
}

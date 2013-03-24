package edu.stevens.cs548.clinic.service.web.soap;

public class IProviderWebPortProxy implements edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort {
  private String _endpoint = null;
  private edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort iProviderWebPort = null;
  
  public IProviderWebPortProxy() {
    _initIProviderWebPortProxy();
  }
  
  public IProviderWebPortProxy(String endpoint) {
    _endpoint = endpoint;
    _initIProviderWebPortProxy();
  }
  
  private void _initIProviderWebPortProxy() {
    try {
      iProviderWebPort = (new edu.stevens.cs548.clinic.service.web.soap.ProviderWebServiceLocator()).getProviderPort();
      if (iProviderWebPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iProviderWebPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iProviderWebPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iProviderWebPort != null)
      ((javax.xml.rpc.Stub)iProviderWebPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.IProviderWebPort getIProviderWebPort() {
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort;
  }
  
  public long createProvider(long arg0, java.lang.String arg1) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.ProviderServiceExn{
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort.createProvider(arg0, arg1);
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.ProviderDTO getProviderByNPI(long arg0) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.ProviderNotFoundExn, edu.stevens.cs548.clinic.service.web.soap.ProviderServiceExn{
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort.getProviderByNPI(arg0);
  }
  
  public java.lang.String siteInfo() throws java.rmi.RemoteException{
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort.siteInfo();
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.GetTreatmentDtoIdsResponseReturn[] getTreatmentDtoIds(java.lang.Long[] arg0) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.TreatmentNotFoundExn{
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort.getTreatmentDtoIds(arg0);
  }
  
  public java.lang.Long[] getTreatmentIds() throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.ProviderNotFoundExn{
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort.getTreatmentIds();
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.ProviderDTO[] getProviderByName(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iProviderWebPort == null)
      _initIProviderWebPortProxy();
    return iProviderWebPort.getProviderByName(arg0);
  }
  
  
}
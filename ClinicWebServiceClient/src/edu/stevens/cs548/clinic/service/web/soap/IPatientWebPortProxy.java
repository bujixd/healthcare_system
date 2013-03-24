package edu.stevens.cs548.clinic.service.web.soap;

public class IPatientWebPortProxy implements edu.stevens.cs548.clinic.service.web.soap.IPatientWebPort {
  private String _endpoint = null;
  private edu.stevens.cs548.clinic.service.web.soap.IPatientWebPort iPatientWebPort = null;
  
  public IPatientWebPortProxy() {
    _initIPatientWebPortProxy();
  }
  
  public IPatientWebPortProxy(String endpoint) {
    _endpoint = endpoint;
    _initIPatientWebPortProxy();
  }
  
  private void _initIPatientWebPortProxy() {
    try {
      iPatientWebPort = (new edu.stevens.cs548.clinic.service.web.soap.PatientWebServiceLocator()).getPatientPort();
      if (iPatientWebPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iPatientWebPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iPatientWebPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iPatientWebPort != null)
      ((javax.xml.rpc.Stub)iPatientWebPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.IPatientWebPort getIPatientWebPort() {
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort;
  }
  
  public long create(java.lang.String arg0, java.util.Calendar arg1, long arg2) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientServiceExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort.create(arg0, arg1, arg2);
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.PatientDTO getPatientByDbId(long arg0) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientServiceExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort.getPatientByDbId(arg0);
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.PatientDTO[] getPatientsByNameDob(java.lang.String arg0, java.util.Calendar arg1) throws java.rmi.RemoteException{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort.getPatientsByNameDob(arg0, arg1);
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.PatientDTO getPatientByPatId(long arg0) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientServiceExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort.getPatientByPatId(arg0);
  }
  
  public void deletePatient(java.lang.String arg0, long arg1) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientServiceExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    iPatientWebPort.deletePatient(arg0, arg1);
  }
  
  public void addDrugTreatment(long arg0, long arg1, java.lang.String arg2, java.lang.String arg3, float arg4) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientNotFoundExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    iPatientWebPort.addDrugTreatment(arg0, arg1, arg2, arg3, arg4);
  }
  
  public void addRaidology(long arg0, long arg1, java.lang.String arg2, java.util.Calendar[] arg3) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientNotFoundExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    iPatientWebPort.addRaidology(arg0, arg1, arg2, arg3);
  }
  
  public edu.stevens.cs548.clinic.service.web.soap.GetTreatmentsResponseReturn[] getTreatments(long arg0, java.lang.Long[] arg1) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientServiceExn, edu.stevens.cs548.clinic.service.web.soap.TreatmentNotFoundExn, edu.stevens.cs548.clinic.service.web.soap.PatientNotFoundExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort.getTreatments(arg0, arg1);
  }
  
  public void deleteTreatment(long arg0, long arg1) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientServiceExn, edu.stevens.cs548.clinic.service.web.soap.TreatmentNotFoundExn, edu.stevens.cs548.clinic.service.web.soap.PatientNotFoundExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    iPatientWebPort.deleteTreatment(arg0, arg1);
  }
  
  public java.lang.String siteInfo() throws java.rmi.RemoteException{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    return iPatientWebPort.siteInfo();
  }
  
  public void addSurgery(long arg0, long arg1, java.lang.String arg2, java.util.Calendar arg3) throws java.rmi.RemoteException, edu.stevens.cs548.clinic.service.web.soap.PatientNotFoundExn{
    if (iPatientWebPort == null)
      _initIPatientWebPortProxy();
    iPatientWebPort.addSurgery(arg0, arg1, arg2, arg3);
  }
  
  
}
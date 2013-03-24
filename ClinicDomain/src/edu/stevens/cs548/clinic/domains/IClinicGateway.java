package edu.stevens.cs548.clinic.domains;

public interface IClinicGateway {

	public IPatientFactory getPatientFactory();
	
	public IPatientDAO getPatientDAO();
	
	public IProviderFactory getProviderFactory();
	
	public IProviderDAO getProviderDAO();
}

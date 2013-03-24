package edu.stevens.cs548.clinic.domains;

public interface IProviderFactory {

	public Provider createProvider(long npi, String name,Specialization specialization);
	
	public Provider createProviders(long npi,String name);
}

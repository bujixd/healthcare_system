package edu.stevens.cs548.clinic.domains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClinicGateway implements IClinicGateway {

	@Override
	public IPatientFactory getPatientFactory() {
		return new PatientFactory();
	}

	private EntityManagerFactory emf;
	@Override
	public IPatientDAO getPatientDAO() {
		EntityManager em = emf.createEntityManager();
		return new PatientDAO(em);
	}
	
	public ClinicGateway(){
		emf = Persistence.createEntityManagerFactory("ClinicDomains");
	}

	@Override
	public IProviderFactory getProviderFactory() {
		return new ProviderFactory();
	}

	@Override
	public IProviderDAO getProviderDAO() {
		EntityManager em = emf.createEntityManager();
		return new ProviderDAO(em);
	}

}

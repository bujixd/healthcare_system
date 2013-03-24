package edu.stevens.cs548.clinic.domains;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.stevens.cs548.clinic.domains.IPatientDAO.PatientExn;



public class ProviderDAO implements IProviderDAO {

	EntityManager em;
	private TreatmentDAO treatmentDAO;
	@Override
	public Provider getProviderByNPI(long npi) throws ProviderExn {
		TypedQuery<Provider> query = 
				em.createNamedQuery("SearchProviderByNPI", Provider.class)
				.setParameter("npi", npi);
		List<Provider> providers = query.getResultList();
		if(providers.size() > 1)
			throw new ProviderExn("Duplicate provider records : npi = " + npi);
		else if (providers.size() < 1)
			throw new ProviderExn("Provider not found : npi " + npi);
		else{
			Provider p = providers.get(0);
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}

	@Override
	public List<Provider> getProviderByName(String name) {
		TypedQuery<Provider> query = 
				em.createNamedQuery("SearchProviderByName", Provider.class)
				.setParameter("name", name);
		List<Provider> providers = query.getResultList();
		for(Provider provider : providers)
			provider.setTreatmentDAO(treatmentDAO);
		return providers;
	}

	@Override
	public void addProvider(Provider pro) throws ProviderExn {
		long npi = pro.getNpi();
		String name = pro.getName();
		TypedQuery<Provider> query = 
				em.createNamedQuery("SearchProviderByNameNPI", Provider.class)
				.setParameter("npi", npi)
				.setParameter("name", name);
		List<Provider> providers = query.getResultList();
		if(providers.size() < 1){
			em.persist(pro);
			pro.setTreatmentDAO(treatmentDAO);
			} else {
				Provider provider2 = providers.get(0);
				throw new ProviderExn("Insertion: Provider with NPI (" + npi
							+ ") already exisit.\n** name:  " + provider2.getName());
			}
	}

	@Override
	public void deleteProvider(Provider pro) throws ProviderExn {
		em.remove(pro);

	}

	public ProviderDAO(EntityManager em){
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}
	
}

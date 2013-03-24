package edu.stevens.cs548.clinic.domains;

import javax.persistence.EntityManager;

public class TreatmentDAO implements ITreatmentDAO {

	public TreatmentDAO(EntityManager em){
		
	}
	
	private EntityManager em;
	@Override
	public Treatment getTreatmentByDbId(long id) throws TreatmentExn {
		Treatment t = em.find(Treatment.class, id);
		if(t == null){
			throw new TreatmentExn("Missing treatment: id = " + id);
		}else{
			return t;
		}
	}

	@Override
	public void addTreatment(Treatment t) {
		em.persist(t);

	}
	
	@Override
	public void deleteTreatment(Treatment t){
		em.remove(t);
	}

}

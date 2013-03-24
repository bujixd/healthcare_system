package edu.stevens.cs548.clinic.domains;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



public class PatientDAO implements IPatientDAO {

	
	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	@Override
	public Patient getPatbientByPatientId(long pid) throws PatientExn {
		TypedQuery<Patient> query = 
				em.createNamedQuery("SearchPatientByPatientID", Patient.class)
				.setParameter("pid", pid);
		List<Patient> patients = query.getResultList();
		if(patients.size() > 1)
			throw new PatientExn("Duplicate patient records : pastient id = " + pid);
		else if (patients.size() < 1)
			throw new PatientExn("Patient not found : patient id " + pid);
		else{
			Patient p = patients.get(0);
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}

	@Override
	public Patient getPatientByDbId(long id) throws PatientExn {
		Patient p = em.find(Patient.class, id);
		if(p == null){
			throw new PatientExn("Patient not found : primary key11111 =" + id);
		}else{
			p.setTreatmentDAO(treatmentDAO);
			return p;
		}
	}
	

	@Override
	public List<Patient> getPatientByNameDob(String name, Date dob) {
		TypedQuery<Patient> query = 
				em.createNamedQuery("SearchPatientByNameDOB", Patient.class)
				.setParameter("name", name)
				.setParameter("dob", dob);
		List<Patient> patients = query.getResultList();
		for (Patient p : patients){
			p.setTreatmentDAO(this.treatmentDAO);
		}
		return patients;
	}

	@Override
	public void addPatient(Patient pat) throws PatientExn {
		long pid = pat.getPatientID();
		TypedQuery<Patient> query = 
				em.createNamedQuery("SearchPatientByPatientID", Patient.class)
				.setParameter("pid", pid);
		List<Patient> patients = query.getResultList();
		if(patients.size() < 1){
		em.persist(pat);
		pat.setTreatmentDAO(this.treatmentDAO);
		} else {
			Patient patient2 = patients.get(0);
			throw new PatientExn("Insertion: Patient with patient id (" + pid
						+ ") already exisit.\n** name:  " + patient2.getName());
		}
	}

	@Override
	public void deletePatient(Patient pat) throws PatientExn {
		em.createQuery("delete from Treatment t where t.patient.id = :id")
			.setParameter("id", pat.getId())
			.executeUpdate();
		em.remove(pat);
	}
	
	public PatientDAO(EntityManager em){
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

}

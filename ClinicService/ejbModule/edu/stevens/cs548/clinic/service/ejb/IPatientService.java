package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import edu.stevens.cs548.clinic.domains.Provider;
import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public interface IPatientService {

	public class PatientServiceExn extends Exception{
		private static final long serialVersionUID = 1L;

		public PatientServiceExn(String m){
			super(m);
		}
	}
	
	public class PatientNotFoundExn extends PatientServiceExn{
		public PatientNotFoundExn(String m){
			super(m);
		}
	}
	
	public class TreatmentNotFoundExn extends PatientServiceExn{
		public TreatmentNotFoundExn(String m){
			super(m);
		}
	}
	
	public long createPatient(String name,Date  dob,long patientId) throws PatientServiceExn;
	
	public PatientDTO getPatientByDbId(long id) throws PatientServiceExn;
	
	public PatientDTO getPatientByPatId(long pid) throws PatientServiceExn;

	public PatientDTO[] getPatientsByNameDob(String name, Date dob);
	
	public void deletePatient(String name, long id) throws PatientServiceExn;

	public long addDrugTreatment(long id,long npi,String diagnosis, String drug, float dosage)throws PatientNotFoundExn;
	
	public long addRaidology(long id,long npi,String diagnosis,List<Date> date) throws PatientNotFoundExn;
	
	public long addSurgert(long id,long npi, String diagnosis,Date date) throws PatientNotFoundExn;

	public TreatmentDto[] getTreatments(long id, long[] tids) throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;

	public void deleteTreatment(long id, long tid)throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;

	public String siteInfo();
}


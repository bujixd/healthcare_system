package edu.stevens.cs548.clinic.domains;

import java.util.Date;
import java.util.List;

public interface IPatientDAO {

	public static class PatientExn extends Exception {
		private static final long serialVersionUID =1L;
		public PatientExn(String msg){
			super(msg);
		}
	}
	
	public Patient getPatbientByPatientId(long pid )throws PatientExn;
	
	public Patient getPatientByDbId(long id)throws PatientExn;
	
	public List<Patient> getPatientByNameDob(String name, Date dob); 
	
	public void addPatient(Patient pat)throws PatientExn;
	
	public void deletePatient(Patient pat) throws PatientExn;

	
}


package edu.stevens.cs548.clinic.domains;

import java.util.Date;

public class PatientFactory implements IPatientFactory {

	@Override
	public Patient createPatient(long pid, String name, Date dob) {
		Patient p = new Patient();
		p.setPatientID(pid);
		p.setName(name);
		p.setBrithDate(dob);
		return p;
	}

}

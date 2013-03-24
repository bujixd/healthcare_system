package edu.stevens.cs548.clinic.service.dto;


import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.domains.Patient;

@XmlRootElement(name ="patient-dto", namespace ="http://cs548.stevens.edu/clinic/service/web")
public class PatientDTO {

	public long id;
		
		@XmlElement(name = "patient-id")
		public long patientID;
		
		@XmlElement(required = true)
		public String name;
		
		@XmlElement(required = true,name = "dob")
		public Date birthDate;
		
		public long[] treatments;
		
		public PatientDTO(){}
		
		public PatientDTO (Patient patient) {
			this.id = patient.getId();
			this.patientID = patient.getPatientID();
			this.name = patient.getName();
			this.birthDate = patient.getBrithDate();
			List<Long> tids = patient.getTreatmentIds();
			this.treatments = new long[tids.size()];
			for (int i = 0;i<treatments.length; i++){
				this.treatments[i] = tids.get(i);
			}
		}
		
	}

	


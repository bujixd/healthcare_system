package edu.stevens.cs548.clinic.service.web.soap;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.domains.Provider;
import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.TreatmentNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceRemote;

@WebService(
		endpointInterface="edu.stevens.cs548.clinic.service.web.soap.IPatientWebService",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap",
		serviceName="PatientWebService",
		portName="PatientPort")
public class PatientWebService { 
			//implements IPatientWebService {

	@EJB(beanName="PatientServiceBean")
	IPatientServiceRemote service;
	
	public long createPatient(String name, Date dob, long patientId)
			throws PatientServiceExn {
		// TODO Auto-generated method stub
		return service.createPatient(name, dob, patientId);
	}

	public PatientDTO getPatientByDbId(long id) throws PatientServiceExn {
		// TODO Auto-generated method stub
		return service.getPatientByDbId(id);
	}

	public PatientDTO getPatientByPatId(long pid) throws PatientServiceExn {
		// TODO Auto-generated method stub
		return service.getPatientByPatId(pid);
	}

	public PatientDTO[] getPatientsByNameDob(String name, Date dob) {
		// TODO Auto-generated method stub
		return service.getPatientsByNameDob(name, dob);
	}

	public void deletePatient(String name, long id) throws PatientServiceExn {
		// TODO Auto-generated method stub
		service.deletePatient(name, id);
	}

	public void addDrugTreatment(long id,long npi, String diagnosis, String drug,
			float dosage) throws PatientNotFoundExn {
		// TODO Auto-generated method stub
		this.service.addDrugTreatment(id,npi, diagnosis, drug, dosage);
	}
	public void addRaidology(long id,long npi, String diagnosis, List<Date> date)
			throws PatientNotFoundExn {
		this.service.addRaidology(id,npi, diagnosis, date);
		
	}

	public void addSurgery(long id,long npi, String diagnosis, Date date)
			throws PatientNotFoundExn {
		this.service.addSurgert(id, npi, diagnosis, date);
		
	}

	public TreatmentDto[] getTreatments(long id, long[] tids)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// TODO Auto-generated method stub
		return service.getTreatments(id, tids);
	}

	public void deleteTreatment(long id, long tid) throws PatientNotFoundExn,
			TreatmentNotFoundExn, PatientServiceExn {
		// TODO Auto-generated method stub
		this.service.deleteTreatment(tid, tid);
	}
	
	public String siteInfo() {
		// TODO Auto-generated method stub
		return service.siteInfo();
	}


	
}

package edu.stevens.cs548.clinic.service.web.soap;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.domains.Provider;
import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.TreatmentNotFoundExn;

@WebService(
		name="IPatientWebPort",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap")

public interface IPatientWebService {
	
	@WebMethod(operationName="create")
	public long createPatient(String name,Date  dob,long patientId) throws PatientServiceExn;
	
	@WebMethod
	public PatientDTO getPatientByDbId(long id) throws PatientServiceExn;
	
	@WebMethod
	public PatientDTO getPatientByPatId(long pid) throws PatientServiceExn;
	
	@WebMethod
	public PatientDTO[] getPatientsByNameDob(String name, Date dob);
	
	@WebMethod
	public void deletePatient(String name, long id) throws PatientServiceExn;

	@WebMethod
	public void addDrugTreatment(long id,long npi,String diagnosis, String drug, float dosage)throws PatientNotFoundExn;

	@WebMethod
	public void addRaidology(long id,long npi,String diagnosis,List<Date> date) throws PatientNotFoundExn;
	
	@WebMethod
	public void addSurgery(long id,long npi, String diagnosis,Date date) throws PatientNotFoundExn;
	
	@WebMethod
	public TreatmentDto[] getTreatments(long id, long[] tids) throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;

	@WebMethod
	public void deleteTreatment(long id, long tid)throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn;

	@WebMethod
	public String siteInfo();
}

package edu.stevens.cs548.clinic.service.ejb;

import java.nio.file.ProviderNotFoundException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domains.IPatientDAO;
import edu.stevens.cs548.clinic.domains.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domains.IProviderDAO;
import edu.stevens.cs548.clinic.domains.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domains.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domains.ITreatmentVisitor;
import edu.stevens.cs548.clinic.domains.Patient;
import edu.stevens.cs548.clinic.domains.PatientDAO;
import edu.stevens.cs548.clinic.domains.PatientFactory;
import edu.stevens.cs548.clinic.domains.Provider;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name="PatientServiceBean")
public class PatientService implements IPatientServiceRemote,
		IPatientServiceLocal {

	private PatientFactory patientFactory;

	private IPatientDAO patientDAO;
	
	private IProviderDAO providerDAO;

	/**
	 * Default constructor.
	 */
	public PatientService() {
		patientFactory = new PatientFactory();
	}

	@PersistenceContext(unitName = "ClinicDomain")
	private EntityManager em;

	@PostConstruct
	private void initialize() {
		patientDAO = new PatientDAO(em);
	}

	/**
	 * @throws PatientServiceExn 
	 * @see IPatientService#createPatient(String, Date, long)
	 */
	public long createPatient(String name, Date dob, long patientId) throws PatientServiceExn {
		Patient patient = this.patientFactory.createPatient(patientId, name, dob);
		try {
			patientDAO.addPatient(patient);
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
		return patient.getId();
	}

	/**
	 * @see IPatientService#getPatient(long)
	 */
	public PatientDTO getPatientByDbId(long id) throws PatientServiceExn {
		try {
			Patient patient = patientDAO.getPatientByDbId(id);
			return new PatientDTO(patient);
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatientsByNameDob(String, Date)
	 */
	public PatientDTO[] getPatientsByNameDob(String name, Date dob) {
		// TODO Auto-generated method stub
		List<Patient> patients = patientDAO.getPatientByNameDob(name, dob);
		PatientDTO[] dto = new PatientDTO[patients.size()];
		for (int i = 0; i < dto.length; i++) {
			dto[i] = new PatientDTO(patients.get(i));
		}
		return dto;
	}

	/**
	 * @see IPatientService#getPatientByPatId(long)
	 */
	public PatientDTO getPatientByPatId(long pid) throws PatientServiceExn {
		try {
			Patient patient = patientDAO.getPatbientByPatientId(pid);
			return new PatientDTO(patient);
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * 
	 * @see IPatientService#deletePatient(String, long)
	 */
	public void deletePatient(String name, long id) throws PatientServiceExn {
		try {
			Patient patient = patientDAO.getPatientByDbId(id);
			if (!name.equals(patient.getName())) {
				throw new PatientServiceExn(
						"Tried to delete wrong patient: name = " + name
								+ " , id = " + id);
			}else{
				patientDAO.deletePatient(patient);
			}
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	@Override
	public long addDrugTreatment(long id, long npi,String diagnosis, String drug,
			float dosage) throws PatientNotFoundExn {
		try{
			Patient patient = patientDAO.getPatientByDbId(id);
			Provider provider = providerDAO.getProviderByNPI(npi);
			return patient.addDrugTreatment(diagnosis, drug, dosage, provider);
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}catch(ProviderExn e){
			throw new ProviderNotFoundException(e.toString());
		}
	}
	
	@Override
	public long addRaidology(long id,long npi, String diagnosis, List<Date> date)
			throws PatientNotFoundExn {
		try {
			Patient patient = patientDAO.getPatientByDbId(id);
			Provider provider = providerDAO.getProviderByNPI(npi);
			return patient.addRadiology(diagnosis, date, provider);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		}catch(ProviderExn e){
			throw new ProviderNotFoundException(e.toString());
		}
	}

	@Override
	public long addSurgert(long id,long npi, String diagnosis, Date date)
			throws PatientNotFoundExn {
		try{
			Patient patient = patientDAO.getPatientByDbId(id);
			Provider provider = providerDAO.getProviderByNPI(npi);
			return patient.addSurery(diagnosis, date, provider);
		} catch (PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		} catch(ProviderExn e){
			throw new ProviderNotFoundException(e.toString());
		}
		
	}
	

	static class TreatmentPDOtoDTO implements ITreatmentVisitor{

		private TreatmentDto dto;
		public TreatmentDto getDTO(){
			return dto;
		}
		
		@Override
		public void visitDrugTreatment(long tid, String diagnosis, String drug,
				float dosage, Provider provider) {
			// TODO Auto-generated method stub
			dto = new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			DrugTreatmentType drugInfo = new DrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setName(drug);
			
			dto.setDrugTreatment(drugInfo);
		}

		@Override
		public void visitRadiology(long tid, String diagnosis, List<Date> dates, Provider provider) {
			// TODO Auto-generated method stub
			dto = new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			RadiologyType radiologyInfo = new RadiologyType();
			radiologyInfo.getDate().addAll(dates);
			dto.setRadiology(radiologyInfo);
		}

		@Override
		public void visitSurgery(long tid, String diagnosis, Date date, Provider provider) {
			dto = new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			SurgeryType surgeryInfo = new SurgeryType();
			surgeryInfo.setDate(date);
			dto.setSurgery(surgeryInfo);
		}
		
	}
	
	@Override
	public TreatmentDto[] getTreatments(long id, long[] tids)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		try{
			Patient patient = patientDAO.getPatientByDbId(id);
			TreatmentDto[] treatments = new TreatmentDto[tids.length];
			for (int i=0; i < tids.length; i++){
				TreatmentPDOtoDTO visitor = new TreatmentPDOtoDTO();
				patient.visitTreatment(tids[i], visitor);
				treatments[i] = visitor.getDTO();
			}
			return treatments;
		} catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		} catch(TreatmentExn e){
			throw new PatientServiceExn(e.toString());
		}
	}

	@Override
	public void deleteTreatment(long id, long tid) throws PatientNotFoundExn,
			TreatmentNotFoundExn, PatientServiceExn {
		try{
			Patient  patient = patientDAO.getPatientByDbId(id);
			patient.deleteTreatment(tid);
		}catch(PatientExn e){
			throw new PatientNotFoundExn(e.toString());
		}catch(TreatmentExn e){
			throw new TreatmentNotFoundExn(e.toString());
		}
		
	}
	
	@Resource(name="SiteInfo")
	private String siteInformation;

	@Override
	public String siteInfo() {
		// TODO Auto-generated method stub
		return siteInformation;
	}

}

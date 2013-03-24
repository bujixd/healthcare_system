package edu.stevens.cs548.clinic.domains;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.stevens.cs548.clinic.domains.Treatment;
import edu.stevens.cs548.clinic.domains.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Patient
 *
 */
@Entity
@Table(name = "PATIENT")
@NamedQueries({
	@NamedQuery(name="SearchPatientByNameDOB",
			query = "select p from Patient p where p.name = :name and p.BrithDate = :dob"),	
	@NamedQuery(name="SearchPatientByPatientID",
			query = "select p from Patient p where p.PatientID = :pid")
	})

public class Patient implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private long PatientID;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date BrithDate;
	@OneToMany(cascade = REMOVE, mappedBy = "patient")
	@OrderBy
	private List<Treatment> treatment;

	public Patient() {
		super();
		treatment = new ArrayList<Treatment>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientID() {
		return PatientID;
	}

	public void setPatientID(long patientID) {
		PatientID = patientID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBrithDate() {
		return BrithDate;
	}

	public void setBrithDate(Date brithDate) {
		BrithDate = brithDate;
	}

	protected List<Treatment> getTreatment() {
		return treatment;
	}

	protected void setTreatment(List<Treatment> treatment) {
		this.treatment = treatment;
	}
	
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO(ITreatmentDAO tdao){
		this.treatmentDAO = tdao;
	}
	
	public void addTreatment(Treatment t){
		this.treatmentDAO.addTreatment(t);
		this.getTreatment().add(t);
		if(t.getPatient()!= this)
			t.setPatient(this);
	}
   
	public long  addDrugTreatment(String diagnosis,String durg, float dosage, Provider provider){
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDosage(dosage);
		treatment.setDrug(durg);
		treatment.setProvider(provider);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	public long addRadiology(String diagnosis,List<Date> date, Provider provider){
		RaidologyTreatment radiology = new RaidologyTreatment();
		radiology.setDiagnosis(diagnosis);
		radiology.setDate(date);
		radiology.setProvider(provider);
		this.addTreatment(radiology);
		return radiology.getId();
	}
	
	public long addSurery(String diagnosis,Date date, Provider provider){
		SurgeryTreatment surgery = new SurgeryTreatment();
		surgery.setDiagnosis(diagnosis);
		surgery.setDate(date);
		surgery.setProvider(provider);
		this.addTreatment(surgery);
		return surgery.getId();
		}
	
	public List<Long> getTreatmentIds(){
		List<Long> tids = new ArrayList<Long>();
		for(Treatment t : this.getTreatment()){
			tids.add(t.getId());
		}
		return tids;
	}
	
	public void visitTreatment(long tid , ITreatmentVisitor visitor) throws TreatmentExn{
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getPatient() != this){
			throw new TreatmentExn("Inappropriate treatment access : patient" + id + 
									" treatment = " + tid);
		}
		t.visit(visitor);
	}
	
	public void visitTreatments(ITreatmentVisitor visitor){
		for(Treatment t : this.getTreatment()){
			t.visit(visitor);
		}
	}

	public void deleteTreatment(long tid)throws TreatmentExn{
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getPatient() == this){
			throw new TreatmentExn("Inappropriate treatment access : patient" + id + 
									" treatment = " + tid);
		}
		treatmentDAO.deleteTreatment(t);
	}
}



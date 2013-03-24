package edu.stevens.cs548.clinic.domains;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.stevens.cs548.clinic.domains.Treatment;
import edu.stevens.cs548.clinic.domains.ITreatmentDAO.TreatmentExn;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

/**
 * Entity implementation class for Entity: Provider
 *
 */
@Entity
@Table(name = "PROVIDER")
@NamedQueries({
	@NamedQuery(name="SearchProviderByNPI",
			query = "select p from Provider p where p.npi = :npi"),
	@NamedQuery(name="SearchProviderByNameNPI",
			query = "select p from Provider p where p.name = :name and p.npi = :npi"),
	@NamedQuery(name="SearchProviderByName",
			query = "select p from Provider p where p.name = :name"),
		
	})
public class Provider implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Column(name ="NPI")
	private long npi;
	private String name;
	@OneToMany(cascade = REMOVE, mappedBy = "provider")
	@OrderBy
	private List<Treatment> treatment;
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	
	public Provider() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Treatment> getTreatment() {
		return treatment;
	}

	public void setTreatment(List<Treatment> treatment) {
		this.treatment = treatment;
	}


	public long getNpi() {
		return npi;
	}

	public void setNpi(long npi) {
		this.npi = npi;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO(ITreatmentDAO tdao){
		this.treatmentDAO = tdao;
	}
	
	void addTreatment(Treatment t){
		if(!treatment.contains(t)){
			treatment.add(t);
		}
		if(t.getProvider()!=this){
			t.setProvider(this);
		}
		treatmentDAO.addTreatment(t);
	}
	
	public void createDrugTreatment(String diagnosis, String drug, float dosage, Patient patient){
		DrugTreatment dt=new DrugTreatment();
		dt.setDiagnosis(diagnosis);
		dt.setDrug(drug);
		dt.setDosage(dosage);
		dt.setPatient(patient);
		addTreatment(dt);
	}
	
	public void createSurgeryTreatment(String diagnosis, Date surgeryDate, Patient patient){
		SurgeryTreatment st=new SurgeryTreatment();
		st.setDiagnosis(diagnosis);
		st.setDate(surgeryDate);
		st.setPatient(patient);
		addTreatment(st);
	}
	
	public void createRadiologyTreatment(String diagnosis, List<Date> treatmentDates, Patient patient){
		RaidologyTreatment rt=new RaidologyTreatment();
		rt.setDiagnosis(diagnosis);
		rt.setDate(treatmentDates);
		rt.setPatient(patient);
		addTreatment(rt);
	}
	
	public List<Long> getTreatmentsWithProvider(){
		List<Long> tp = new ArrayList<Long>();
		for(Treatment t : this.getTreatment()){
			tp.add(t.getId());
		}
		return tp;
	}
	
	public List<Long> getTreatmentsWithPatient(Patient p){
		List<Long> tp = new ArrayList<Long>();
		for(Treatment t : this.getTreatment()){
			if(t.getPatient()==p)
				tp.add(t.getId());
		}
		return tp;
	}
	

	public void visitTreatment(long tid , ITreatmentVisitor visitor) throws TreatmentExn{
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getProvider() != this){
			throw new TreatmentExn("Inappropriate treatment access : provider" + npi + 
									" treatment = " + tid);
		}
		t.visit(visitor);
	}
	
}

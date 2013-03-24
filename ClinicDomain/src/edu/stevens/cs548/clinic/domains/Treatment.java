package edu.stevens.cs548.clinic.domains;

import java.io.Serializable;
import javax.persistence.*;

import edu.stevens.cs548.clinic.domains.Patient;
import edu.stevens.cs548.clinic.domains.Provider;

/**
 * Entity implementation class for Entity: Treatment
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TTYPE",discriminatorType = DiscriminatorType.STRING,length = 2)
@Table(name = "TREATMENT")
public abstract class Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private String diagnosis;
	@ManyToOne
	@JoinColumn(name = "patient_fk", referencedColumnName = "id")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "provider_fk", referencedColumnName = "npi")
	private Provider provider;
	@Column(name = "TTYPE")
	private String TreatmentType;
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Treatment() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
		if(!patient.getTreatment().contains(this))
			patient.addTreatment(this);
	}
   

	public abstract void visit (ITreatmentVisitor visitor);

	
	public String getTreatmentType() {
		return TreatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		TreatmentType = treatmentType;
	}
}

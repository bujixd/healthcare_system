package edu.stevens.cs548.clinic.domains;

import edu.stevens.cs548.clinic.domains.Treatment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: DrugTreatment
 *
 */
@Entity
@DiscriminatorValue("D")
public class DrugTreatment extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String drug;
	private float dosage;
	//private String physician;
	
	
	public DrugTreatment() {
		super();
		this.setTreatmentType("D");
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public float getDosage() {
		return dosage;
	}

	public void setDosage(float dosage) {
		this.dosage = dosage;
	}

	/**public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}*/
	
	public void visit (ITreatmentVisitor visitor){
		visitor.visitDrugTreatment(this.getId(), 
								   this.getDiagnosis(), 
								   this.drug, 
								   this.dosage,
								   this.getProvider());
	}

   
}

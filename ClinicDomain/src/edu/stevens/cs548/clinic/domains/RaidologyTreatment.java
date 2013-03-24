package edu.stevens.cs548.clinic.domains;

import edu.stevens.cs548.clinic.domains.Treatment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RaidologyTreatment
 *
 */
@Entity
@DiscriminatorValue("R")
public class RaidologyTreatment extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	

	private List<Date> date;
	//private String radiologist;

	public RaidologyTreatment() {
		super();
		this.setTreatmentType("R");
	}

	public List<Date> getDate() {
		return date;
	}

	public void setDate(List<Date> date) {
		this.date = date;
	}

	/**public String getRadiologist() {
		return radiologist;
	}

	public void setRadiologist(String radiologist) {
		this.radiologist = radiologist;
	}**/

	public void visit (ITreatmentVisitor visitor){
		visitor.visitRadiology(this.getId(), 
							   this.getDiagnosis(), 
							   this.date, 
							   this.getProvider());
   

	}

}

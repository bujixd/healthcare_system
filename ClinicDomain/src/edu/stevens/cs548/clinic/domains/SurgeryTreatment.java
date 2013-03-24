package edu.stevens.cs548.clinic.domains;

import edu.stevens.cs548.clinic.domains.Treatment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: SureryTreatment
 *
 */
@Entity
@DiscriminatorValue("S")
public class SurgeryTreatment extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date date;
	//private String surgeon;
	
	public SurgeryTreatment() {
		super();
		this.setTreatmentType("S");
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}**/
   
	public void visit (ITreatmentVisitor visitor){
		visitor.visitSurgery(this.getId(), 
							 this.getDiagnosis(), 
							 this.date, 
							 this.getProvider());

	}
}
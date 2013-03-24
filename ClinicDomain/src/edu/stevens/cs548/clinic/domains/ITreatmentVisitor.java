package edu.stevens.cs548.clinic.domains;

import java.util.Date;
import java.util.List;

public interface ITreatmentVisitor {
	
	public void visitDrugTreatment( long tid,
									String diagnosis, 
									String drug,
									float dosage,
									Provider provider);

	public void visitRadiology( long tid,
								String diagnosis,
							    List<Date> dates,
							    Provider provider);
	
	public void visitSurgery(long tid,
			  			     String diagnosis,
							 Date date,
							 Provider provider);
}

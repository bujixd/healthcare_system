package edu.stevens.cs548.clinic.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.domains.Provider;
import edu.stevens.cs548.clinic.domains.Specialization;


@XmlRootElement(name ="provider-dto", namespace ="http://cs548.stevens.edu/clinic/service/web")
public class ProviderDTO {

	public long id;
	
	@XmlElement(name = "npi")
	public long npi;
	
	@XmlElement(required = true)
	public String name;
	
	@XmlElement
	public Specialization specilization;
	
	public long[] treatments;
	
	public ProviderDTO(){}
	
	public ProviderDTO(Provider p){
		this.id = p.getId();
		this.npi = p.getNpi();
		this.name = p.getName();
		this.specilization = p.getSpecialization();
		List<Long> tids = p.getTreatmentsWithProvider();
		this.treatments = new long[tids.size()];
		for (int i = 0;i<treatments.length; i++){
			this.treatments[i] = tids.get(i);
		}
	}
	
}

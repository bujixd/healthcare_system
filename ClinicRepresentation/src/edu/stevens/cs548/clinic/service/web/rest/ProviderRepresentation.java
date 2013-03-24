package edu.stevens.cs548.clinic.service.web.rest;

import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs548.clinic.domains.Specialization;
import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.web.rest.data.LinkType;
import edu.stevens.cs548.clinic.service.web.rest.data.ProviderType;


public class ProviderRepresentation extends ProviderType{
	
	public List<LinkType> getLinksTreatments() {
		return this.getTreatments();
	}
	
	
	public ProviderRepresentation() {
		super();
	}

	public ProviderRepresentation (ProviderDTO dto,UriInfo uriInfo){
		this.id = dto.id;
		this.npi = dto.npi;
		this.name = dto.name;
		
		long[] treatments = dto.treatments;
		UriBuilder ub = uriInfo.getBaseUriBuilder();
		ub.path("treatment");
		List<LinkType> links = this.getTreatments();
		for(long t : treatments){
			LinkType link = new LinkType();
			UriBuilder ubTreatment = ub.clone();
			ubTreatment.path("{tid}");
			String treatmentURI = ubTreatment.build(Long.toString(t)).toString();
			link.setUrl(treatmentURI);
			link.setRelation(Representation.RELATION_TREATMENT);
			link.setMediaType(Representation.MEDTA_TYPE);
			links.add(link);;
		}
	}
}

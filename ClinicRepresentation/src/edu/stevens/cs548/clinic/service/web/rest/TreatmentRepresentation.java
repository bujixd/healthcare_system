package edu.stevens.cs548.clinic.service.web.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.web.rest.data.LinkType;
import edu.stevens.cs548.clinic.service.web.rest.data.TreatmentType;

public class TreatmentRepresentation extends TreatmentType {

	public LinkType getLinkPatient(){
		return this.getPatient();
	}
	
	public LinkType getLinkProvider(){
		return this.getProvider();
	}
	
	public TreatmentRepresentation(){
		super();
	}
	
	public TreatmentRepresentation(TreatmentDto treatmentDTO, UriInfo context) {
		this.diagnosis=treatmentDTO.getDiagnosis();
		this.patient.setRelation(Representation.RELATION_PATIENT);
		this.patient.setMediaType(Representation.MEDTA_TYPE);
		UriBuilder ub1=context.getBaseUriBuilder();
		this.patient.setUrl(ub1.path("patient").path("{id}").build(treatmentDTO.getPatientId()).toString());
		this.provider.setRelation(Representation.RELATION_PROVIDER);
		this.provider.setMediaType(Representation.MEDTA_TYPE);
		UriBuilder ub2=context.getBaseUriBuilder();
		this.provider.setUrl(ub2.path("provider").path("{id}").build(treatmentDTO.getPatientId()).toString());
		if(treatmentDTO.TreatmentType instanceof DrugTreatmentType){
			DrugTreatmentType dt=(DrugTreatmentType)treatmentDTO.TreatmentType;
			this.drugTreatment.setDosage(dt.getDosage());
			this.drugTreatment.setName(dt.getName());
		}else if(treatmentDTO.TreatmentType instanceof SurgeryType){
			this.surgery.setDate(((SurgeryType)treatmentDTO.TreatmentType).getDate());
		}else if(treatmentDTO.TreatmentType instanceof RadiologyType){
			List<Date> dates=((RadiologyType)treatmentDTO.TreatmentType).getDate();
			List<Date> treatmentDates=this.radiology.getDate();
			for(Date i : dates){
				treatmentDates.add(i);
			}
		}
	}

}

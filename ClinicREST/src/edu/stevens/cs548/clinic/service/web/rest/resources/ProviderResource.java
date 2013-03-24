package edu.stevens.cs548.clinic.service.web.rest.resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceRemote;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceRemote;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.ProviderService;
import edu.stevens.cs548.clinic.service.web.rest.PatientRepresentation;
import edu.stevens.cs548.clinic.service.web.rest.ProviderRepresentation;
import edu.stevens.cs548.clinic.service.web.rest.TreatmentRepresentation;

@Path("generic")
public class ProviderResource {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public ProviderResource() {
        // TODO Auto-generated constructor stub
    }

    @EJB(beanName="ProviderServiceBean")
    private IProviderServiceRemote providerService;
    
    /**
     * Retrieves representation of an instance of ProviderResource
     * @return an instance of String
     */
    @GET
    @Produces("application/xml")
    public ProviderRepresentation getProviderByNPI(@PathParam("npi") String npi) {
        // TODO return proper representation object
    	try{
    		long key = Long.parseLong(npi);
    		ProviderDTO patientDTO = providerService.getProviderByNPI(key);
    		ProviderRepresentation patientRep= new ProviderRepresentation(patientDTO,context);
    		return patientRep;
    	} catch (ProviderServiceExn e) {
    		throw new WebApplicationException(403);
    	}
    }
    
    
    @GET 
    @Path("treatment/{id}")
    @Produces("application/xml")
    public TreatmentRepresentation getTreatment(@PathParam("id")String id){
    	try {
    		long tid = Long.parseLong(id);
			TreatmentDto[] treatments = providerService.getTreatmentDtoIds(new long[]{tid});
			if(treatments!=null&&treatments.length==1){
				return new TreatmentRepresentation(treatments[0],context);
			}else{
				throw new WebApplicationException(403);
			}
		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(500);
		}
    }

}

package edu.stevens.cs548.clinic.service.web.rest.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.DatatypeConverter;

import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.ejb.IPatientService;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceRemote;
import edu.stevens.cs548.clinic.service.web.rest.PatientRepresentation;
import edu.stevens.cs548.clinic.service.web.rest.TreatmentRepresentation;

@Path("/patient")
public class PatientResource {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public PatientResource() {
        // TODO Auto-generated constructor stub
    }
    
    @EJB(beanName="PatientServiceBean")
    private IPatientServiceRemote patientService;

    /**
     * Retrieves representation of an instance of PatientResource
     * @return an instance of String
     */
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public PatientRepresentation getPatient(@PathParam("id") String id) {
        // TODO return proper representation object
    	try{
    		long key = Long.parseLong(id);
    		PatientDTO patientDTO = patientService.getPatientByDbId(key);
    		PatientRepresentation patientRep= new PatientRepresentation(patientDTO,context);
    		return patientRep;
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException(403);
    	}
    }
    
    @GET
    @Path("patientId")
    @Produces("application/xml")
    public PatientRepresentation getPatientByPatientId(@QueryParam("patientId") String patientId){
    	try{
    		long pid = Long.parseLong(patientId);
    		PatientDTO patientDTO = patientService.getPatientByPatId(pid);
    		PatientRepresentation patientRep= new PatientRepresentation(patientDTO,context);
    		return patientRep;
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException(403);
    	}
    }

    @GET
    @Produces("application/xml")
    public PatientRepresentation[] getPatientByNameDob(@QueryParam("name") String name,
    													@QueryParam("dob") String dob) throws PatientServiceExn{
    	Date brithDate = DatatypeConverter.parseDate(dob).getTime(); 
		PatientDTO[] patientDTO = patientService.getPatientsByNameDob(name, brithDate);
		PatientRepresentation[] patientReps= new PatientRepresentation[patientDTO.length];
		for(int i = 0; i < patientDTO.length; i++){
			patientReps[i] = new PatientRepresentation(patientDTO[i],context);
		}
		return patientReps;
    }
    
    @DELETE
    @Path("patient-key")
    @Consumes("application/xml")
    public Response deletePatient(@PathParam("id") String id,
    										   @PathParam("name") String name) {
        // TODO return proper representation object
    	try{
    		long key = Long.parseLong(id);
    		patientService.deletePatient(name, key);
    		return Response.ok().build();
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException(403);
    	}
    }

    @POST
    @Path("addDrugTreatment")
    @Consumes("application/xml")
    public Response addDrugTreatment(TreatmentRepresentation treatmentRep, @HeaderParam("Authorization") String patientUri){
    	try{
    		long patientDatabaseId=Long.parseLong(patientUri.substring(patientUri.lastIndexOf("/")+1));
    		String providerUri=treatmentRep.getProvider().getUrl();
    		long providerDatabaseId=Long.parseLong(providerUri.substring(providerUri.lastIndexOf("/")+1));
    		long id=patientService.addDrugTreatment(patientDatabaseId, providerDatabaseId, treatmentRep.getDiagnosis(), treatmentRep.getDrugTreatment().getName(), treatmentRep.getDrugTreatment().getDosage());
    		UriBuilder ub=context.getBaseUriBuilder();
    		return Response.created(ub.path("treatment").path("{id}").build(id)).build();
    	}catch(Exception e){
    		throw new WebApplicationException(500);
    	}
    }
    
    @POST
    @Path("addSurgeryTreatment")
    @Consumes("application/xml")
    public Response addSurgeryTreatment(TreatmentRepresentation treatmentRep, @HeaderParam("Authorization") String patientUri){
    	try{
    		long patientDatabaseId=Long.parseLong(patientUri.substring(patientUri.lastIndexOf("/")+1));
    		String providerUri=treatmentRep.getProvider().getUrl();
    		long providerDatabaseId=Long.parseLong(providerUri.substring(providerUri.lastIndexOf("/")+1));
    		long id=patientService.addSurgert(patientDatabaseId, providerDatabaseId, treatmentRep.getDiagnosis(), treatmentRep.getSurgery().getDate());
    		UriBuilder ub=context.getBaseUriBuilder();
    		return Response.created(ub.path("treatment").path("{id}").build(id)).build();
    	}catch(Exception e){
    		throw new WebApplicationException(500);
    	}
    }
    
    @POST
    @Path("addDrugTreatment")
    @Consumes("application/xml")
    public Response addRadiologyTreatment(TreatmentRepresentation treatmentRep, @HeaderParam("Authorization") String patientUri){
    	try{
    		long patientDatabaseId=Long.parseLong(patientUri.substring(patientUri.lastIndexOf("/")+1));
    		String providerUri=treatmentRep.getProvider().getUrl();
    		long providerDatabaseId=Long.parseLong(providerUri.substring(providerUri.lastIndexOf("/")+1));
    		Date[] treatmentDates=new Date[treatmentRep.getRadiology().getDate().size()];
    		List<Date> date = new ArrayList<Date>();
    		for(int i=0; i<treatmentDates.length;i++){
    			date.add(treatmentRep.getRadiology().getDate().get(i));
    		}
    		long id=patientService.addRaidology(patientDatabaseId, providerDatabaseId, treatmentRep.getDiagnosis(), date);
    		UriBuilder ub=context.getBaseUriBuilder();
    		return Response.created(ub.path("treatment").path("{id}").build(id)).build();
    	}catch(Exception e){
    		throw new WebApplicationException(500);
    	}
    }
    /**
     * PUT method for updating or creating an instance of PatientResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/xml")
    public Response addPatient(PatientRepresentation patientRep) {
    	try {
			long id = patientService.createPatient(patientRep.getName(),
										patientRep.getDob(),
										patientRep.getPatientId());
			UriBuilder ub = context.getAbsolutePathBuilder().path("{id}");
			URI url = ub.build(Long.toString(id));
			return Response.created(url).build();
		} catch (IPatientService.PatientServiceExn e) {
			throw new WebApplicationException();
		}
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/xml")
    public Response deleteTreatment(@QueryParam("tid") String treatmentId, @QueryParam("id") String pid){
    	long patientDatabaseId=Long.parseLong(pid);
    	try {
			patientService.deleteTreatment(patientDatabaseId, Long.parseLong(treatmentId));
			return Response.ok().build();
		} catch (Exception e){
			throw new WebApplicationException(500);
		}
    }

}
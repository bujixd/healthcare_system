package edu.stevens.cs548.clinic.service.web.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.domains.Patient;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.TreatmentNotFoundExn;

@WebService(
		name="IProviderWebPort",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap")
public interface IProviderWebService {

public long createProvider(long npi, String name) throws ProviderServiceExn;
	@WebMethod
	public ProviderDTO[] getProviderByName(String name);
	
	@WebMethod
	public ProviderDTO getProviderByNPI(long npi) throws ProviderServiceExn,ProviderNotFoundExn;
	
	@WebMethod
	public long[] getTreatmentIds() throws ProviderNotFoundExn;
	
	@WebMethod
	public TreatmentDto[] getTreatmentDtoIds(long tids[]) throws TreatmentNotFoundExn;
		
	@WebMethod
	public String siteInfo();
}

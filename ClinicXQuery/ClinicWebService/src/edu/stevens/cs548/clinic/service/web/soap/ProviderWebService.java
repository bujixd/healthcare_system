package edu.stevens.cs548.clinic.service.web.soap;

import javax.ejb.EJB;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.domains.Patient;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.TreatmentNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceRemote;


@WebService(
		endpointInterface="edu.stevens.cs548.clinic.service.web.soap.IProviderWebService",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap",
		serviceName="ProviderWebService",
		portName="ProviderPort")
public class ProviderWebService { 
			//implements IProviderWebService {

	
	@EJB(beanName="ProviderServiceBean")
	IProviderServiceRemote service;
	
	public long createProvider(long npi, String name) throws ProviderServiceExn {
		// TODO Auto-generated method stub
		return service.createProvider(npi, name);
	}

	public ProviderDTO[] getProviderByName(String name) {
		// TODO Auto-generated method stub
		return service.getProviderByName(name);
	}

	public ProviderDTO getProviderByNPI(long npi) throws ProviderServiceExn,
			ProviderNotFoundExn {
		// TODO Auto-generated method stub
		return service.getProviderByNPI(npi);
	}

	public long[] getTreatmentIds() throws ProviderNotFoundExn {
		// TODO Auto-generated method stub
		return service.getTreatmentIds();
	}

	public TreatmentDto[] getTreatmentDtoIds(long[] tids)
			throws TreatmentNotFoundExn {
		// TODO Auto-generated method stub
		return service.getTreatmentDtoIds(tids);
	}

	public String siteInfo() {
		// TODO Auto-generated method stub
		return service.siteInfo();
	}

}

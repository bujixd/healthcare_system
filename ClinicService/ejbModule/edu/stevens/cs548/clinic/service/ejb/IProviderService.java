package edu.stevens.cs548.clinic.service.ejb;

import edu.stevens.cs548.clinic.domains.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domains.Patient;
import edu.stevens.cs548.clinic.domains.Treatment;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;

public interface IProviderService {

	public class ProviderServiceExn extends Exception{
		private static final long serialVersionUID = 1L;

		public ProviderServiceExn(String m){
			super(m);
		}
	}
	
	public class ProviderNotFoundExn extends ProviderServiceExn{
		public ProviderNotFoundExn(String m){
			super(m);
		}
	}
	
	public class TreatmentNotFoundExn extends ProviderServiceExn{
		public TreatmentNotFoundExn(String m){
			super(m);
		}
	}
	
	public long createProvider(long npi, String name) throws ProviderServiceExn;
	
	public ProviderDTO[] getProviderByName(String name);
	
	public ProviderDTO getProviderByNPI(long npi) throws ProviderServiceExn,ProviderNotFoundExn;
	
	public long[] getTreatmentIds() throws ProviderNotFoundExn;
	
	public TreatmentDto[] getTreatmentDtoIds(long tids[]) throws TreatmentNotFoundExn;
			
	public String siteInfo();
}

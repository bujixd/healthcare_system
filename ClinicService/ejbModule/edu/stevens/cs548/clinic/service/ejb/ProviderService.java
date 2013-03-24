package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;

import edu.stevens.cs548.clinic.domains.IProviderDAO;
import edu.stevens.cs548.clinic.domains.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domains.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domains.ITreatmentVisitor;
import edu.stevens.cs548.clinic.domains.Patient;
import edu.stevens.cs548.clinic.domains.PatientDAO;
import edu.stevens.cs548.clinic.domains.Provider;
import edu.stevens.cs548.clinic.domains.ProviderDAO;
import edu.stevens.cs548.clinic.domains.ProviderFactory;
import edu.stevens.cs548.clinic.domains.Treatment;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.PatientDTO;
import edu.stevens.cs548.clinic.service.dto.ProviderDTO;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.PatientService.TreatmentPDOtoDTO;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ProviderService
 */
@Stateless(name = "ProviderServiceBean")
public class ProviderService implements IProviderServiceRemote,
		IProviderServiceLocal {

	private ProviderFactory providerFactory;

	private IProviderDAO providerDAO;

	/**
	 * Default constructor.
	 */
	public ProviderService() {
		providerFactory = new ProviderFactory();
	}

	@PersistenceContext(unitName = "ClinicDomain")
	private EntityManager em;

	private Provider provider1;

	@PostConstruct
	private void initialize() {
		providerDAO = new ProviderDAO(em);
	}

	/**
	 * @throws ProviderServiceExn
	 * @see IProviderService#getProviderByNPI(long)
	 */
	public ProviderDTO getProviderByNPI(long npi) throws ProviderServiceExn,
			ProviderNotFoundExn {
		try {
			Provider provider = providerDAO.getProviderByNPI(npi);
			return new ProviderDTO(provider);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}

	/**
	 * @see IProviderService#getProviderByName(String)
	 */
	public ProviderDTO[] getProviderByName(String name) {
		List<Provider> providers = providerDAO.getProviderByName(name);
		ProviderDTO[] dto = new ProviderDTO[providers.size()];
		for (int i = 0; i < dto.length; i++) {
			dto[i] = new ProviderDTO(providers.get(i));
		}
		return dto;
	}

	/**
	 * @see IProviderService#createProvider(long, String)
	 */
	public long createProvider(long npi, String name) throws ProviderServiceExn {
		Provider provider = providerFactory.createProviders(npi, name);
		try {
			providerDAO.addProvider(provider);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
		return provider.getId();
	}

	static class TreatmentPDOtoDTO implements ITreatmentVisitor {

		private TreatmentDto dto;

		public TreatmentDto getDTO() {
			return dto;
		}

		@Override
		public void visitDrugTreatment(long tid, String diagnosis, String drug,
				float dosage,Provider provider) {
			// TODO Auto-generated method stub
			dto = new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			DrugTreatmentType drugInfo = new DrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setName(drug);
			dto.setDrugTreatment(drugInfo);
		}

		@Override
		public void visitRadiology(long tid, String diagnosis, List<Date> dates,Provider provider) {
			// TODO Auto-generated method stub
			dto = new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			RadiologyType radiologyInfo = new RadiologyType();
			radiologyInfo.getDate().addAll(dates);
			dto.setRadiology(radiologyInfo);
		}

		@Override
		public void visitSurgery(long tid, String diagnosis, Date date,Provider provider) {
			dto = new TreatmentDto();
			dto.setDiagnosis(diagnosis);
			SurgeryType surgeryInfo = new SurgeryType();
			surgeryInfo.setDate(date);
			dto.setSurgery(surgeryInfo);
		}

	}

	/**
	 * @see IProviderService#getTreatmentDtoIds(long)
	 */
	public TreatmentDto[] getTreatmentDtoIds(long tids[])
			throws TreatmentNotFoundExn {
		try {
			TreatmentDto[] treatments = new TreatmentDto[tids.length];
			Provider p = new Provider();
			for (int i = 0; i < tids.length; i++) {
				if (tids[i] == p.getNpi()) {
					TreatmentPDOtoDTO visitor = new TreatmentPDOtoDTO();
					p.visitTreatment(tids[i], visitor);
					treatments[i] = visitor.getDTO();
				}
			}
			return treatments;
		} catch (TreatmentExn e) {
			throw new TreatmentNotFoundExn(e.toString());
		}
	}

	/**
	 * @throws ProviderExn
	 * @see IProviderService#getTreatmentIds(Patient)
	 */
	public long[] getTreatmentIds() throws ProviderNotFoundExn {

		Provider provider;
		try {
			provider = providerDAO.getProviderByNPI(provider1.getNpi());
			long tids[] = new long[provider.getTreatmentsWithProvider().size()];
			for (int i = 0; i < tids.length; i++) {
				tids[i] = provider.getTreatmentsWithProvider().get(i);
			}
			return tids;
		} catch (ProviderExn e) {
			throw new ProviderNotFoundExn(e.toString());
		}

	}

	@Resource(name="SiteInfo")
	private String siteInformation;

	@Override
	public String siteInfo() {
		// TODO Auto-generated method stub
		return siteInformation;
	}

}

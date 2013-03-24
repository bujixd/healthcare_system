package edu.stevens.cs548.clinic.domains;

import java.util.List;


public interface IProviderDAO {

	public static class ProviderExn extends Exception {
		private static final long serialVersionUID =1L;
		public ProviderExn(String msg){
			super(msg);
		}
	}
	
	public List<Provider> getProviderByName(String name);
	
	public Provider getProviderByNPI(long npi)throws ProviderExn;
	
	public void addProvider(Provider pro)throws ProviderExn;
	
	public void deleteProvider(Provider pro) throws ProviderExn;
}

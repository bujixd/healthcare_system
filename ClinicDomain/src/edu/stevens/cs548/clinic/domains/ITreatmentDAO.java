package edu.stevens.cs548.clinic.domains;

public interface ITreatmentDAO {
	
	public static class TreatmentExn extends Exception{
		private static final long serialVersionUID =1L;
		public TreatmentExn(String msg){
			super(msg);
		}
	}

	public Treatment getTreatmentByDbId(long id)throws TreatmentExn;
	
	public void addTreatment(Treatment t);
	
	public void deleteTreatment(Treatment t);
	//public void deleteTreatments (Patient patient);
}

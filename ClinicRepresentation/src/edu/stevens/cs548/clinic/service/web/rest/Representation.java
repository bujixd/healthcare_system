/**
 * 
 */
package edu.stevens.cs548.clinic.service.web.rest;

/**
 * @author bujixd
 *
 */
public class Representation {
	
	public static final String RELATIONS = "http://cs548.stevens.edu/clinic/rest/relations/";
	
	public static final String RELATION_TREATMENT = RELATIONS + "treatment";

	public static final String RELATION_PATIENT = RELATIONS + "patient";
	
	public static final String RELATION_PROVIDER = RELATIONS + "provider";
	
	public static final String NAMESPACE = "http://cs548.stevens.edu/clinic/rest/data";

	public static final String DAP_NAMESPACE = NAMESPACE + "/dap";
	
	public static final String MEDTA_TYPE = "application/xml";
	
	//public abstract list<link> getLinks();
	
}

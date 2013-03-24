package edu.stevens.cs548.clinic.service.web.soap.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.rpc.ServiceException;

import edu.stevens.cs548.clinic.service.web.soap.*;

public class Tester {

	private class ServiceError extends Exception {
		ServiceError(String s) {
			super(s);
		}
	}

	Logger logger = Logger
			.getLogger("edu.stevens.cs548.clinic.service.web.soap.client.Tester");

	/*
	 * Input file. Defaults to standard input.
	 */
	private String InputFileName = "<stdin>";

	private BufferedReader InputFile = new BufferedReader(
			new InputStreamReader(System.in));

	/*
	 * Output file. Defaults to standard output.
	 */
	private String OutputFileName;

	private PrintWriter OutputFile = new PrintWriter(new OutputStreamWriter(
			System.out));

	/*
	 * Endpoint URL for the Web service.
	 */
	private URL endpointUrl = null;

	/*
	 * Client proxy for the Web service. Generated from the endpoint URL.
	 */
	private IPatientWebPort patient = null;
	
	private IProviderWebPort provider = null;

	/*
	 * Line number in input file, for error reporting.
	 */
	private int lineNo = 0;

	private String readLine() {
		/*
		 * Read a command from the input.
		 */
		try {
			lineNo++;
			return InputFile.readLine();
		} catch (IOException e) {
			error("Error reading input: " + e);
			return null;
		}
	}

	private void error(String msg) {
		if (lineNo > 0) {
			System.err.print(lineNo + ": ");
		}
		System.err.println("** Error **");
		System.err.println(msg);
		System.exit(-1);
	}

	private void remoteError(Exception e) {
		e.printStackTrace(System.err);
		error("Network error: " + e);
	}

	private void warning(String msg) {
		if (lineNo > 0) {
			System.err.print(lineNo + ": ");
		}
		System.err.println("** Warning **");
		System.err.println(msg);
	}

	private void print(String s) {
		OutputFile.print(s);
	}

	private void println(String s) {
		OutputFile.println(s);
	}
	
	private void newline() {
		OutputFile.println();
	}

	private void displayLong(long n) {
		OutputFile.print(n);
	}

	private void displayLongList(long[] l){
		println("[");
		for (long lo : l) {
			displayLong(lo);
		}
		println("]");
	} 
	
	private void displayTreatment(String prefix, GetTreatmentsResponseReturn t,
			String suffix) {
		print(prefix);
		logger.info("Displaying a treatment.");
		print("Treatment(){");
		print("diagnosis=");
		print(t.getDiagnosis());
		print(",treatment=");
		if (t.getDrugTreatment() != null) {
			DrugTreatmentType dt = t.getDrugTreatment();
			print("DrugTreatment{");
			print("name=");
			print(dt.getName());
			print(",dosage=");
			print(Float.toString(dt.getDosage()));
			print("}");
		} else if (t.getRadiology() != null) {
			Date[] rad = t.getRadiology();
			DateFormat datefmt = DateFormat.getInstance();
			print("Radiology{dates=[");
			for (int i = 0; i < rad.length - 1; i++) {
				print(datefmt.format(rad[i]));
				print(",");
			}
			if (rad.length > 0) {
				print(datefmt.format(rad[rad.length - 1]));
			}
			print("]}");
		} else if (t.getSurgery() != null) {
			SurgeryType st = t.getSurgery();
			print("Surgery{");
			print("date=");
			print(DateFormat.getInstance().format(st.getDate()));
			print("}");
		}
		print("}");
	}

	private void displayTreatment(String prefix, GetTreatmentDtoIdsResponseReturn t,
			String suffix) {
		print(prefix);
		logger.info("Displaying a treatment.");
		print("Treatment(){");
		print("diagnosis=");
		print(t.getDiagnosis());
		print(",treatment=");
		if (t.getDrugTreatment() != null) {
			DrugTreatmentType dt = t.getDrugTreatment();
			print("DrugTreatment{");
			print("name=");
			print(dt.getName());
			print(",dosage=");
			print(Float.toString(dt.getDosage()));
			print("}");
		} else if (t.getRadiology() != null) {
			Date[] rad = t.getRadiology();
			DateFormat datefmt = DateFormat.getInstance();
			print("Radiology{dates=[");
			for (int i = 0; i < rad.length - 1; i++) {
				print(datefmt.format(rad[i]));
				print(",");
			}
			if (rad.length > 0) {
				print(datefmt.format(rad[rad.length - 1]));
			}
			print("]}");
		} else if (t.getSurgery() != null) {
			SurgeryType st = t.getSurgery();
			print("Surgery{");
			print("date=");
			print(DateFormat.getInstance().format(st.getDate()));
			print("}");
		}
		print("}");
	}
	
	private void displayTreatmentList(GetTreatmentsResponseReturn[] ts) {
		println("[");
		for (GetTreatmentsResponseReturn t : ts) {
			displayTreatment("  ", t, ",\n");
		}
		println("]");
	}
	
	private void displayTreatmentDtoList(GetTreatmentDtoIdsResponseReturn[] ts) {
		println("[");
		for (GetTreatmentDtoIdsResponseReturn t : ts) {
			displayTreatment("  ", t, ",\n");
		}
		println("]");
	}

	private void displayProvider(String prefix, ProviderDTO p, String suffix) {
		print(prefix);
		logger.info("Displaying a provider DTO.");
		print("Provider(");
		print(Long.toString(p.getId()));
		print("){");
		print("name=");
		print(p.getName());
		print(",NPI=");
		print(Long.toString(p.getNpi()));
		print("}");

	}

	private void displayProvider(ProviderDTO p) {
		displayProvider("", p, "\n");
	}

	private void displayProviderList(ProviderDTO[] ps) {
		println("[");
		for (ProviderDTO p : ps) {
			displayProvider("  ", p, ",\n");
		}
		println("]");
	}
	
	private void displayPatient(String prefix, PatientDTO p, String suffix) {
		print(prefix);
		logger.info("Displaying a patient DTO.");
		print("Patient(");
		print(Long.toString(p.getId()));
		print("){");
		print("name=");
		print(p.getName());
		print(",birthDate=");
		print(DateFormat.getInstance().format(p.getDob().getTime()));
		print(",patientId=");
		print(Long.toString(p.getPatientId()));
		print("}");

	}

	private void displayPatient(PatientDTO p) {
		displayPatient("", p, "\n");
	}

	private void displayPatientList(PatientDTO[] ps) {
		println("[");
		for (PatientDTO p : ps) {
			displayPatient("  ", p, ",\n");
		}
		println("]");
	}

	private long addPatient(String[] args) throws ServiceError {
		if (args.length != 3) {
			error("Usage: patient addPatient name dob patient-id");
		}
		String name = args[0];
		Calendar dob = DatatypeConverter.parseDate(args[1]);
		long patientId = Long.parseLong(args[2]);
		logger.info("Adding a patient (name=" + name + ").");
		try {
			return patient.create(name, dob, patientId);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
			return -1;
		}
	}
	
	private long addProvider(String[] args) throws ServiceError {
		if (args.length != 2) {
			error("Usage: provider addProvider name specialization NPI");
		}
		long NPI = Long.parseLong(args[0]);
		String name = args[1];
		logger.info("Adding a provider (name=" + name + ").");
		try {
			return provider.createProvider(NPI, name);
		} catch (ProviderServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
			return -1;
		}
	}

	private PatientDTO getPatientByDbId(String[] args) throws ServiceError {
		if (args.length != 1) {
			error("Usage: patient getPatientByDbId patient-key");
		}
		long patientKey = Long.parseLong(args[0]);
		try {
			return patient.getPatientByDbId(patientKey);
		} catch (RemoteException e) {
			error("Network error: " + e);
			return null;
		} catch (Exception e) {
			throw new ServiceError(e.toString());
		}
	}

	private PatientDTO getPatientByPatId(String[] args) throws ServiceError {
		if (args.length != 1) {
			error("Usage: patient getPatientByPatId patient-id");
		}
		long patientId = Long.parseLong(args[0]);
		logger.info("Getting a patient by patient id (" + patientId + ").");
		try {
			return patient.getPatientByPatId(patientId);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
			return null;
		}
	}

	private ProviderDTO getProviderByNPI(String[] args) throws ServiceError {
		if (args.length != 1) {
			error("Usage: provider getPatientByNPI npi");
		}
		long npi = Long.parseLong(args[0]);
		logger.info("Getting a provider by npi (" + npi + ").");
		try {
			return provider.getProviderByNPI(npi);
		} catch (ProviderServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
			return null;
		}
	}
	
	private PatientDTO[] getPatientsByNameDob(String[] args) {
		if (args.length != 2) {
			error("Usage: patient getPatientsByNameDob name dob");
		}
		String name = args[0];
		Calendar dob = DatatypeConverter.parseDate(args[1]);
		try {
			return patient.getPatientsByNameDob(name, dob);
		} catch (RemoteException e) {
			remoteError(e);
			return null;
		}
	}

	private ProviderDTO[] getProvidersByName(String[] args) {
		if (args.length != 1) {
			error("Usage: providers getProviderByName name");
		}
		String name = args[0];
		try {
			return provider.getProviderByName(name);
		} catch (RemoteException e) {
			remoteError(e);
			return null;
		}
	}

	
	private void deletePatient(String[] args) throws ServiceError {
		if (args.length != 2) {
			error("Usage: patient deletePatient name key");
		}
		String name = args[0];
		long id = Long.parseLong(args[1]);
		try {
			patient.deletePatient(name, id);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
		}
	}

	private void addDrugTreatment(String[] args) throws ServiceError {
		if (args.length != 5) {
			error("Usage: patient addDrugTreatment patient-key provider-key diagnosis drug dosage");
		}
		long id = Long.parseLong(args[0]);
		long providerKey = Long.parseLong(args[1]);
		String diagnosis = args[2];
		String drug = args[3];
		float dosage = Float.parseFloat(args[4]);
		try {
			/*
			 * My code does not yet have providers.
			 */
			patient.addDrugTreatment(id, providerKey, diagnosis, drug, dosage);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
		}
	}

	private void addRadiology(String[] args) throws ServiceError {
		if (args.length < 4) {
			error("Usage: patient addRadiology patient-key provider-key diagnosis date1 date2 ...");
		}
		long id = Long.parseLong(args[0]);
		long providerKey = Long.parseLong(args[1]);
		String diagnosis = args[2];
		Calendar[] dates = new Calendar[args.length];
		for (int i = 3; i < args.length; i++) {
			dates[i - 3] =  DatatypeConverter.parseDate(args[i]);;
		}
		try {
			/*
			 * My code does not yet have providers.
			 */
			patient.addRaidology(id,providerKey, diagnosis, dates);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
		}
	}

	private void addSurgery(String[] args) throws ServiceError {
		if (args.length != 4) {
			error("Usage: patient addSurgery patient-key provider-key diagnosis date");
		}
		long id = Long.parseLong(args[0]);
		long providerKey = Long.parseLong(args[1]);
		String diagnosis = args[2];
		Calendar date = DatatypeConverter.parseDate(args[3]);
		try {
			/*
			 * My code does not yet have providers.
			 */
			patient.addSurgery(id,providerKey, diagnosis, date);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			remoteError(e);
		}
	}

	
	private GetTreatmentsResponseReturn[] getTreatments(String[] args)
			throws ServiceError {
		if (args.length < 1) {
			error("Usage: patient getTreatments patient-key tid1 tid2 ...");
		}
		long id = Long.parseLong(args[0]);
		Long[] tids = new Long[args.length];
		for (int i = 1; i < args.length; i++) {
			tids[i - 1] = Long.parseLong(args[i]);
		}
		try {
			return patient.getTreatments(id, tids);
		} catch (PatientServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			error("Network error: " + e);
			return null;
		}
	}
	
	
	private GetTreatmentDtoIdsResponseReturn[] getTreatmentDtoIds(String[] args)
			throws ServiceError {
		if (args.length < 1) {
			error("Usage: provider getTreatmentDtoIds tid1 tid2 ...");
		}
		Long[] tids = new Long[args.length];
		for (int i = 0; i < args.length; i++) {
			tids[i] = Long.parseLong(args[i]);
		}
		try {
			return provider.getTreatmentDtoIds(tids);
		} catch (ProviderServiceExn e) {
			throw new ServiceError(e.toString());
		} catch (RemoteException e) {
			error("Network error: " + e);
			return null;
		}
	}

	private void deleteTreatment(String[] args) throws ServiceError {
		if (args.length != 2) {
			error("Usage: patient getTreatments patient-key tid");
		}
		long id = Long.parseLong(args[0]);
		long tid = Long.parseLong(args[1]);
		try {
			patient.deleteTreatment(id, tid);
		} catch (RemoteException e) {
			remoteError(e);
		} catch (Exception e) {
			throw new ServiceError(e.toString());
		}
	}

	private String siteInfo(String[] args) {
		if (args.length > 0) {
			error("Usage: patient siteInfo");
			return null;
		}
		try {
			return patient.siteInfo();
		} catch (RemoteException e) {
			remoteError(e);
			return null;
		}
	}

	private String siteInfoProvider(String[] args) {
		if (args.length > 0) {
			error("Usage: provider siteInfo");
			return null;
		}
		try {
			return provider.siteInfo();
		} catch (RemoteException e) {
			remoteError(e);
			return null;
		}
	}
	
	public void invokePatientService(String cmd, String[] args) {
		try {
			if ("addPatient".equals(cmd)) {
				displayLong(addPatient(args));
				newline();
			} else if ("getPatientByDbId".equals(cmd)) {
				displayPatient(getPatientByDbId(args));
				newline();
			} else if ("getPatientByPatId".equals(cmd)) {
				displayPatient(getPatientByPatId(args));
				newline();
			} else if ("getPatientByNameDob".equals(cmd)) {
				displayPatientList(getPatientsByNameDob(args));
				newline();
			} else if ("deletePatient".equals(cmd)) {
				deletePatient(args);
			} else if ("addDrugTreatment".equals(cmd)) {
				addDrugTreatment(args);
			} else if ("addRadiology".equals(cmd)) {
				addRadiology(args);
			} else if ("addSurgery".equals(cmd)) {
				addSurgery(args);
			} else if ("getTreatments".equals(cmd)) {
				displayTreatmentList(getTreatments(args));
				newline();
			} else if ("deleteTreatment".equals(cmd)) {
				deleteTreatment(args);
			} else if ("siteInfo".equals(cmd)) {
				println(siteInfo(args));
			} else {
				error("Unrecognized patient service command: " + cmd);
			}
		} catch (ServiceError e) {
			error("Service raised exception: " + e);
		}
	}

	public void invokeProviderService(String cmd, String[] args) {
		try {
			if ("addProvider".equals(cmd)) {
				displayLong(addProvider(args));
				newline();
			} else if ("getProviderByNPI".equals(cmd)) {
				displayProvider(getProviderByNPI(args));
				newline();
			} else if ("getProvidersByName".equals(cmd)) {
				displayProviderList(getProvidersByName(args));
				newline();
			} else if ("getTreatmentDtoIds".equals(cmd)) {
				displayTreatmentDtoList(getTreatmentDtoIds(args));
				newline();
			} else if ("siteInfoProvider".equals(cmd)) {
				println(siteInfo(args));
			} else {
				error("Unimplemented provider client.");
			}
		} catch (ServiceError e) {
			error("Service raised exception: " + e);
		}
	}

	private String currentWorkingDir() {
		return new File(".").getAbsolutePath();
	}

	private Tester processArgs(String[] args) {
		/*
		 * Process the command line arguments:
		 * 
		 * --input filename -i filename File containing a list of commands.
		 * Default is standard input.
		 * 
		 * --output filename -o filename File to write out results of commands.
		 * Default is standard output.
		 * 
		 * --url endpoint-url -u endpoint-url Endpoint URL for the service. No
		 * default.
		 */
		for (int iarg = 0; iarg < args.length; iarg++) {
			if ("--input".equals(args[iarg]) || "-i".equals(args[iarg])) {
				if (iarg + 1 < args.length) {
					InputFileName = args[++iarg];
					try {
						InputFile = new BufferedReader(new FileReader(
								InputFileName));
					} catch (FileNotFoundException e) {
						error("Input file not found: " + InputFileName
								+ "\nDirectory: " + currentWorkingDir());
					}
				} else {
					error("Missing value for --input or -i option.");
				}
			} else if ("--output".equals(args[iarg]) || "-o".equals(args[iarg])) {
				if (iarg + 1 < args.length) {
					OutputFileName = args[++iarg];
					try {
						OutputFile = new PrintWriter(new FileWriter(
								OutputFileName));
					} catch (IOException e) {
						error("Problem opening output file: " + OutputFileName
								+ "\nDirectory: " + currentWorkingDir());
					}
				} else {
					error("Missing value for --output or -o option");
				}
			} else if ("--url".equals(args[iarg]) || "-u".equals(args[iarg])) {
				if (iarg + 1 < args.length) {
					try {
						endpointUrl = new URL(args[++iarg]);
						PatientWebService service = new PatientWebServiceLocator();
						this.patient = service.getPatientPort(endpointUrl);
						ProviderWebService service1 = new ProviderWebServiceLocator();
						this.provider = service1.getProviderPort(endpointUrl);
						//this.patient = service.getPatientWebPort();
					} catch (MalformedURLException e) {
						error("Bad service URL: " + args[iarg]);
					} catch (ServiceException e) {
						error("Service exception: " + e);
					}
				}
			}
		}

		if (this.patient == null) {
			error("You must specify an endpoint URL with the --url or -u option.");
		}

		return this;
	}

	private void processLine(String[] args) {
		if (args.length < 2) {
			error("Usage: (patient|provider) command arg1 ... argn");
		} else if ("patient".equals(args[0])) {
			invokePatientService(args[1],
					Arrays.copyOfRange(args, 2, args.length));
		} else if ("provider".equals(args[0])) {
			invokeProviderService(args[1],
					Arrays.copyOfRange(args, 2, args.length));
		} else {
			error("Service name must be \"patient\" or \"provider\".");
		}
	}

	private void processLines() {
		String line;
		while ((line = this.readLine()) != null) {
			String[] args = line.split("\\s");
			processLine(args);
		}
		try {
			InputFile.close();
		} catch (IOException e) {
			warning("Failed to close input: " + e);
		}
		OutputFile.close();
	}

	public Tester() {
	}

	public static void main(String[] args) {
		new Tester().processArgs(args).processLines();
	}

}


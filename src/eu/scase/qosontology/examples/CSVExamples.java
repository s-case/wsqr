package eu.scase.qosontology.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import eu.scase.qosontology.OntologyQoSAPI;

/**
 * Example for instantiating the QoS ontology from a CSV
 * 
 * @author Themistoklis Diamantopoulos
 */
public class CSVExamples {

	/**
	 * Reads a CSV file of the form:<br>
	 * <br>
	 * {@code ;Measure_1;Measure_2;Measure_3...}<br>
	 * {@code WS_1;Value_Of_Measure_1;Value_Of_Measure_2;Value_Of_Measure_3...}<br>
	 * {@code WS_2;Value_Of_Measure_1;Value_Of_Measure_2;Value_Of_Measure_3...}<br>
	 * {@code ...}<br>
	 * <br>
	 * and returns a HashMap containing the data in the CSV file.
	 * 
	 * @param filename the path and the filename to the CSV file
	 * @return a HashMap containing web service names as keys and HashMap's as values. Each inner HashMap contains a
	 *         measure value (as value) for each measure key (as HashMap key).
	 */
	public static HashMap<String, HashMap<String, Float>> readCSV(String filename) {

		// The DecimalFormat is used to parse strings to floats. It ensures that the numbers are correctly parsed,
		// regardless of the system locale (i.e. comma or dot for the decimal point).
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		df.setDecimalFormatSymbols(symbols);

		// Read the CSV file in a data structure. wsMeasures contains the values of each measure.
		HashMap<String, HashMap<String, Float>> wsMeasures = new HashMap<String, HashMap<String, Float>>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));

			// Read header
			String line = reader.readLine();
			String[] linevalues = line.split(";");
			List<String> measureNames = Arrays.asList(Arrays.copyOfRange(linevalues, 1, linevalues.length));

			// Read values
			line = reader.readLine();
			while (line != null) {
				linevalues = line.split(";");
				HashMap<String, Float> measureValues = new HashMap<String, Float>();

				// linevalues[0] is the web service name, and linevalues[1...n] are the measure values
				for (int i = 1; i < linevalues.length; i++) {
					String measureName = measureNames.get(i - 1);
					String linevalue = linevalues[i];

					// Change decimal point from comma to dot
					linevalue = linevalue.replace(',', '.');

					// Transform to float
					Float measureValue;
					try {
						measureValue = df.parse(linevalue).floatValue();
					} catch (ParseException e) {
						// Empty value
						measureValue = null;
					}
					measureValues.put(measureName, measureValue);
				}
				wsMeasures.put(linevalues[0], measureValues);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wsMeasures;
	}

	/**
	 * Example for adding web services from a CSV file to the ontology.
	 */
	public static void Add_WebServices_From_CSV() {

		// Connect to the ontology.
		OntologyQoSAPI ontology = new OntologyQoSAPI();

		// Read web services from CSV
		HashMap<String, HashMap<String, Float>> wsMeasures = readCSV("csvdata" + File.separator
				+ "System_Level_Granularity_All_Measures_New.csv");

		for (Entry<String, HashMap<String, Float>> wsEntry : wsMeasures.entrySet()) {
			String webService = wsEntry.getKey();
			HashMap<String, Float> measureNamesAndValues = wsEntry.getValue();

			// Add web service.
			ontology.addWebService(webService);
			// System.out.println("webService: " + webService);

			// Iterate over all measures and add them to the ontology.
			for (Entry<String, Float> measureEntry : measureNamesAndValues.entrySet()) {
				String measureName = measureEntry.getKey();
				Float measureValue = measureEntry.getValue();

				// Get the value type of the measure
				String measureValueKind = "Raw";
				String[] splittedMeasureName = measureName.split("\\(");
				if (splittedMeasureName.length > 1) {
					measureName = splittedMeasureName[0];
					measureValueKind = splittedMeasureName[1].split("\\)")[0];
				}

				// System.out.println("   " + measureName + ": " + measureValue);
				if (measureValue != null) {
					ontology.addMeasureToWebService(webService, measureName, measureValueKind, measureValue);
				}
			}
		}

		// Close the connection.
		ontology.close();
	}

	/**
	 * Executes the example.
	 * 
	 * @param args unused parameter.
	 */
	public static void main(String[] args) {
		Add_WebServices_From_CSV();
	}
}


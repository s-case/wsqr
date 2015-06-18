package eu.scase.qosontology.examples;

import eu.scase.qosontology.OntologyQoSAPI;
import eu.scase.qosontology.OntologyQoSQuery;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;

/**
 * Example for reaching Service Level Agreements (SLAs) using the QoS ontology
 * 
 * @author Themistoklis Diamantopoulos
 */
public class SLAExamples {

	/**
	 * Performs a Service Level Agreement (SLA). The SLA is described as follows:<br>
	 * <br>
	 * ID: SLA1<br>
	 * Name: Service Level Agreement 1 - Converting Java applications to other programming languages<br>
	 * Description: I need a service to convert my Java application to other programming languages. The service must
	 * have availability equal to 1 and average quality perception more than 4 (out of 6)<br>
	 * Services involved: JavaToCSharpWS, JavaToPythonWS
	 */
	public static void SLA1() {
		// Connect to the ontology.
		OntologyQoSAPI ontology = new OntologyQoSAPI();

		// Create the query
		OntologyQoSQuery query = new OntologyQoSQuery()
			.selectWebServices("JavaToCSharpWS", "JavaToPythonWS")
			.selectMeasures("Availability_AV", "Quality_Perception")
			.filter("MeasureValueKind(Quality_Perception) = 'Average'")
			.filter("Availability_AV = 1")
			.filter("Quality_Perception > 4")
			.sort("DESC(Availability_AV)", "DESC(Quality_Perception)");

		// Perform the query
		ResultSet results = ontology.performQuery(query);

		// Print the results to screen
		System.out.println("Selected Web Services\t\tAvailability_AV\t\tQuality_Perception");
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution();
			Literal webservicename = solution.getLiteral("WebServiceName");
			Literal availability = solution.getLiteral("Availability_AV");
			Literal qualityperception = solution.getLiteral("Quality_Perception");
			System.out.println(webservicename + "\t\t\t" + availability.getFloat() + "\t\t\t" + qualityperception.getFloat());
		}
		System.out.println();
	}

	/**
	 * Performs a Service Level Agreement (SLA). The SLA is described as follows:<br>
	 * <br>
	 * ID: SLA2<br>
	 * Name: Service Level Agreement 2 - Converting HTML code to other formats<br>
	 * Description: I need a service to convert my HTML code into other file formats. The service must have less than 2
	 * bugs or quality perception more than 4 (out of 6), The number of bugs is computed using the regression model:<br>
	 * Num_Bugs = 0.47352 * Distinct_Method_Invocations_DMI - 1.962222<br>
	 * Services involved: HtmlToExcelWS, HtmlToJspConverterWS, HtmlToLaTexWS
	 */
	public static void SLA2() {
		// Connect to the ontology.
		OntologyQoSAPI ontology = new OntologyQoSAPI();

		// Create the query
		OntologyQoSQuery query = new OntologyQoSQuery()
			.selectWebServices("HtmlToExcelWS", "HtmlToJspConverterWS", "HtmlToLaTexWS")
			.selectMeasures("Quality_Perception", "Distinct_Method_Invocations_DMI")
			.define("NumBugs", "0.47352 * Distinct_Method_Invocations_DMI - 1.962222")
			.filter("MeasureValueKind(Quality_Perception) = 'Average'")
			.filter("NumBugs < 2", "Quality_Perception > 4.0")
			.sort("ASC(NumBugs)", "DESC(Quality_Perception)");

		// Perform the query
		ResultSet results = ontology.performQuery(query);

		// Print the results to screen
		System.out.println("Selected Web Services\t\tNumBugs\t\t\tQuality_Perception");
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution();
			Literal webservicename = solution.getLiteral("WebServiceName");
			Literal bugs = solution.getLiteral("NumBugs");
			Literal qualityperception = solution.getLiteral("Quality_Perception");
			System.out.println(webservicename + "\t\t\t" + bugs.getFloat() + "\t\t\t" + qualityperception.getFloat());
		}
		System.out.println();
	}

	/**
	 * Performs a Service Level Agreement (SLA). The SLA is described as follows:<br>
	 * <br>
	 * ID: SLA3<br>
	 * Name: Service Level Agreement 3 - Generating secure passwords<br>
	 * Description: I need a service to generate passwords. The service must have 0 bugs for security reasons, and its
	 * successability must be more than 0.8<br>
	 * Services involved: PasswordGeneratorWS, SecurePasswordGeneratorWS, RandomDataGeneratorWS
	 */
	public static void SLA3() {
		// Connect to the ontology.
		OntologyQoSAPI ontology = new OntologyQoSAPI();

		// Create the query
		OntologyQoSQuery query = new OntologyQoSQuery()
			.selectWebServices("PasswordGeneratorWS", "SecurePasswordGeneratorWS", "RandomDataGeneratorWS")
			.selectMeasures("Successability_SU", "Bugs")
			// .filter("MeasureValueKind(Bugs) = 'Raw'") // We can comment out this since 'Raw' is the default
			.filter("Successability_SU > 0.8")
			.filter("Bugs = 0")
			.sort("DESC(Successability_SU)", "DESC(Bugs)");

		// Perform the query
		ResultSet results = ontology.performQuery(query);

		// Print the results to screen
		System.out.println("Selected Web Services\t\tSuccessability_SU\tBugs");
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution();
			Literal webservicename = solution.getLiteral("WebServiceName");
			Literal successability = solution.getLiteral("Successability_SU");
			Literal bugs = solution.getLiteral("Bugs");
			System.out.println(webservicename + "\t\t\t" + successability.getFloat() + "\t\t\t" + bugs.getFloat());
		}
		System.out.println();
	}

	/**
	 * Performs a Service Level Agreement (SLA). The SLA is described as follows:<br>
	 * <br>
	 * ID: SLA4<br>
	 * Name: Service Level Agreement 4 - Converting data into different units of measure<br>
	 * Description: I need a service to convert data into different units of measure. The service must have
	 * accessibility index more than 0.8. Note that the probability that accessability index is larger than
	 * 0.891 (P(Accessibility_Index > 0.891)) is computed as:<br>
	 * exp(L12) / (1 + exp(L12)), where L12 = -0.2778 - 0.0027 * Weighted_Methods_per_Class_WMC<br>
	 * Services involved: ComputeUnitsWS, CurrencyConverterWS, MoneyToStringConverterWS, NumberToWordWS,
	 * NumericalConverterWS
	 */
	public static void SLA4() {
		// Connect to the ontology.
		OntologyQoSAPI ontology = new OntologyQoSAPI();

		// Create the query
		OntologyQoSQuery query = new OntologyQoSQuery()
			.selectWebServices("ComputeUnitsWS", "CurrencyConverterWS", "MoneyToStringConverterWS", "NumberToWordWS", "NumericalConverterWS")
			.selectMeasures("Weighted_Methods_per_Class_WMC")
			.define("L12", "-0.2778 - 0.0027 * Weighted_Methods_per_Class_WMC")
			.define("expL12", "((L12 + 3) * (L12 + 3) + 3) / ((L12 - 3) * (L12 - 3) + 3)")
			.define("P_Accessibility_Index", "expL12 / (1 + expL12)")
			.filter("P_Accessibility_Index > 0.4")  // Assuming e.g. that having an accessibility index more than 0.4
													// means having accessibility more than 0.8
			.sort("DESC(P_Accessibility_Index)");

		// For the above computation we assume exp(x) can be found by the 2/2 PadeApproximant
		// See http://mathworld.wolfram.com/PadeApproximant.html
		// According to the approximant exp(x) = ((x + 3) ^ 2 + 3) / ((x - 3) ^ 2 + 3)
		// This should work for values in range [-1, 1]. See the following graph:
		// http://graphsketch.com/?eqn1_color=1&eqn1_eqn=exp(x)&eqn2_color=2&eqn2_eqn=((x%2B3)%20*%20(x%2B3)%20%2B%203)%20%2F%20((x-3)%20*%20(x-3)%20%2B%203)&eqn3_color=3&eqn3_eqn=&eqn4_color=4&eqn4_eqn=&eqn5_color=5&eqn5_eqn=&eqn6_color=6&eqn6_eqn=&x_min=-5.5&x_max=5.5&y_min=-10.5&y_max=10.5&x_tick=1&y_tick=1&x_label_freq=5&y_label_freq=5&do_grid=0&do_grid=1&bold_labeled_lines=0&bold_labeled_lines=1&line_width=4&image_w=850&image_h=525
		
		// Perform the query
		ResultSet results = ontology.performQuery(query);

		// Print the results to screen
		System.out.println("Selected Web Services\t\tP_Accessibility_IndexIndex");
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution();
			Literal webservicename = solution.getLiteral("WebServiceName");
			Literal accessibility = solution.getLiteral("P_Accessibility_Index");
			System.out.println(webservicename + "\t\t\t" + accessibility.getFloat());
		}
		System.out.println();
	}

	/**
	 * Performs a Service Level Agreement (SLA). The SLA is described as follows:<br>
	 * <br>
	 * ID: SLA5<br>
	 * Name: Service Level Agreement 5 - Exporting files into different file formats<br>
	 * Description: I need a service to export my files into other file formats. The service must have accessibility
	 * more than 0.85, quality perception more than 3 and there must be more than 1 distinct classes<br>
	 * Services involved: CSVGeneratorWS, ExcelToSqlWS, XMLtoRDFConverterWS, YaHP-ConverterWS
	 */
	public static void SLA5() {
		// Connect to the ontology.
		OntologyQoSAPI ontology = new OntologyQoSAPI();

		// Create the query
		OntologyQoSQuery query = new OntologyQoSQuery()
			.selectWebServices("CSVGeneratorWS", "ExcelToSqlWS", "XMLtoRDFConverterWS", "YaHP-ConverterWS")
			.selectMeasures("Accessability_AC", "Quality_Perception", "Distinct_Classes_DC")
			.filter("MeasureValueKind(Quality_Perception) = 'Average'")
			// .filter("MeasureValueKind(Distinct_Classes_DC) = 'Raw'") // We can comment out this since 'Raw' is the default
			.filter("Accessability_AC > 0.85")
			.filter("Quality_Perception > 3")
			.filter("Distinct_Classes_DC > 1")
			.sort("DESC(Accessability_AC)", "DESC(Quality_Perception)");

		// Perform the query
		ResultSet results = ontology.performQuery(query);

		// Print the results to screen
		System.out.println("Selected Web Services\t\tAccessability_AC\tQuality_Perception\tDistinct_Classes");
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution();
			Literal webservicename = solution.getLiteral("WebServiceName");
			Literal accessability = solution.getLiteral("Accessability_AC");
			Literal qualityperception = solution.getLiteral("Quality_Perception");
			Literal distinctclasses = solution.getLiteral("Distinct_Classes_DC");
			System.out.println(webservicename + "\t\t\t" + accessability.getFloat() + "\t\t\t" + qualityperception.getFloat() + "\t\t\t" + distinctclasses.getFloat());
		}
		System.out.println();
	}


	/**
	 * Executes the examples.
	 * 
	 * @param args unused parameter.
	 */
	public static void main(String[] args) {
		SLA1();
		SLA2();
		SLA3();
		SLA4();
		SLA5();
	}
}


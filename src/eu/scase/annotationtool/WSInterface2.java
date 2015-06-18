package eu.scase.annotationtool;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.*; 


/**
 * GUI and Engine of the WSQR Annotation Tool
 * 
 * @author Davide Tosi and Matteo Binda
 */


public class WSInterface2 {
	private Element root;
	private String pathJar, end;
	private int  s, x=170;
	private String ExternalValidationM, InternalValidationM, InternalMK, MeasureVK;
	private Document document;
	private JFrame frame;
	private JLabel lblWS, lblMeasureSaved;
	private JTextField textFieldName,textField, textField2, textField3, textField4, textFieldAttributeName,textFieldAttributeValue, textFieldStatisticalSignificanceLevel, textFieldPValue;
	private JTextField textFieldStatisticalTestUsed, textFieldAccuracyLevel, textFieldAccuracyIndicatorUsed;
	private JRadioButton rdbtnInternal, rdbtnExternal, bAverage, bRaw, bStandardDeviation, bMedian, bMeasurementTheory, bAxiomaticApproach,bDynamicMeasure, bStaticMeasure ;
	private JRadioButton bMeasurementTheoryE, bAxiomaticApproachE, bEmpiricalValidation,bNAValueKind,bNAExternalValidation, bNAInternalVMN, bNAInternalMK;
	public WSInterface2() throws ClassNotFoundException{

		frame = new JFrame();
		frame.getContentPane();
		frame.setVisible(true);
		frame.setBounds(250, 250, 750,650); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.setResizable(false);

		//window label 
		lblWS = new JLabel("WSQualityRepresentation");
		springLayout.putConstraint(SpringLayout.NORTH, lblWS, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblWS, 240, SpringLayout.WEST, frame.getContentPane());
		lblWS.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWS.setToolTipText("");
		frame.getContentPane().add(lblWS);

		//JLabel service name
		final JLabel lblName = new JLabel("Web Service Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblName, 55, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblName, 295, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblName);

		//Text box for Service Name
		textFieldName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textFieldName, 6, SpringLayout.SOUTH, lblName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldName, -120, SpringLayout.WEST, lblName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldName, 150, SpringLayout.EAST, lblName);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		//Radio button for Internal Measures
		rdbtnInternal = new JRadioButton("InternalMeasure");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnInternal, 110, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rdbtnInternal, 41, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rdbtnInternal, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(rdbtnInternal);

		//Radio button for External Measures
		rdbtnExternal = new JRadioButton("ExternalMeasure");
		springLayout.putConstraint(SpringLayout.NORTH, rdbtnExternal, 135, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, rdbtnExternal, 41, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, rdbtnExternal, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(rdbtnExternal);

		//Button group
		final ButtonGroup group = new ButtonGroup();
		group.add(rdbtnInternal);
		group.add(rdbtnExternal);


		//JLabel Measure name
		final JLabel lblMeasureName = new JLabel("MeasureName");
		springLayout.putConstraint(SpringLayout.NORTH,lblMeasureName, 180, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMeasureName, 30, SpringLayout.EAST, frame);
		springLayout.putConstraint(SpringLayout.EAST, lblMeasureName, 150, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblMeasureName);

		//Text box for Measure Name
		textField2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField2, 6, SpringLayout.SOUTH, lblMeasureName);
		springLayout.putConstraint(SpringLayout.WEST, textField2, 0, SpringLayout.WEST, lblMeasureName);
		springLayout.putConstraint(SpringLayout.EAST, textField2, 98, SpringLayout.EAST, lblMeasureName);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);	

		//JLabel for Measure Definition
		final JLabel lblMeasureDefinition = new JLabel("MeasureDefinition");
		springLayout.putConstraint(SpringLayout.NORTH,lblMeasureDefinition, 240, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMeasureDefinition, 30, SpringLayout.EAST, frame);
		springLayout.putConstraint(SpringLayout.EAST, lblMeasureDefinition, 150, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblMeasureDefinition);

		//JLabel for Attribute Description
		final JLabel lblDescription = new JLabel("Description");
		springLayout.putConstraint(SpringLayout.NORTH,lblDescription, 260, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDescription, 30, SpringLayout.EAST, frame);
		springLayout.putConstraint(SpringLayout.EAST, lblDescription, 150, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblDescription);

		//Description
		textField3 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField3, 0, SpringLayout.SOUTH, lblDescription);
		springLayout.putConstraint(SpringLayout.WEST, textField3, 0, SpringLayout.WEST, lblDescription);
		springLayout.putConstraint(SpringLayout.EAST, textField3, 98, SpringLayout.EAST, lblDescription);
		frame.getContentPane().add(textField3);
		textField3.setColumns(10);
		
		//Label
		final JLabel lblFormula = new JLabel("Formula");
		springLayout.putConstraint(SpringLayout.NORTH,lblFormula, 300, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblFormula, 30, SpringLayout.EAST, frame);
		springLayout.putConstraint(SpringLayout.EAST, lblFormula, 150, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblFormula);

		textField4 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField4, 0, SpringLayout.SOUTH, lblFormula);
		springLayout.putConstraint(SpringLayout.WEST, textField4, 0, SpringLayout.WEST, lblFormula);
		springLayout.putConstraint(SpringLayout.EAST, textField4, 98, SpringLayout.EAST, lblFormula);
		frame.getContentPane().add(textField4);
		textField4.setColumns(10);


		//JLabel Service Measure Value
		final JLabel lblMeasureValue = new JLabel("MeasureValue");
		springLayout.putConstraint(SpringLayout.NORTH, lblMeasureValue, 350, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMeasureValue, 30, SpringLayout.EAST, frame);
		springLayout.putConstraint(SpringLayout.EAST, lblMeasureValue, 150, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblMeasureValue);

		//Text Box for Measure Value
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 6, SpringLayout.SOUTH, lblMeasureValue);
		springLayout.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblMeasureValue);
		springLayout.putConstraint(SpringLayout.EAST, textField, 98, SpringLayout.EAST, lblMeasureValue);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		//JLabel MeasureValueKind 
		final JLabel lblMeasureValueKind  = new JLabel("MeasureValueKind ");
		springLayout.putConstraint(SpringLayout.NORTH, lblMeasureValueKind , 410, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMeasureValueKind , 30, SpringLayout.EAST, frame);
		springLayout.putConstraint(SpringLayout.EAST, lblMeasureValueKind , 150, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblMeasureValueKind );

		bAverage = new JRadioButton("Average");
		springLayout.putConstraint(SpringLayout.NORTH, bAverage, 430, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bAverage, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bAverage, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bAverage);

		bRaw = new JRadioButton("Raw");
		springLayout.putConstraint(SpringLayout.NORTH, bRaw, 450, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bRaw, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bRaw, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bRaw);

		bStandardDeviation = new JRadioButton("Standard Deviation");
		springLayout.putConstraint(SpringLayout.NORTH, bStandardDeviation, 470, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bStandardDeviation, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bStandardDeviation, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bStandardDeviation);

		bMedian = new JRadioButton("Median");
		springLayout.putConstraint(SpringLayout.NORTH, bMedian, 490, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bMedian, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bMedian, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bMedian);

		bNAValueKind = new JRadioButton("N/A");
		springLayout.putConstraint(SpringLayout.NORTH, bNAValueKind, 510, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bNAValueKind, 30, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, bNAValueKind, 164, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bNAValueKind);

		final ButtonGroup group2 = new ButtonGroup();

		group2.add(bRaw);
		group2.add(bAverage);
		group2.add(bMedian);
		group2.add(bStandardDeviation);
		group2.add(bNAValueKind);

		final JLabel lblMs = new JLabel("Measure Saved: ");
		lblMs.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblMs, 150, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblMs, 550, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblMs);

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		/*
		 * INTERNAL MEASURES ENGINEERING 
		 */

		final JLabel lblIM = new JLabel("InternalMeasure");
		lblIM.setVisible(false);
		lblIM.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblIM, 120, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblIM, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblIM);


		final JLabel lblvmn = new JLabel("InternalValidationMeansName");
		lblvmn.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblvmn, 150, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblvmn, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblvmn);

		bMeasurementTheory = new JRadioButton("MeasurementTheory");
		bMeasurementTheory.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bMeasurementTheory, 170, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bMeasurementTheory, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bMeasurementTheory);

		bAxiomaticApproach = new JRadioButton("AxiomaticApproach ");
		bAxiomaticApproach.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bAxiomaticApproach, 190, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bAxiomaticApproach, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bAxiomaticApproach);

		bNAInternalVMN = new JRadioButton("N/A ");
		bNAInternalVMN.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bNAInternalVMN, 210, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bNAInternalVMN, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bNAInternalVMN);

		final ButtonGroup group3 = new ButtonGroup();
		group3.add(bMeasurementTheory);
		group3.add(bAxiomaticApproach);
		group3.add(bNAInternalVMN);

		final JLabel lblInternalValidationMeansAttributes  = new JLabel("InternalValidationMeansAttributes ");
		lblInternalValidationMeansAttributes.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblInternalValidationMeansAttributes , 260, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblInternalValidationMeansAttributes , 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblInternalValidationMeansAttributes );

		final JLabel lblAttributeName = new JLabel("AttributeName");
		lblAttributeName.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblAttributeName, 280, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAttributeName, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblAttributeName);

		textFieldAttributeName = new JTextField();
		textFieldAttributeName.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldAttributeName, 300, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldAttributeName, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldAttributeName);
		textFieldAttributeName.setColumns(10);

		final JLabel lblAttributeValue = new JLabel("AttributeValue");
		lblAttributeValue.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblAttributeValue, 320, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAttributeValue, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblAttributeValue);

		textFieldAttributeValue = new JTextField();
		textFieldAttributeValue.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldAttributeValue, 340, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldAttributeValue, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldAttributeValue);
		textFieldAttributeValue.setColumns(10);

		final JLabel lblInternalMeasureKind = new JLabel("InternalMeasureKind");
		lblInternalMeasureKind.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblInternalMeasureKind, 390, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblInternalMeasureKind, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblInternalMeasureKind);

		bDynamicMeasure = new JRadioButton("DynamicMeasure");
		bDynamicMeasure.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bDynamicMeasure, 410, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bDynamicMeasure, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bDynamicMeasure);

		bStaticMeasure = new JRadioButton("StaticMeasure");
		bStaticMeasure.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bStaticMeasure, 430, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bStaticMeasure, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bStaticMeasure);

		bNAInternalMK = new JRadioButton("N/A");
		bNAInternalMK.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bNAInternalMK, 450, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bNAInternalMK, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bNAInternalMK);


		final ButtonGroup group4 = new ButtonGroup();
		group4.add(bDynamicMeasure);
		group4.add(bStaticMeasure);
		group4.add(bNAInternalMK);


		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		/*
		 * EXTERNAL MEASURES ENGINEERING 
		 */

		final JLabel lblEM = new JLabel("ExternalMeasure");
		lblEM.setVisible(false);
		lblEM.setFont(new Font("Tahoma", Font.BOLD, 14));
		springLayout.putConstraint(SpringLayout.NORTH, lblEM, 120, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEM, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblEM);

		final JLabel lblExternalValidationMeansName = new JLabel("ExternalValidationMeansName");
		lblExternalValidationMeansName.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblExternalValidationMeansName, 150, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblExternalValidationMeansName, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblExternalValidationMeansName);

		bMeasurementTheoryE = new JRadioButton("MeasurementTheory");
		bMeasurementTheoryE.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bMeasurementTheoryE, 170, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bMeasurementTheoryE, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bMeasurementTheoryE);

		bAxiomaticApproachE = new JRadioButton("AxiomaticApproach ");
		bAxiomaticApproachE.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bAxiomaticApproachE, 190, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bAxiomaticApproachE, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bAxiomaticApproachE);

		bEmpiricalValidation = new JRadioButton("EmpiricalValidation ");
		bEmpiricalValidation.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bEmpiricalValidation, 210, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bEmpiricalValidation, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bEmpiricalValidation);

		bNAExternalValidation = new JRadioButton("N/A ");
		bNAExternalValidation.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, bNAExternalValidation, 230, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, bNAExternalValidation, 310, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(bNAExternalValidation);

		final ButtonGroup group5 = new ButtonGroup();
		group5.add(bMeasurementTheoryE);
		group5.add(bAxiomaticApproachE);
		group5.add(bEmpiricalValidation);
		group5.add(bNAExternalValidation);

		final JLabel lblStatisticalSignificanceLevel  = new JLabel("StatisticalSignificanceLevel ");
		lblStatisticalSignificanceLevel.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblStatisticalSignificanceLevel , 260, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStatisticalSignificanceLevel , 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblStatisticalSignificanceLevel );

		textFieldStatisticalSignificanceLevel = new JTextField();
		textFieldStatisticalSignificanceLevel.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldStatisticalSignificanceLevel, 280, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldStatisticalSignificanceLevel, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldStatisticalSignificanceLevel);
		textFieldStatisticalSignificanceLevel.setColumns(10);

		final JLabel lblPValue = new JLabel("PValue");
		lblPValue.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblPValue, 310, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblPValue, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblPValue);

		textFieldPValue = new JTextField();
		textFieldPValue.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldPValue, 330, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldPValue, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldPValue);
		textFieldPValue.setColumns(10);

		final JLabel lblStatisticalTestUsed = new JLabel("StatisticalTestUsed");
		lblStatisticalTestUsed.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblStatisticalTestUsed, 360, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStatisticalTestUsed, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblStatisticalTestUsed);

		textFieldStatisticalTestUsed = new JTextField();
		textFieldStatisticalTestUsed.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldStatisticalTestUsed, 380, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldStatisticalTestUsed, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldStatisticalTestUsed);
		textFieldStatisticalTestUsed.setColumns(10);

		final JLabel lblAccuracyLevel  = new JLabel("AccuracyLevel-Value");
		lblAccuracyLevel .setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblAccuracyLevel , 410, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAccuracyLevel , 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblAccuracyLevel );

		textFieldAccuracyLevel  = new JTextField();
		textFieldAccuracyLevel.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldAccuracyLevel , 430, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldAccuracyLevel , 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldAccuracyLevel );
		textFieldAccuracyLevel.setColumns(10);

		final JLabel lblAccuracyIndicatorUsed = new JLabel("AccuracyIndicatorUsed-Name");
		lblAccuracyIndicatorUsed.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, lblAccuracyIndicatorUsed, 460, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAccuracyIndicatorUsed, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(lblAccuracyIndicatorUsed);

		textFieldAccuracyIndicatorUsed = new JTextField();
		textFieldAccuracyIndicatorUsed.setVisible(false);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldAccuracyIndicatorUsed, 480, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textFieldAccuracyIndicatorUsed, 310, SpringLayout.EAST, frame);
		frame.getContentPane().add(textFieldAccuracyIndicatorUsed);
		textFieldAccuracyIndicatorUsed.setColumns(10);

		///////////////////////////////////////////////////////////////////////////////////////////
		/*
		 *  JBUTTON TO ADD AND GENERATE WSQR REPRESENTATIONS
		 */

		final JButton btnAdd = new JButton("Add Measure");
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd,530, SpringLayout.SOUTH, frame);
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 250, SpringLayout.WEST, frame);
		frame.getContentPane().add(btnAdd);

		JButton btnGenerate = new JButton("Generate XML");
		springLayout.putConstraint(SpringLayout.NORTH, btnGenerate,530, SpringLayout.SOUTH, frame);
		springLayout.putConstraint(SpringLayout.WEST, btnGenerate, 400, SpringLayout.WEST, frame);
		frame.getContentPane().add(btnGenerate);



		// Event for the externalvalidationname
		ActionListener radioButtonActionListener3 = new ActionListener(){
			public void actionPerformed(ActionEvent ae) {

				ExternalValidationM="MeasurementTheory";

				;
			}};
			bMeasurementTheoryE.addActionListener(radioButtonActionListener3);
			ActionListener radioButtonActionListener4 = new ActionListener(){
				public void actionPerformed(ActionEvent ae) {

					ExternalValidationM="AxiomaticApproach";

					;
				}};
				bAxiomaticApproachE.addActionListener(radioButtonActionListener4);
				ActionListener radioButtonActionListener5 = new ActionListener(){
					public void actionPerformed(ActionEvent ae) {

						ExternalValidationM="EmpiricalValidation";

						;
					}};
					bEmpiricalValidation.addActionListener(radioButtonActionListener5);  
					ActionListener radioButtonActionListenerNae = new ActionListener(){
						public void actionPerformed(ActionEvent ae) {

							ExternalValidationM="";

							;
						}};
						bNAExternalValidation.addActionListener(radioButtonActionListenerNae);
						if(group5.isSelected(null)){ExternalValidationM="";}

						
						//Event for the internalvalidationname
						ActionListener radioButtonActionListener6 = new ActionListener(){
							public void actionPerformed(ActionEvent ae) {

								InternalValidationM="MeasurementTheory";

								;
							}};
							bMeasurementTheory.addActionListener(radioButtonActionListener6);
							ActionListener radioButtonActionListener7 = new ActionListener(){
								public void actionPerformed(ActionEvent ae) {

									InternalValidationM="AxiomaticApproach";

									;
								}};
								bAxiomaticApproach.addActionListener(radioButtonActionListener7); 
								ActionListener radioButtonActionListenerInternalVMN = new ActionListener(){
									public void actionPerformed(ActionEvent ae) {

										InternalValidationM="";

										;
									}};
									bNAInternalVMN.addActionListener(radioButtonActionListenerInternalVMN );
									if(group3.isSelected(null)){InternalValidationM="";}

									
									//Event for the selection between Dynamic or Static Measures
									ActionListener radioButtonActionListener8 = new ActionListener(){
										public void actionPerformed(ActionEvent ae) {

											InternalMK="DynamicMeasure";

											;
										}};
										bDynamicMeasure.addActionListener(radioButtonActionListener8);
										ActionListener radioButtonActionListener9 = new ActionListener(){
											public void actionPerformed(ActionEvent ae) {

												InternalMK="StaticMeasure";

												;
											}};
											bStaticMeasure.addActionListener(radioButtonActionListener9); 
											ActionListener radioButtonActionListenerNaIntenralMK = new ActionListener(){
												public void actionPerformed(ActionEvent ae) {

													InternalMK=null;

													;
												}};
												bNAInternalMK.addActionListener(radioButtonActionListenerNaIntenralMK);
												if(group4.isSelected(null)){InternalMK=null;}

												//Event for measurevaluekind
												ActionListener radioButtonActionListener10 = new ActionListener(){
													public void actionPerformed(ActionEvent ae) {

														MeasureVK="Average";

														;
													}};
													bAverage.addActionListener(radioButtonActionListener10);
													ActionListener radioButtonActionListener11 = new ActionListener(){
														public void actionPerformed(ActionEvent ae) {

															MeasureVK="Raw";


															;
														}};
														bRaw.addActionListener(radioButtonActionListener11);
														ActionListener radioButtonActionListener12 = new ActionListener(){
															public void actionPerformed(ActionEvent ae) {

																MeasureVK="StandaradDeviation";

																;
															}};
															bStandardDeviation.addActionListener(radioButtonActionListener12);
															ActionListener radioButtonActionListener13 = new ActionListener(){
																public void actionPerformed(ActionEvent ae) {

																	MeasureVK="Median";

																	;
																}};
																bMedian.addActionListener(radioButtonActionListener13);
																ActionListener radioButtonActionListener14 = new ActionListener(){
																	public void actionPerformed(ActionEvent ae) {

																		MeasureVK=null;

																		;
																	}};
																	bNAValueKind.addActionListener(radioButtonActionListener14);

																	if(group2.isSelected(null)){MeasureVK=null;}


																	//Event after pressing button “Add”
																	//Root Element 
																	root = new Element("WSQRMeasure"); 
																	//Document
																	document = new Document(root); 

																	btnAdd.addActionListener(new ActionListener(){


																		public void actionPerformed(ActionEvent ae){
																			final String s =textField2.getText();
																			
																			lblMeasureSaved = new JLabel(s);
																			lblMeasureSaved.setText("- "+s);
																			springLayout.putConstraint(SpringLayout.NORTH,lblMeasureSaved, x, SpringLayout.NORTH, frame);
																			springLayout.putConstraint(SpringLayout.WEST, lblMeasureSaved, 560, SpringLayout.WEST, frame);
																			frame.getContentPane().add(lblMeasureSaved);
																			frame.validate();
																			x=x+20;
																			if(rdbtnExternal.isSelected())

																			{		

																				// External Measures
																				Element item1 = new Element("ExternalMeasure"); 

																				Element descr1 = new Element("ExternalMeasureValidationMeans");
																				descr1.setAttribute("ExternalValidationMeansName",ExternalValidationM);

																				Element external = new Element ("ExternalValidationMeansAttribute");

																				Element StatisticalSignificanceLevel = new Element("StatisticalSignificanceLevel");
																				StatisticalSignificanceLevel.setAttribute("Value",textFieldStatisticalSignificanceLevel.getText());

																				Element PValue = new Element("PValue");
																				PValue.setAttribute("Value",textFieldPValue.getText());

																				Element StatisticalTestUsed = new Element("StatisticalTestUsed");
																				StatisticalTestUsed.setAttribute("Name",textFieldStatisticalTestUsed.getText());

																				Element AccuracyLevel = new Element("AccuracyLevel");
																				AccuracyLevel.setAttribute("Value",textFieldAccuracyLevel.getText());

																				Element AccuracyIndicatorUsed = new Element("AccuracyIndicatorUsed");
																				AccuracyIndicatorUsed.setAttribute("Name",textFieldAccuracyIndicatorUsed.getText());

																				item1.addContent(descr1); 
																				descr1.addContent(external);
																				external.addContent(StatisticalSignificanceLevel);
																				external.addContent(PValue);
																				external.addContent(StatisticalTestUsed);
																				external.addContent(AccuracyLevel);
																				external.addContent(AccuracyIndicatorUsed);
																				root.addContent(item1);

																				Element MeasureName = new Element("MeasureName"); 
																				MeasureName.setText(textField2.getText());
																				root.addContent(MeasureName);



																				Element MeasureValue = new Element("MeasureValue"); 
																				MeasureValue.setText(textField.getText());
																				root.addContent(MeasureValue);

																				Element MeasureValueKind = new Element("MeasureValueKind"); 
																				MeasureValueKind.setText(MeasureVK);
																				root.addContent(MeasureValueKind);

																				Element MeasureDefinition = new Element ("MeasureDefinition");
																				MeasureDefinition.setAttribute("Description",textField3.getText());
																				MeasureDefinition.setAttribute("Formula",textField4.getText());
																				root.addContent(MeasureDefinition);

																			}
																			else if(rdbtnInternal.isSelected()){


																				{
																					Element item1 = new Element("InternalMeasure"); 

																					Element descr1 = new Element("InternalMeasureValidationMeans");
																					descr1.setAttribute("InternalValidationMeansName", InternalValidationM);


																					Element InternalValidationMeansAttributes = new Element ("InternalValidationMeansAttributes");
																					InternalValidationMeansAttributes.setAttribute("AttributeName",textFieldAttributeName.getText());
																					InternalValidationMeansAttributes.setAttribute("AttributeValue",textFieldAttributeValue.getText());
																					descr1.addContent(InternalValidationMeansAttributes);


																					Element InternalMeasureKind = new Element("InternalMeasureKind"); 
																					InternalMeasureKind.setText(InternalMK);


																					item1.addContent(descr1); 
																					item1.addContent(InternalMeasureKind);
																					root.addContent(item1);

																					Element MeasureName = new Element("MeasureName"); 
																					MeasureName.setText(textField2.getText());
																					root.addContent(MeasureName);

																					Element MeasureValue = new Element("MeasureValue"); 
																					MeasureValue.setText(textField.getText());
																					root.addContent(MeasureValue);

																					Element MeasureValueKind = new Element("MeasureValueKind"); 
																					MeasureValueKind.setText(MeasureVK);
																					root.addContent(MeasureValueKind);

																					Element MeasureDefinition = new Element ("MeasureDefinition");
																					MeasureDefinition.setAttribute("Description",textField3.getText());
																					MeasureDefinition.setAttribute("Formula",textField4.getText());
																					root.addContent(MeasureDefinition);
																				}

																			}



																		}	}	); 


																	btnGenerate.addActionListener(new ActionListener(){
																		public void actionPerformed(ActionEvent ae){



																			try {

																				//Create the XMLOutputter Object
																				XMLOutputter outputter = new XMLOutputter(); 
																				//Imposto il formato dell'outputter come "bel formato" 
																				outputter.setFormat(Format.getPrettyFormat()); 

																				//Create the Output file xml.foo 
																				outputter.output(document, new FileOutputStream(textFieldName.getText()+".xml")); 
																				//If you need to print the XML output on the standard output 
																				//outputter.output(document, System.out);

																			}  
																			catch (IOException e) { 
																				System.err.println("Errore durante il parsing del documento");
																				e.printStackTrace(); 

																			}

																			//String for the PATH
																			pathJar = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
																			s = pathJar.lastIndexOf("/");
																			end = pathJar.substring(0, s);
																			JOptionPane.showMessageDialog(null, "Your WSQR_"+textFieldName.getText()+".xml"+" file has been saved into folder: "+ end, "File Saved", JOptionPane.INFORMATION_MESSAGE);

																		}
																	});      	


																	/*
																	 * Action Listener for internal measure
																	 */
																	ActionListener radioButtonActionListener = new ActionListener(){
																		public void actionPerformed(ActionEvent ae) {
																			lblIM.setVisible(true); lblEM.setVisible(false); 
																			lblvmn.setVisible(true); lblExternalValidationMeansName.setVisible(false);
																			bMeasurementTheory.setVisible(true); bMeasurementTheoryE.setVisible(false);
																			bAxiomaticApproach.setVisible(true); bAxiomaticApproachE.setVisible(false);
																			lblInternalMeasureKind.setVisible(true); bEmpiricalValidation.setVisible(false);
																			bDynamicMeasure.setVisible(true); lblStatisticalSignificanceLevel.setVisible(false);
																			bStaticMeasure.setVisible(true); textFieldStatisticalSignificanceLevel.setVisible(false);
																			lblAttributeName.setVisible(true); lblPValue.setVisible(false);
																			textFieldAttributeName.setVisible(true); textFieldPValue.setVisible(false);
																			lblAttributeValue.setVisible(true); lblStatisticalTestUsed.setVisible(false);
																			textFieldAttributeValue.setVisible(true); textFieldStatisticalTestUsed.setVisible(false);
																			lblAccuracyLevel.setVisible(false);lblInternalValidationMeansAttributes.setVisible(true);
																			textFieldAccuracyLevel.setVisible(false); bNAExternalValidation.setVisible(false);
																			lblAccuracyIndicatorUsed.setVisible(false); bNAInternalVMN.setVisible(true);
																			textFieldAccuracyIndicatorUsed.setVisible(false); bNAInternalMK.setVisible(true);
																			lblMs.setVisible(true);
																			;
																		}};
																		rdbtnInternal.addActionListener(radioButtonActionListener);

																		/*
																		 * Action listener for external measure
																		 */
																		ActionListener radioButtonActionListener2 = new ActionListener() {
																			public void actionPerformed(ActionEvent ae) {
																				lblEM.setVisible(true);lblIM.setVisible(false);
																				lblExternalValidationMeansName.setVisible(true);lblvmn.setVisible(false);
																				bMeasurementTheoryE.setVisible(true); bMeasurementTheory.setVisible(false);
																				bAxiomaticApproachE.setVisible(true); bAxiomaticApproach.setVisible(false);
																				bEmpiricalValidation.setVisible(true); lblInternalMeasureKind.setVisible(false);
																				lblStatisticalSignificanceLevel.setVisible(true); bDynamicMeasure.setVisible(false);
																				textFieldStatisticalSignificanceLevel.setVisible(true); bStaticMeasure.setVisible(false);
																				lblPValue.setVisible(true); lblAttributeName.setVisible(false);
																				textFieldPValue.setVisible(true); textFieldAttributeName.setVisible(false);
																				lblStatisticalTestUsed.setVisible(true); lblAttributeValue.setVisible(false);
																				textFieldStatisticalTestUsed.setVisible(true); textFieldAttributeValue.setVisible(false);
																				lblAccuracyLevel.setVisible(true);lblInternalValidationMeansAttributes.setVisible(false);
																				textFieldAccuracyLevel.setVisible(true);bNAExternalValidation.setVisible(true);
																				lblAccuracyIndicatorUsed.setVisible(true); bNAInternalVMN.setVisible(false);
																				textFieldAccuracyIndicatorUsed.setVisible(true); bNAInternalMK.setVisible(false);
																				lblMs.setVisible(true);

																				;
																			}};
																			rdbtnExternal.addActionListener(radioButtonActionListener2);
	}

}




import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;


public class Spreadsheet extends JFrame {

	int numberOfColumns = 7;

	Button done = new Button( "Done" );
	Button undo = new Button ( "Undo");

	Map<String,Expression> variables = new HashMap<String,Expression>();

//	TextField eqtA = new TextField(numberOfColumns);
//	TextField eqtB = new TextField(numberOfColumns);
//	TextField eqtC = new TextField(numberOfColumns);
//	TextField eqtD = new TextField(numberOfColumns);
//	TextField eqtE = new TextField(numberOfColumns);
//	TextField eqtF = new TextField(numberOfColumns);
//	TextField eqtG = new TextField(numberOfColumns);
//	TextField eqtH = new TextField(numberOfColumns);
//	TextField eqtI = new TextField(numberOfColumns);
//
//	TextField valueA = new TextField(numberOfColumns);
//	TextField valueB = new TextField(numberOfColumns);
//	TextField valueC = new TextField(numberOfColumns);
//	TextField valueD = new TextField(numberOfColumns);
//	TextField valueE = new TextField(numberOfColumns);
//	TextField valueF = new TextField(numberOfColumns);
//	TextField valueG = new TextField(numberOfColumns);
//	TextField valueH = new TextField(numberOfColumns);
//	TextField valueI = new TextField(numberOfColumns);

	TextField[] eqtView = new TextField[9];
	TextField[] valueView = new TextField[9];
	
	public Spreadsheet( int width, int height ) {
		setTitle( "Button Example" );
		setSize( width, height );
		setLayout( new GridLayout(4,1) );



		//title row
		Panel title = new Panel(new FlowLayout());
		char name = 'A';
		Label col;
		Dimension d = new Dimension(70,1);

		for (int i = 0; i <9; i++){
			col = new Label ("$" + name, Label.CENTER);
			col.setPreferredSize(d);
			name++;
			title.add(col);
		}

		add(title);

		//equation view
		Panel equations = new Panel(new FlowLayout());

//		equations.add(eqtA);
//		equations.add(eqtB);
//		equations.add(eqtC);
//		equations.add(eqtD);
//		equations.add(eqtE);
//		equations.add(eqtF);
//		equations.add(eqtG);
//		equations.add(eqtH);
//		equations.add(eqtI);
		
		for (int i = 0; i < 9; i++){
			eqtView[i] = new TextField();
			equations.add(eqtView[i]);
		}
		add(equations);


		//value view
		Panel values = new Panel(new FlowLayout());

//		values.add(valueA);
//		values.add(valueB);
//		values.add(valueC);
//		values.add(valueD);
//		values.add(valueE);
//		values.add(valueF);
//		values.add(valueG);
//		values.add(valueH);
//		values.add(valueI);
		
		for (int i = 0; i < 9; i++){
			valueView[i] = new TextField();
			equations.add(valueView[i]);
		}
		add(values);

		//done button
		Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER));
		buttons.add(done);
		buttons.add(undo);
		add(buttons);
		done.addActionListener(new Calculation());
//		undo.addActionListener(this);

		setVisible(true);
	}



	public class Calculation implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub


			setVariables();

			valueA.setText(Double.toString(getResult(eqtA.getText())));
			variables.put("$A", new Number(getResult(eqtA.getText())));

			valueB.setText(Double.toString(getResult(eqtB.getText())));
			variables.put("$B", new Number(getResult(eqtB.getText())));

			valueC.setText(Double.toString(getResult(eqtC.getText())));
			variables.put("$C", new Number(getResult(eqtC.getText())));

			valueD.setText(Double.toString(getResult(eqtD.getText())));
			variables.put("$D", new Number(getResult(eqtD.getText())));

			valueE.setText(Double.toString(getResult(eqtE.getText())));
			variables.put("$E", new Number(getResult(eqtE.getText())));

			valueF.setText(Double.toString(getResult(eqtF.getText())));
			variables.put("$F", new Number(getResult(eqtF.getText())));

			valueG.setText(Double.toString(getResult(eqtG.getText())));
			variables.put("$G", new Number(getResult(eqtG.getText())));

			valueH.setText(Double.toString(getResult(eqtH.getText())));
			variables.put("$H", new Number(getResult(eqtH.getText())));

			valueI.setText(Double.toString(getResult(eqtI.getText())));
			variables.put("$I", new Number(getResult(eqtI.getText())));


		}

		public void setVariables(){

			if (isNumber(eqtA.getText()))
				variables.put("$A", new Number(Double.parseDouble(eqtA.getText())));
			if (isNumber(eqtB.getText()))
				variables.put("$B", new Number(Double.parseDouble(eqtB.getText())));
			if (isNumber(eqtC.getText()))
				variables.put("$C", new Number(Double.parseDouble(eqtC.getText())));
			if (isNumber(eqtD.getText()))
				variables.put("$D", new Number(Double.parseDouble(eqtD.getText())));
			if (isNumber(eqtE.getText()))
				variables.put("$E", new Number(Double.parseDouble(eqtE.getText())));
			if (isNumber(eqtF.getText()))
				variables.put("$F", new Number(Double.parseDouble(eqtF.getText())));
			if (isNumber(eqtG.getText()))
				variables.put("$G", new Number(Double.parseDouble(eqtG.getText())));
			if (isNumber(eqtH.getText()))
				variables.put("$I", new Number(Double.parseDouble(eqtH.getText())));
			if (isNumber(eqtI.getText()))
				variables.put("$I", new Number(Double.parseDouble(eqtI.getText())));

		}
		
		public Double getResult(String expression){

			Evaluator sentence = new Evaluator(expression);
			Double result = sentence.interpret(variables);

			return result;
		}

		public boolean isNumber(String curr){
			try {
				Double.parseDouble(curr);
			}
			catch(NumberFormatException e){
				return false;
			}

			return true;
		}
	}
	
	public static void main( String args[] ) {
		new Spreadsheet( 1000, 150 );
	}
}





import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;


public class Spreadsheet extends JFrame {

	int numberOfColumns = 7;

	Button done = new Button( "Done" );
	Button undo = new Button ( "Undo");

	Map<String,Expression> variables = new HashMap<String,Expression>();


	TextField[] eqtView = new TextField[9];
	TextField[] valueView = new TextField[9];

	ArrayList<State> history = new ArrayList<State>();
	
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


		for (int i = 0; i < 9; i++){
			eqtView[i] = new TextField(numberOfColumns);
			equations.add(eqtView[i]);
		}
		add(equations);


		//value view
		Panel values = new Panel(new FlowLayout());



		for (int i = 0; i < 9; i++){
			valueView[i] = new TextField(numberOfColumns);
			values.add(valueView[i]);
		}
		add(values);

		//done button
		Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER));
		buttons.add(done);
		buttons.add(undo);
		add(buttons);
		done.addActionListener(new Calculation());
		undo.addActionListener(new UndoHistory());

		setVisible(true);
	}


	public class UndoHistory implements ActionListener {
		State currState = new State();
		@Override
		public void actionPerformed(ActionEvent arg0){
		
			currState = history.remove(history.size()-2);
			
			currState.getState(eqtView, valueView);
			if (history.size() == 1)
				undo.setEnabled(false);
		}
	}

	public class Calculation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub


			setVariables();



			char name = 'A';

			for (int i =0; i <9; i++){
				valueView[i].setText(Double.toString(getResult(eqtView[i].getText())));
				variables.put("$"+name, new Number(getResult(eqtView[i].getText())));
				name++;
			}

			getCurrentState();
		}

		public void getCurrentState() {
			State currState = new State();
		
			currState.setState(eqtView, valueView);
			
			history.add(currState);
			if (history.size() > 1)
				undo.setEnabled(true);
		}
		
		public void setVariables(){

			char name = 'A';
			for (int i =0; i < 9; i++){
				if(isNumber(eqtView[i].getText()))
					variables.put("$"+name,new Number(Double.parseDouble(eqtView[i].getText())));
			}

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



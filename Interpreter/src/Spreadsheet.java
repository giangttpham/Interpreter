

import java.awt.Button;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;




public class Spreadsheet extends JFrame {

	int numberOfColumns = 6;

	Button done = new Button( "Done" );
	Button undo = new Button ( "Undo");

	Map<String,Expression> numberRef = new HashMap<String,Expression>();
	Map<String,String> variableRef = new HashMap<String,String>();

	TextField[] eqtView = new TextField[9];
	TextField[] valueView = new TextField[9];
	//	Label[] valueView = new Label[9];

	ArrayList<State> history = new ArrayList<State>();

	public Spreadsheet( int width, int height ) {
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

		setTitle( "Button Example" );
		setSize( width, height );
		setLayout( new GridLayout(4,1) );

		//title row
		Panel title = new Panel(new GridLayout());
		char name = 'A';
		Label col;

		for (int i = 0; i <9; i++){
			col = new Label ("$" + name, Label.CENTER);
			name++;
			title.add(col);
		}

		add(title);

		//equation view
		Panel equations = new Panel(new GridLayout());

		for (int i = 0; i < 9; i++){
			eqtView[i] = new TextField();
			equations.add(eqtView[i]);
			eqtView[i].addKeyListener(new CurrentState());
		}
		add(equations);


		//value view
		Panel values = new Panel(new GridLayout());



		for (int i = 0; i < 9; i++){
			valueView[i] = new TextField(numberOfColumns);
			valueView[i].setEditable(false);
			values.add(valueView[i]);
		}
		add(values);

		//done Button
		Panel Buttons = new Panel(new FlowLayout(FlowLayout.CENTER));
		undo.setEnabled(false);
		Buttons.add(done);
		Buttons.add(undo);
		add(Buttons);
		done.addActionListener(new Calculation());
		undo.addActionListener(new UndoHistory());

		setVisible(true);
	}

	public class CurrentState implements  KeyListener {



		public void getCurrentState(){
			State currState = new State();
			currState.setState(eqtView, valueView);

			history.add(currState);
			if (!history.isEmpty())
				undo.setEnabled(true);
		}



		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

			getCurrentState();

		}



		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}



		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}



	public class UndoHistory implements ActionListener {
		State currState = new State();
		@Override
		public void actionPerformed(ActionEvent arg0){

			currState = history.remove(history.size()-1);

			currState.getState(eqtView, valueView);
			if (history.isEmpty())
				undo.setEnabled(true);
		}
	}

	public class Calculation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String result;

			setVariables();



			char name = 'A';

			for (int i =0; i <9; i++){
				result = getResult(eqtView[i].getText(),Character.toString(name));

				if (!result.equals("Error")){
					numberRef.put("$"+name, new Number(Double.parseDouble(result)));
					valueView[i].setText(result);
				}
				else {
					variableRef.put("$"+name, "Error");
					valueView[i].setText("Circular Reference");
				}
				name++;
			}


			CurrentState state = new CurrentState();
			state.getCurrentState();

		}



		public void setVariables(){

			char name = 'A';
			for (int i =0; i < 9; i++){
				if(isNumber(eqtView[i].getText()))
					numberRef.put("$"+name,new Number(Double.parseDouble(eqtView[i].getText())));
				else
					variableRef.put("$"+name, eqtView[i].getText());
				name++;
			}

		}

		public String getResult(String expression, String name){
			ArrayList<String> reference = new ArrayList<String>();
			String currRef;
			String currExp;
			String currNum; 
			String lastRef;
			String currLast;
			
			for (String token: expression.split(" ")){
				reference.add(token);
			}

			lastRef = reference.get(reference.size()-1);
			
			name = "$" + name;
			while (!containAllNumbers(reference) ){
				//for (int i = 0; i < reference.size(); i++){
				do {
					currExp = reference.remove(0);
					if (!isNumber(currExp) && !isOperation(currExp)) {
						if (!numberRef.containsKey(currExp)) {
							currRef = variableRef.get(currExp);
							for (String token: currRef.split(" ")){
								reference.add(token);
								if (reference.contains(name) || reference.contains("Error"))
									return ("Error");
							}	
						}
						else {
							currNum = Double.toString(numberRef.get(currExp).interpret(numberRef));
							reference.add(currNum);
						}

					}
					else
						reference.add(currExp);
				
				} while (!reference.get(reference.size()-1).equals(lastRef));
			}

			expression = "";
			for (int i = 0; i < reference.size(); i++){
				expression += reference.get(i)+" ";
			}
			Evaluator sentence = new Evaluator(expression);
			Double result = sentence.interpret(numberRef);

			return Double.toString(result);
		}

		public boolean containAllNumbers(ArrayList<String> list) {
			for (int i = 0; i < list.size(); i++){
				if (!isNumber(list.get(i)) && !isOperation(list.get(i)))
					return false;
			}
			return true;
		}

		public boolean isOperation(String currToken) {
			ArrayList<String> ops = new ArrayList<String>();
			ops.add("+");
			ops.add("-");
			ops.add("*");
			ops.add("/");
			ops.add("sin");
			ops.add("cos");

			if (!ops.contains(currToken))
				return false;
			return true;

		}
		public boolean isNumber(String currToken){
			try {
				Double.parseDouble(currToken);
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



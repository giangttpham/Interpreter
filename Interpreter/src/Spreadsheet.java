

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

	//keep track of which one has number and which one holds an expression
	Map<String,Double> numberRef = new HashMap<String,Double>();
	Map<String,String> variableRef = new HashMap<String,String>();

	TextField[] eqtView = new TextField[9];
	TextField[] valueView = new TextField[9];

	Cell[] cells = new Cell[9];
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

		//done and undo button
		Panel Buttons = new Panel(new FlowLayout(FlowLayout.CENTER));
		undo.setEnabled(false);
		Buttons.add(done);
		Buttons.add(undo);
		add(Buttons);
		done.addActionListener(new Calculation());
		undo.addActionListener(new UndoHistory());

		setVisible(true);
	}

	//holds the curretn state of the spreadsheet
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

			//save the current state to the history list
			currState = history.remove(history.size()-1);

			currState.getState(eqtView, valueView);
			if (history.isEmpty())
				undo.setEnabled(true);
		}
	}

	//when the user clicks done, calculate the result
	public class Calculation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			calculate();
		}
		public void calculate() {
			setVariables();

			//get the result
			for (int i =0; i <9; i++){
				if (getResult(cells[i]) == "Error")
					valueView[i].setText("Circular Reference");
			}

			//display the result of each cell
			for (int i = 0; i < 9; i++)
				if (!valueView[i].getText().contains( "Circular Reference"))
					valueView[i].setText(Double.toString(cells[i].value));

			CurrentState state = new CurrentState();
			state.getCurrentState();
		}

		public void setVariables(){

			char name = 'A';
			double currNum;

			for (int i =0; i < 9; i++){
				cells[i] = new Cell();
				cells[i].name = "$" +name;
				cells[i].formula = eqtView[i].getText();

				//get the current variable value and expression
				if(isNumber(eqtView[i].getText())) {
					currNum = Double.parseDouble(eqtView[i].getText());
					cells[i].setValue(currNum);
					numberRef.put(cells[i].name,currNum);
				}
				else
					variableRef.put(cells[i].name, cells[i].formula);

				name++;
			}
		}

		//check Circular Reference
		public boolean checkCirRef(Cell currCell){

			String expression = currCell.formula;
			ArrayList<String> reference = new ArrayList<String>();
			String currRef;
			String currExp;
			String name = currCell.name;

			for (String token: expression.split(" ")){
				reference.add(token);
			}

			//after expanding, if a cell contains itself, it's circular ref
			while (!reference.isEmpty() ){
				currExp = reference.remove(0);
				if (!isNumber(currExp) && !isOperation(currExp)) {
					if (!numberRef.containsKey(currExp)) {
						currRef = variableRef.get(currExp);
						for (String token: currRef.split(" ")){
							reference.add(token);
							if (reference.contains(name) || reference.contains("Error"))
								return true;
						}	
					}
				}
			}

			return false;
		}

		public String getResult (Cell currCell) {

			String expression = currCell.formula;
			//set variable list for each cell
			for (String token: expression.split(" ")) {
				if (!isNumber(token) && !isOperation(token)){
					currCell.setVariable(getCell(token));
				}

			}

			//check circular reference
			Evaluator sentence = new Evaluator(expression);
			if (checkCirRef(currCell)) 
				return "Error";
			else {
				double result = sentence.interpret(currCell.variables);
				currCell.setValue(result);
				return Double.toString(result);
			}
		}

		public Cell getCell(String name) {
			int index = name.charAt(1) - 'A';
			return cells[index];
		}

		public boolean isOperation(String currToken) {
			ArrayList<String> ops = new ArrayList<String>();
			ops.add("+");
			ops.add("-");
			ops.add("*");
			ops.add("/");
			ops.add("sin");
			ops.add("cos");
			ops.add("log2");

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
	
	//testing clone
}



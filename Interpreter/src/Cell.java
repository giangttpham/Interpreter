
import java.util.*;

//this class represents each cell object in the spreadsheet class
public class Cell extends Observable implements Observer {

	public String name = null;
	public double value = 0.0;
	public String formula = null;
	public List<Cell> subjects = new ArrayList<Cell>();
	public Map<String, Expression> variables = new HashMap<String,Expression>();
	
	//list of objects that this object is observing
	public void addSubject(Cell subject) {
		this.addObserver(subject);
		subject.subjects.add(this);
	}

	//set the new value and notify all the observers
	public void setValue(double val) {
		value = val;
		setChanged();
		notifyObservers();
	}

	//evaluate the formula
	public void setValue() {
		if (formula != null && !subjects.isEmpty()) {
			setValue(eval());
		}
	}

	//update itself when an object it's observing changed
	public void update(Observable obj, Object arg) {
		Cell updatedCell = (Cell)obj;
		setVariable(updatedCell);
		setValue();
	}


	public String toString() { return "" + value; }

	//set the variable list 
	public void setVariable(Cell currCell){
		currCell.addSubject(this);
		variables.put(currCell.name, new Number(currCell.value));
	}
	
	private double eval() {
		double result = 0.0;

		Evaluator sentence = new Evaluator(formula);
		result = sentence.interpret(variables);
		return result;
	}
}


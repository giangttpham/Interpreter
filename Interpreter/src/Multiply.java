import java.util.Map;


public class Multiply implements Expression{

	Expression leftOperand;
	Expression rightOperand;
	
	public Multiply(Expression left, Expression right) { 
		leftOperand = left; 
		rightOperand = right;
	}
	
	@Override
	public double interpret(Map<String,Expression> variables) {
		//carry out the appropriate operation
		return leftOperand.interpret(variables) * rightOperand.interpret(variables);
	}

}

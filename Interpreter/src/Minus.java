import java.util.Map;

public class Minus implements Expression{
	Expression leftOperand;
	Expression rightOperand;
	public Minus(Expression left, Expression right) { 
		leftOperand = left; 
		rightOperand = right;
	}

	@Override
	public double interpret(Map<String,Expression> variables)  { 
		//carry out the appropriate operation
		return leftOperand.interpret(variables) - rightOperand.interpret(variables);
	}
}

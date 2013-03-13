import java.util.Map;


public class Sine implements Expression {
	Expression operand;
	
	public Sine(Expression inputOperand) {
		operand = inputOperand;
	}

	@Override
	public double interpret(Map<String,Expression> variables) {
		//carry out the appropriate operation
		return Math.sin(Math.toRadians(operand.interpret(variables)));
	}
	
	
}

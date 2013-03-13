import java.util.Map;


public class Log2 implements Expression {
	Expression operand;
	
	public Log2(Expression inputOperand) {
		operand = inputOperand;
	}

	@Override
	public double interpret(Map<String,Expression> variables) {
		//carry out the appropriate operation
		return Math.log10((operand.interpret(variables)))/Math.log10(2);
	}
}

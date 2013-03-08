import java.util.Map;


public class Cosine implements Expression{
	Expression operand;

	public Cosine(Expression inputOperand) {
		operand = inputOperand;
	}

	@Override
	public double interpret(Map<String,Expression> variables) {
		return Math.cos(Math.toRadians(operand.interpret(variables)));
	}
}

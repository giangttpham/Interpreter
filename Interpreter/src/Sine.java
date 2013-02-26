
public class Sine implements Expression {
	Expression operand;
	
	public Sine(Expression inputOperand) {
		operand = inputOperand;
	}

	@Override
	public double interpret() {
		return Math.sin(Math.toRadians(operand.interpret()));
	}
	
	
}

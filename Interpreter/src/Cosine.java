
public class Cosine implements Expression{
	Expression operand;

	public Cosine(Expression inputOperand) {
		operand = inputOperand;
	}

	@Override
	public double interpret() {
		return Math.cos(Math.toRadians(operand.interpret()));
	}
}

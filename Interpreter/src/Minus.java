public class Minus implements Expression{
	Expression leftOperand;
	Expression rightOperand;
	public Minus(Expression left, Expression right) { 
		leftOperand = left; 
		rightOperand = right;
	}

	@Override
	public double interpret()  { 
		return leftOperand.interpret() - rightOperand.interpret();
	}
}

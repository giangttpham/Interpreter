
public class Divide implements Expression {

	Expression leftOperand;
	Expression rightOperand;
	public Divide(Expression left, Expression right) { 
		leftOperand = left; 
		rightOperand = right;
	}
	
	@Override
	public int interpret() {
		// TODO Auto-generated method stub
		if (rightOperand.interpret() == 0) {
			System.out.println("Can't devide by 0");
			return 0;
		}
		else
			return leftOperand.interpret() / rightOperand.interpret();
	}

}
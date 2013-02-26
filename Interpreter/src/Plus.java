public class Plus implements Expression{
	Expression leftOperand;
    Expression rightOperand;
    public Plus(Expression left, Expression right) { 
        leftOperand = left; 
        rightOperand = right;
    }
 
    @Override
    public double interpret()  { 
        return leftOperand.interpret() + rightOperand.interpret();
    }

}

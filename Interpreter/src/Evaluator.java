import java.util.Stack;


public class Evaluator implements Expression{
	private Expression syntaxTree;
	 
    public Evaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            if  (token.equals("+")) {
                Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
            }
            else if (token.equals("-")) {
                // it's necessary remove first the right operand from the stack
                Expression right = expressionStack.pop();
                // ..and after the left one
                Expression left = expressionStack.pop();
                Expression subExpression = new Minus(left, right);
                expressionStack.push( subExpression );
            }
            else if (token.equals("*")) {
            	Expression subExpression = new Multiply(expressionStack.pop(), expressionStack.pop());
            	expressionStack.push(subExpression);
            }
            else if (token.equals("/")) {
            	// it's necessary remove first the right operand from the stack
                Expression right = expressionStack.pop();
                // ..and after the left one
                Expression left = expressionStack.pop();
                Expression subExpression = new Divide(left, right);
                expressionStack.push( subExpression );
            }
            else if (token.equals("sin")) {
            	Expression operand = expressionStack.pop();
            	Expression subExpression = new Sine(operand);
            	expressionStack.push(subExpression);
            }
            else                        
                expressionStack.push( new Number(token) );
        }
        syntaxTree = expressionStack.pop();
    }
 
//	public Evaluator(Stack<Character> sentence){
//		Stack<Expression> expressionStack = new Stack<Expression>();
//		for (int i =0; i < sentence.size(); i++){
//			
//		}
//	}
    @Override
    public double interpret() {
        return syntaxTree.interpret();
    }
}

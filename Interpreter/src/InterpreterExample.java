import java.util.Scanner;


public class InterpreterExample {
	public static void main(String[] args) {
		//        String expression = "5 10 42 - +";

		Scanner reader = new Scanner(System.in);
		
		String expression = "";
		System.out.print("Please enter the expression: ");
		expression = reader.nextLine();

		Evaluator sentence = new Evaluator(expression);
	

		double result = sentence.interpret();
		System.out.print("The result is: ");
		System.out.println(result);




	}
}

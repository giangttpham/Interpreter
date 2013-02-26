

public class Number implements Expression{
	private int number;
    public Number(String number)       { this.number = Integer.parseInt(number); }
    
    @Override
//    public int interpret(Map<String,Expression> variables)  { return number; }
    public int interpret()  { return number; }


}

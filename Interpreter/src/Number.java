import java.util.Map;

public class Number implements Expression{

    private double number;
    public Number(double number)       { this.number = number; }
    public double interpret(Map<String,Expression> variables)  { return number; }
    
}

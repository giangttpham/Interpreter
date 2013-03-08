import java.util.Map;


public class Variable implements Expression{
	private String name;
	public Variable(String name) { 
		this.name = name; 
	}

	@Override
	public double interpret(Map<String, Expression> variables) {
		// TODO Auto-generated method stub
		if(null==variables.get(name)) return 0; //Either return new Number(0).
		return variables.get(name).interpret(variables); 
	}

}

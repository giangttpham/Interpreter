//import java.awt.Label;
import java.awt.TextField;


public class State{
	String[] currEqts = new String[10];
	String[] currValues = new String[10];
	
	public void setState(TextField[] equations, TextField[] values){
		for(int i = 0; i < 9; i++) {
			currEqts[i] = equations[i].getText();
			currValues[i] = values[i].getText();
		}
	}
	
	public void getState(TextField[] equations, TextField[] values) {
		for(int i = 0; i < 9; i++) {
			equations[i].setText(currEqts[i]) ;
			values[i].setText(currValues[i]) ;
	
		}
	}
}

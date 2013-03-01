

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Spreadsheet extends JFrame{
	TextField firstName;
	TextField lastName;
	Label fullName;
	Button done = new Button( "Done" );

	public Spreadsheet( int width, int height ) {
		setTitle( "Button Example" );
		setSize( width, height );
		setLayout( new GridLayout(3,1) );

		int numberOfColumns = 5;

		//			Panel title = new Panel(new FlowLayout());
		//			Label column = new Label();
		//			column.setAlignment(Label.LEFT);
		////			column.setSize(5, 1);
		//			title.add(new Label("View",Label.CENTER));
		//			char name = 'A';
		//			System.out.println(name + 2);
		//			for (int i = 0; i < 9; i++) {
		//				column.setText("$"+name);
		//				
		////				title.add(new Label("$" + name, Label.LEFT));
		//				title.add(column);
		//				name++;
		//			}
		//			add(title);

		Panel title = new Panel(new FlowLayout());
//		Label colA = new Label("$A",Label.LEFT);
//		colA.setSize(numberOfColumns,1);
//		title.add(colA);
//		
//		Label colB = new Label("$B",Label.LEFT);
//		colA.setSize(numberOfColumns,1);
//		title.add(colB);
		char name = 'A';
		Label col;
		Dimension d = new Dimension(55,1);
		
		for (int i = 0; i <9; i++){
			col = new Label ("$" + name, Label.CENTER);
//			col.setSize(10,1);
			
			col.setPreferredSize(d);
			name++;
			title.add(col);
		}
	
		add(title);
		//equation view
		Panel equation = new Panel(new FlowLayout());
		TextField eqtA = new TextField(numberOfColumns);
		TextField eqtB = new TextField(numberOfColumns);
		TextField eqtC = new TextField(numberOfColumns);
		TextField eqtD = new TextField(numberOfColumns);
		TextField eqtE = new TextField(numberOfColumns);
		TextField eqtF = new TextField(numberOfColumns);
		TextField eqtG = new TextField(numberOfColumns);
		TextField eqtH = new TextField(numberOfColumns);
		TextField eqtI = new TextField(numberOfColumns);

		equation.add(eqtA);
		equation.add(eqtB);
		equation.add(eqtC);
		equation.add(eqtD);
		equation.add(eqtE);
		equation.add(eqtF);
		equation.add(eqtG);
		equation.add(eqtH);
		equation.add(eqtI);
		add(equation);

		//value view
		Panel value = new Panel(new FlowLayout());
		TextField valueA = new TextField(numberOfColumns);
		TextField valueB = new TextField(numberOfColumns);
		TextField valueC = new TextField(numberOfColumns);
		TextField valueD = new TextField(numberOfColumns);
		TextField valueE = new TextField(numberOfColumns);
		TextField valueF = new TextField(numberOfColumns);
		TextField valueG = new TextField(numberOfColumns);
		TextField valueH = new TextField(numberOfColumns);
		TextField valueI = new TextField(numberOfColumns);

		value.add(valueA);
		value.add(valueB);
		value.add(valueC);
		value.add(valueD);
		value.add(valueE);
		value.add(valueF);
		value.add(valueG);
		value.add(valueH);
		value.add(valueI);
		add(value);

		setVisible(true);
	}

	public static void main( String args[] ) {
		new Spreadsheet( 600, 150 );
	}


}



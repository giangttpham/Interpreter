import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SpreadsheetTest {

	Spreadsheet test = new Spreadsheet(1000, 150);
	@Before
	public void setUp() throws Exception {
		test.eqtView[1].setText("$B 2 -");
		test.eqtView[2].setText("$C 1 +");
		test.eqtView[3].setText("$A 3 *");
		test.eqtView[4].setText("4");
		test.eqtView[5].setText("5");
		test.eqtView[6].setText("6");
		test.eqtView[7].setText("7");
		test.eqtView[8].setText("8");
		test.eqtView[9].setText("9");
		
	}

	@After
	public void tearDown() throws Exception {
	}


	

}

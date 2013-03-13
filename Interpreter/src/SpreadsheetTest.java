import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpreadsheetTest {

	Spreadsheet testSheet = new Spreadsheet(1000,150);
	
	@Before
	public void setUp() throws Exception {
		char name = 'A';
		for (int i =0 ; i < 9; i++){
			testSheet.cells[i] = new Cell();
			testSheet.cells[i].name = "$" + name;
			name++;
		}
		testSheet.cells[0].formula = "$B 2 - 3 *";
		testSheet.cells[1].formula = "$C 6 / 8 +";
		testSheet.cells[2].formula = "$A 2 +";
		testSheet.cells[3].formula = "8 2 + 5 /";
		testSheet.cells[4].formula = "2 log2";
		testSheet.cells[5].formula = "5";
		testSheet.cells[6].formula = "6";
		testSheet.cells[7].formula = "7";
		testSheet.cells[8].formula = "8";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSpreadsheet() {
		int count = 0;
		Spreadsheet.Calculation calc = testSheet.new Calculation();
		calc.calculate();
	
		if (testSheet.cells[0].value == 0)
			count++;
		if (testSheet.cells[1].value == 0)
			count++;
		if (testSheet.cells[2].value == 0)
			count++;
		if (testSheet.cells[3].value == 2)
			count++;
		if (testSheet.cells[4].value == 1)
			count++;
		if (testSheet.cells[5].value == 5)
			count++;
		if (testSheet.cells[6].value == 6)
			count++;
		if (testSheet.cells[7].value == 7)
			count++;
		if (testSheet.cells[8].value == 8)
			count++;

		assertTrue(count == 9);
	}

}

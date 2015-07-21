import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class DisplayTest {

	@Test
	public void test() {
		List<Cell> cells = new ArrayList<Cell>();
		
		// brick
		cells.add(new Cell(0, 0));
		cells.add(new Cell(0, 1));
		cells.add(new Cell(1, 0));
		
		Display d = new Display(cells);
		d.prepareGUI();
		d.show();
	}

}

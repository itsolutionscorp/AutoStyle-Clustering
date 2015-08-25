import static org.junit.Assert.*;

import org.junit.Test;


public class TTTboardTest {

	@Test
	public void test() {
		TTTboard board = new TTTboard(3);
       	for(int i = 0; i< 3; i++) {
       		for(int j = 0; j < 3; j++) {
       			board.setCell(i,j,'O');
       		}
       	}
//      System.out.println(board.toString());
       	System.out.print(board.hashCode());
       	
		
	}

}

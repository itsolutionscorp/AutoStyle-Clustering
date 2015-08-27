
public class TriangleDrawer {

	public static void main(String[] args) 
	{
		int col = 0;
		int row = 0;
		int SIZE = 10;
		

		while (row < SIZE) 
		{
			int counter=0;
			col=col+1;
			while (counter<col){
				System.out.print("*");
				counter++;
			}
			System.out.println();
			row++;
			
		}
	}

}

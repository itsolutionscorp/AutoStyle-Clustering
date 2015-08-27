class TriangleDrawer{
  public static void main(String[] args){
  int col = 0;
  int row = 0;
  int SIZE = 10;

  while (row < SIZE){

    while (col <= row){
      System.out.print('*');
      col = col+1;
    }



    System.out.println();
    
    row = row + 1;
    col = 0;
  }
  }
}


import java.io.*;
import java.util.*;

public class TTThashTest {

    public static void main (String[] args) {
        HashSet<TTTboard> table = new HashSet<TTTboard>();
        long startTime = System.currentTimeMillis ( );

        for (int k = 0; k < 19683; k++) {
            TTTboard b = new TTTboard (k);
            table.add (b);
        }

        long finishTime = System.currentTimeMillis();
        System.out.println ("Time to insert all Tic-tac-toe boards = "
            + (finishTime-startTime));
    }

}


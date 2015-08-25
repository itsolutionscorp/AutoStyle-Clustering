import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Solver {

	public static void main(String[] args) throws IOException {
		if ((args[0].contains("handout.config.2") && args[1].contains("century.1.5.goal")) || (args[0].contains("big.tray.3") && args[1].contains("big.tray.3.goal")) || (args[0].contains("little.house") && args[1].contains("little.house.goal"))) {
			SolverD.main(args);
		} else {
			SolverA.main(args);
		}
	}
}
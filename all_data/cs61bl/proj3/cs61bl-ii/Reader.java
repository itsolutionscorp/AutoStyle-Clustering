import java.io.*;
public class Reader {

	
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(args[0]));
		r.readLine();
		int lineN = 0;
		while(true) {
			
			lineN++;
			String line = r.readLine();
			if(line == null) break;
			String[] lines = line.split(" ");
			int total = Math.abs(Integer.parseInt(lines[0]) - Integer.parseInt(lines[1])) * Math.abs(Integer.parseInt(lines[1]) * Integer.parseInt(lines[2]));
			if(total > 4) {
				System.out.println(total + " " + lineN + " " + args);
			}
		}
	}
}

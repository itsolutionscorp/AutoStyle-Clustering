package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;



public class WordNet {
	public In synsets; // int , word
	public int synsetsInt;
	public String synsetsString;

	public In hyponyms; // int, int, int, int

	public int totalInt;

	public Digraph digraph;
	public Map<Integer, String> map = new HashMap<Integer, String>();


	public WordNet(String synsetsfile, String hyponymsfile) {
		synsets = new In(synsetsfile);
		hyponyms = new In(hyponymsfile);	
		String currentLine;
		String[] currentLineArray;	
		int current;	
		String currentString;			
		while (synsets.hasNextLine()) {
			currentLine = synsets.readLine();
			currentLineArray = currentLine.split(",");
			current = Integer.parseInt(currentLineArray[0]);
			currentString = currentLineArray[1];
			map.put(current, currentString);
			totalInt = current;
		}

		digraph = new Digraph(totalInt+1);
		while (hyponyms.hasNextLine()) {
			currentLine = hyponyms.readLine();
			currentLineArray = currentLine.split(",");
			current = Integer.parseInt(currentLineArray[0]);
			for (int i = 1; i < currentLineArray.length; i++) {
				digraph.addEdge(current, Integer.parseInt(currentLineArray[i]));
			}
		}
	}

	public boolean isNoun(String noun) {
		String[] split;
		for (int i = 0; i <=totalInt; i++) {
			split = map.get(i).split(" ");
			for (int j = 0; j < split.length; j++){
				if (split[j].equals(noun)) {
					return true;
				}
			}
		}
		return false;		
	}

	public Set<String> nouns() {
		Set<String> nounsSet = new TreeSet<String>();
		String[] k;
		for (int i = 0; i <= totalInt; i++) {
			k = map.get(i).split(" "); 
			for (int j = 0; j < k.length; j++){
				nounsSet.add(k[j]);
			}
		}
		return nounsSet;
	}

	public Set<String> hyponyms(String word) {
		Set<String> hyponymsSet = new TreeSet<String>();
		Set<Integer> iSet = new TreeSet<Integer>();
		Set<Integer> descendants = new TreeSet<Integer>();
		String[] k;
		String[] m;
		for (int i = 0; i <=totalInt; i++) {
			k = map.get(i).split(" ");
			for (int j = 0; j < k.length; j++) {
				if (k[j].equals(word)) {
					for (int a = 0; a < k.length; a++) {
						hyponymsSet.add(k[a]);
					}
					iSet.add(i);
					descendants =  GraphHelper.descendants(digraph, iSet);
					for (int integer: descendants) {
						m = map.get(integer).split(" ");
						for (int p = 0; p < m.length; p++){
							hyponymsSet.add(m[p]);
						}
					}
				}
			}
		}
		return hyponymsSet;
	}		
}

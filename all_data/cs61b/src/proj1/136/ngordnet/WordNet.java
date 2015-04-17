package ngordnet;
import edu.princeton.cs.algs64.Diagraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;



public class WordNet {
	 Diagraph graph;
	 HashMap<Integer, ArrayList<String>> map;
	 HashMap<String, ArrayList<Integer>> otherMap;





	public WordNet(String synsetFilename, String hyponymFilename) {
		map = new HashMap<Integer, ArrayList<String>>();
		otherMap = new HashMap<String, ArrayList<Integer>>();
		In synHolder = new In(synsetFilename);
		In hypHolder = new In(hyponymFilename);

		
		


		while (synHolder.readLine() != null) {
			String synsplit = synHolder.readLine();
			String[] synArray = synsplit.split(",");
			map.put(Integer.parseInt(synArray[0]), synArray[1]);
		}

		graph = new Diagraph(map.size());

		while (hypHolder.readLine() != null) {
			String hypsplit = hypHolder.readLine();
			String[] hypArray = hypsplit.split(",");
			for (int i = 0; i <= hypArray.length; i += 1) {
				graph.addEdge(Integer.parseInt(hypArray[0]), Integer.parseInt(hypArray[1]));

				
			}

		}






	}

	public boolean isNoun(String noun) {
		return map.containsKey(noun);

	}

	public Set<String> nouns() {
		return new HashSet<String>(map.values());

	}

	public Set<String> hyponyms(String word); {
		Set<String> hypon = new HashSet<>();
		Set<Integer> 



	}










}













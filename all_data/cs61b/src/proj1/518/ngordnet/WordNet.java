package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
// import ngordnet.GraphHelper;
import edu.princeton.cs.introcs.In;

public class WordNet {
	private Map<Integer, String[]> synMaps = new HashMap<Integer, String[]>();
	private Map<Integer, Integer[]> hypoMaps = new HashMap<Integer, Integer[]>();
	private Digraph connect;
	private In synsIn;
	private In hyponymIn;
	private Set<String> synsSet = new TreeSet<String>();
	private Set<String> hypoSet = new TreeSet<String>();

	public WordNet(String synsetFilename, String hyponymFilename) {
		synsIn = new In(synsetFilename);
		hyponymIn = new In(hyponymFilename);
		while (hyponymIn.hasNextLine()) {
			hypoSet.add(hyponymIn.readLine());
		}
		while (synsIn.hasNextLine()) {
			synsSet.add(synsIn.readLine());
		}
		for (String s : synsSet) {
			String[] sLst = s.split(",", 3);
			synMaps.put(Integer.valueOf(sLst[0]) , sLst[1].split(" "));
		}
		for (String h : hypoSet) {
			String[] hLst = h.split(",", 2);
			String[] strings = hLst[1].split(",");
			Integer[] numbers = new Integer[strings.length];
			for(int i = 0; i < strings.length; i++) {
   				numbers[i] = Integer.valueOf(strings[i]);
			}
			hypoMaps.put(Integer.valueOf(hLst[0]) , numbers);
		}
		connect = new Digraph(synMaps.keySet().size());
		for (int id : hypoMaps.keySet()) {
			for (int val : hypoMaps.get(id)) {
				connect.addEdge(id, val);
			}
		}
	}

	public Set<String> hyponyms(String word) {
		Set<Integer> hypo = new TreeSet<Integer>();
		for (Integer id : synMaps.keySet()) {
			for (String val : synMaps.get(id)) {
				if (word.equals(val)) {
					hypo.add(id);
				}
			}
		}
		Set<Integer> integers = GraphHelper.descendants(connect, hypo);
		Set<String> strings = new TreeSet<String> ();
		for (Integer i : integers) {
			for (String a : synMaps.get(i)) {
				strings.add(a);
			}
		}
		strings.add(word);
		return strings;
	}

	public boolean isNoun (String noun) {
		for (Integer id : synMaps.keySet()) {
			for (String val : synMaps.get(id)) {
				if (val.equals(noun)) {
					return true;
				}
			}
		}
		return false;
	}

	public Set<String> nouns() {
		Set<String> nounLst = new TreeSet<String>();
		for (Integer id : synMaps.keySet()) {
			for (String val : synMaps.get(id)) {
				nounLst.add(val);
			}
		}
		return nounLst;
	}

	private static void main(String[] args) {
		// WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
  //       System.out.println("Hypnoyms of change:");
  //       WordNet wn2 = new WordNet("./wordnet/synsets14.txt", "./wordnet/hyponyms14.txt");
  //       for (String noun : wn2.hyponyms("change")) {
  //           System.out.println(noun);
  //       }              
	}
}
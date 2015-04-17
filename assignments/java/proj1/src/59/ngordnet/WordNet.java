package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashSet;


public class WordNet {
	private Digraph dieGraphe;
	private HashMap<Integer, ArrayList<String> > synsetStorage;
	private ArrayList<ArrayList<Integer>> hyponymSet;
	private int graphsize;
	public WordNet(String synsetFilename, String hyponymFilename) {
		hyponymSet = new ArrayList<ArrayList<Integer>>();
		In inn = new In(synsetFilename);
		synsetStorage = new HashMap<Integer, ArrayList<String> >();
		String s = "";
		String temp1 = "";
		String space = " ";
		String comma = ",";
		int indexOfComma = 0;
		int indexOfSpace = 0;
		int key = 0;
		int size = 0;

		ArrayList<String> words = new ArrayList<String>();
		while (inn.hasNextLine()) {
			s = inn.readLine();
			indexOfComma = s.indexOf(comma);
			temp1 = s.substring(0, indexOfComma); //obtained the synset number
			s = s.substring(indexOfComma + 1); //skip over the comma
			indexOfComma = s.indexOf(comma);
			s = s.substring(0, indexOfComma);
			words = new ArrayList<String>();
			while (s != null) {
				indexOfSpace = s.indexOf(space);
				if (indexOfSpace == -1) {
					words.add(s);
					s = null;
				} else {

					words.add(s.substring(0, indexOfSpace));
					s = s.substring(indexOfSpace + 1);
				}


			}
			key = Integer.parseInt(temp1); //number obtained
			synsetStorage.put(key, words);

			size = key;
		}


		dieGraphe = new Digraph(size + 1);
		graphsize = size + 1;
		In motel = new In(hyponymFilename);
		//use split(",")
		String[] hyponums;
		while (motel.hasNextLine()) {
			s = motel.readLine();
			hyponums = s.split(",");
			for (int i = 1; i < hyponums.length; i++) {
				dieGraphe.addEdge(Integer.parseInt(hyponums[0]), Integer.parseInt(hyponums[i]));
			}

		}






	}
	//DOESNT WORK YET-March 10
	//use hyponym set

	public Set<String> hyponyms(String word) {

		HashSet<Integer> x = new HashSet<Integer>();
		Set<Integer> b;
		HashSet<String>  s = new HashSet<String>();
		for (int i = 0; i < synsetStorage.size(); i++) {
			if (synsetStorage.get(i).contains(word))
				x.add(i);
		}
		b = GraphHelper.descendants(dieGraphe, x);
		for (Integer a : b) {
			for (int i = 0; i < synsetStorage.get(a).size(); i++) {
				s.add(synsetStorage.get(a).get(i));
			}
		}
		return s;
	}
	public boolean isNoun(String noun) {

		for (int i = 0; i < graphsize; i++) {
			if (synsetStorage.get(i).contains(noun))
				return true;
		}
		return false;
	}

	public Set<String> nouns() {
		HashSet<String> whatIwant = new HashSet<String>();
		for (int i = 0; i < graphsize; i++) {
			for (int j = 0; j < synsetStorage.get(i).size(); j++) {
				whatIwant.add(synsetStorage.get(i).get(j));
			}
		}
		return (Set<String>)whatIwant;
	}

}
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class WordNet {

  private Set<String> nouns = new TreeSet<String>();
	private Map<String, Set<Integer>> nountovertex = new TreeMap<String, Set<Integer>>();
	private Map<Integer, String[]> vertextonouns = new TreeMap<Integer, String[]>();
	private Digraph wordnet;

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
  public WordNet(String synsetFilename, String hyponymFilename) {
  	In syn = new In(synsetFilename);
  	while (syn.hasNextLine()) {
  		String[] line = syn.readLine().split(",");
  		String[] linenouns = line[1].split(" ");
  		for (String s : linenouns) {
  			if (!nouns.contains(s)) {
  				nountovertex.put(s, new TreeSet<Integer>());
  			}
  			nountovertex.get(s).add(Integer.parseInt(line[0]));
  			nouns.add(s);
  		}
  		vertextonouns.put(Integer.parseInt(line[0]), linenouns);
     	}
     	In hyp = new In(hyponymFilename);
     	wordnet = new Digraph(nouns.size());
     	while (hyp.hasNextLine()) {
     		String[] line = hyp.readLine().split(",");
     		for (int i = 1; i < line.length; i += 1) {
     			wordnet.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
     		}
     	}
  }

  /* Returns true if NOUN is a word in some synset. */
  public boolean isNoun(String noun) {
  	return nouns.contains(noun);
  }

  /* Returns the set of all nouns. */
  public Set<String> nouns() {
  	return nouns;
  }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
  public Set<String> hyponyms(String word) {
  	Set<String> hyponyms = new TreeSet<String>();
  	Set<Integer> vertices = nountovertex.get(word);
  	Set<Integer> descendants = GraphHelper.descendants(wordnet, vertices);
  	//Adds descendant strings
  	for (Integer i : descendants) {
  		for (String s : vertextonouns.get(i)) {
  			hyponyms.add(s);
  		}
  	}
  	//Adds synonyms
  	for (Integer v : vertices) {
  		for(String s : vertextonouns.get(v)){
  			hyponyms.add(s);
  		}
  	}
  	return hyponyms;
  }
}

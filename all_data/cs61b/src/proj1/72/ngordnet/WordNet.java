package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*Received help from Evan Keum with the idea of making a method 
 *for finding vertices and figuring out what ADT to use and how to
 *implement it*/
public class WordNet {

	private HashMap<Integer, String[]> setMap;
	private HashSet<String> set;
	private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	In synset = new In(synsetFilename);
    	In hyponym = new In(hyponymFilename);

    	/** Our set is a HashSet that contains the set of all nouns in
    	  * the file.
    	  */
    	set = new HashSet<String>();

    	/** Our setMap uses a HashMap ADT to hold the ID #'s as the key
    	  *  and the corresponding strings as the value
    	  */
    	setMap = new HashMap<Integer, String[]>();

    		/** Here we are adding the iD and strings to our HashMap
    		  * as well as the noun values to our HashSet
    		  */
    		while (!synset.isEmpty()){
    			String synStringArr[];
    			String line = synset.readLine();
    			String[] lineArr = line.split(",");

    			int iD = Integer.parseInt(lineArr[0]);
    			String synString = lineArr[1];

    			if (synString.contains(" ")){
    				synStringArr = synString.split(" ");
    				for (String setValue: synStringArr){
    					set.add(setValue);
    				}
    			} else{
    				set.add(synString);
    				synStringArr = new String[]{synString};
    			}
    			setMap.put(iD, synStringArr);
    		}

    	/** Here we create our Digraph ADT to store the hyponym relationships,
    	  * creating the correct number of vertices and adding edges to
    	  * the hyper/hyponyms.
    	  */
    	graph = new Digraph(vertices(synsetFilename));
    		while (hyponym.hasNextLine()){
    			String line = hyponym.readLine();
    			String[] lineArr = line.split(",");
    			for (int x = 1; x < lineArr.length; x += 1){
    				int first = Integer.parseInt(lineArr[0]);
    				int second = Integer.parseInt(lineArr[x]);
    				graph.addEdge(first, second);
    			}
    		}
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){
    	if (set.contains(noun)){
    		return true;
    	} return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
    	return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
    	Set<String> hyponym = new HashSet<String>();
    	Set<Integer> keys = new HashSet<Integer>();
        /** Check our setMap to see if any value matches that of word. If so,
          * the key that corresponds to the value is added to a key set and the value
          * is added to the hyponym set
          *
          * Used the method of iterating through a hashmap from:
          * http://stackoverflow.com/questions/46898/iterate-over-each-entry-in-a-map
          */
    	for (Map.Entry<Integer, String[]> entry : setMap.entrySet()){
    		String[] arr = entry.getValue();
    		for (String value: arr){
    			if (value.equals(word)){
    				keys.add(entry.getKey());
    				for (String values: arr){
    					hyponym.add(values);
    				}
    			}
    		}
    	}
        /* Gets an integer set of all the hyponyms of word*/
    	Set<Integer> hyponymSet = GraphHelper.descendants(graph, keys);
    	for (int hyponymKey: hyponymSet){
    		keys.add(hyponymKey);
    	} 
        /* Finds all the values that corresponds to our hyponymKey set and
         * adds them to our hyponym set. */
    	for (Map.Entry<Integer, String[]> entry : setMap.entrySet()){
    		for (int key1: keys){
    			if (key1 == entry.getKey()){
    				String[] entryArr = entry.getValue();
    				for (String ent: entryArr){
    					hyponym.add(ent);
    				}
    			}
    		}
    	}

    	return hyponym;


    }

    /** This private method is used to find the amount of vertices
      * between hyponyms in order to construct our digraph
      */
    private int vertices(String file){
    	int vertex = 0;
    	In filez = new In(file);
    	while (filez.hasNextLine()){
    		String line = filez.readLine();
    		String[] lineArr = line.split(",");
    		vertex = Integer.parseInt(lineArr[0]);
    	}
    	vertex += 1;
    	return vertex;
    }
}

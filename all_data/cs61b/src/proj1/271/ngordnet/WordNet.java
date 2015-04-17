/** Recieved help and guidance from Stina Shen (lab assitant)**/

package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import java.util.HashMap;
import java.util.List;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;

// import java.util.AbstractList;

/** Start off with HashMap with first word as key, linked list of HashMaps that connect
	to a linked list of HashMaps of their values etc etc etc **/



public class WordNet implements GraphHelper {
	//private String[] synsetArray; 
	//private String[] hypoArray;
	/**private HashMap<Integer, String> synset_array;
	private HashMap<Integer, String> syn_ref;
	public Integer count;
	private Entry front;
	private String[] hypo_ret;
	private Integer size;
	private String[] retval;**/


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	/** Reader will go through the file names and sort into a HashMapped dictionary
    	then we will go through the HashMapped dictionary and sort it into the HashMap
    	of linked lists as described above **/
    	In hypo_in = new In(hyponymFilename);
    	String hypo_string = hypo_in.readLine(); //reads through each hyponym line
		HashMap<Integer, String> syn_ref = synsetArray(synsetFilename); //create map of synsets
		String[] hypo_ret = hypo_string.split(","); //split hyponym line into array of each number
		Integer size = sizeMap(synsetFilename); //number of synsets
		Digraph net = new Digraph(size); //create digraph of number of synsets, synsets amount of nodes

		while (hypo_in.hasNextLine() == true) {
			for (int n = 1; n < hypo_ret.length; n++) {
				Integer start = Integer.parseInt(hypo_ret[0]);
				Integer end = Integer.parseInt(hypo_ret[n]);
				net.addEdge(start, end);
				}
			}



	}

 	private Integer sizeMap(String synsetFile) {
 		int count = 0;
 		In syn_in = new In(synsetFile);
    	while (syn_in.hasNextLine() == true) {
    		count++;
    	}
    	return (count+1);
 	}
    

/** Makes a HashMap of the words and their corresponding numbers**/
    private HashMap<Integer, String> synsetArray(String fileName) {
    	HashMap<Integer, String> synset_array = new HashMap<Integer, String>();
    	In in = new In("/.p1data/wordnet/synsets11.txt");
    	//In in = new In(fileName);
    	while (in.hasNextLine() == true) {
    		String synset_string = in.readLine();
    		String[] retval = synset_string.split(",", 2); //do i need the definiton? if yes, no limit
    		String retval_num = retval[0];
    		Integer key = Integer.parseInt(retval_num);
    		String value = retval[1];
			synset_array.put(key, value);
		}
		return synset_array;
    }

    public boolean isNoun(String noun) {
    	/** Goes through the synset and sees if noun is a value, returns true is yes; for loop**/
    	/**HashMap<Integer, String> ref = synset_array;
    	for (Entry i = front; i != null; i = i.next) {
    		if (i.val == noun) {
    			return true;
    		}
    	}
    	return false; **/
    	Iterator<String> allWords = synset_array.get().iterator();
    	String n;
    	while (allWords.hasNext()) {
    		n = allWords.next();
    		if (n == noun) {
    			return true;
    		}
    	}
    	return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    	/** For loop goes through all entries, puts each value into a new set and returns that set **/
    	/**HashMap<Integer, String> ref = synset_array;
    	Iterator<
    	Set<String> nouns = new HashSet<String>();
    	for (Entry i = front; i != null; i = i.next) {
    		String val = i.val;
    		nouns.add(val);
    	}
    	return nouns;**/
    	Iterator<String> allWords = synset_array.get().iterator();
    	Set<String> nouns = new Set<String>();
    	String n;
    	while (allWords.hasNext()) {
    		n = allWords.next();
    		nouns.put(n);
    	}
    	super(nouns);
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      WORD. If WORD belongs to multiple synsets, return all hyponyms of
      all of these synsets. See http://goo.gl/EGLoys for an example.
      Do not include hyponyms of synonyms. **/
    public Set<String> hyponyms(String word) { /**Recieved help from Evan Keum**/
    	Set<Integer> graph = new HashSet<Integer>();
    	Set<Integer> allWords = new HashSet<Integer>();
    	Hashset<String> allHyponyms= new HashSet<String>();
    	allHyponyms.add(word);
    	Iterator<String> hypoIter = allHyponyms.getKeys().iterator();
    	for (Entry entry : allWords.keySet()) {
    		Integer key = entry.getKey();
    		String[] val = entry.get();
    		for (int i = 0; i < val.length; i++) {
    			if (val[i] == word) {
    				String add = val[i];
    				allHyponyms.add(add);
    				graph.add(descendants(key));
    			}
    		}
    	}
    } 

}
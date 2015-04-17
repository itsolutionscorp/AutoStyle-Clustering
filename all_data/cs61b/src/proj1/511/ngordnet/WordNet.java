package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Iterator;
import java.util.Arrays;


public class WordNet {

        private int len = 0;
        private HashMap<Integer, String> first_map;
        private HashMap<String, Integer[]> second_map;
        private HashSet<String> first_set;
        private HashMap<Integer, HashSet<String>> wnet;
        Digraph graph;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	//hashmap from ID to noun
        first_map = new HashMap<Integer, String>();
    	//hashmap from noun to ID
    	second_map = new HashMap<String, Integer[]>();
        first_set = new HashSet<String>();
        wnet = new HashMap<Integer, HashSet<String>>();
        In synset = new In(synsetFilename);
        In hypo = new In(hyponymFilename);
        while(synset.hasNextLine() || !synset.isEmpty()) {

            String [] reading = synset.readLine().split(",");
            String [] synset_line = reading[1].split(" ");
            HashSet<String> w = new HashSet(Arrays.asList(synset_line));
            wnet.put(Integer.parseInt(reading[0]), w);
            for (int i = 0; i < synset_line.length; i= i +1) {
               
                Integer[] tmp = second_map.get(synset_line[i]);
                if (tmp != null){
                    //increase the size of tmp and add the element to the last
                    int length = tmp.length;
                    Integer[] p = new Integer[length + 1];

                    for (int j = 0; j < tmp.length; j ++) {
                        p[j] = tmp[j];
                    }
                    p[length] = Integer.parseInt(reading[0]);
                    second_map.put(synset_line[i], p);
                }
                else {
                    Integer[] q = new Integer[1];
                    q[0] = Integer.parseInt(reading[0]);
                    //System.out.println("q0" + q);
                    second_map.put(synset_line[i], q);
                }
                
            }
          
            len += 1;
        }

        graph = new Digraph(len);

        while(hypo.hasNextLine()|| !hypo.isEmpty()) {
            String [] reading = hypo.readLine().split(",");
            for (int i = 1; i < reading.length; i++) {
              // System.out.println(Integer.parseInt(reading[0]));
              // System.out.println(Integer.parseInt(reading[i]));
              graph.addEdge(Integer.parseInt(reading[0]), Integer.parseInt(reading[i]));

            }
        }
        
    }

    /* Returns true if NOUN is a word in some synset. */
    //String checking-- to see if the noun is inside your value
    public boolean isNoun(String noun) {
    	if (noun != null){
	    	//if (second_map.containsKey(noun)){
            if(second_map.containsKey(noun)){
             		return true;
	    	}
    	}
    	return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    
    	TreeSet<String> lst = new TreeSet<String>();
    	
    	//for (Set<String, Integer> word: second_map.keySet())
    	for (String word : second_map.keySet()) {
    		if (isNoun(word)){
    			lst.add(word);
    		}
    	}
        return lst;
    }


    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        
        HashSet<String> return_set= new HashSet<String>();
        Set<Integer> children = new HashSet<Integer>();
        HashSet<Integer> ids = new HashSet<Integer>();
        HashSet<String> getie = new HashSet<String>();
         //iterate all verticies, for each verticie (ID) get word for the ID
        
        // for (int i = 0; i < len; i ++){
        //     getie = wnet.get(i);
        //     if(getie!=null){
        //         if(getie.contains(word)){
        //             ids.add(i);
        //         }
        //     }
        // }
        ids = new HashSet(Arrays.asList(second_map.get(word)));
        children = GraphHelper.descendants(graph, ids);
       // loop through the set and added them to a set
      //  for (Integer k = 0; !children.isEmpty(); k ++){
         for (Iterator<Integer> iter = children.iterator(); iter.hasNext();) {
           // if(children.contains(k)){

            Integer k = iter.next();
            return_set.addAll(wnet.get(k));
            
        }
        return return_set;
    }

}

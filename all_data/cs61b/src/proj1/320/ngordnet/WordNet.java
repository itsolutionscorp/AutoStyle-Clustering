package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;


public class WordNet {
    private String synsetFile;
    private String hyponymFile;
    private HashMap<String, ArrayList<Integer>> nounID;
    private HashMap<Integer, String[]> idNoun;
    private Digraph theG;
    private int idCount;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetFile = synsetFilename;
        this.hyponymFile = hyponymFilename;   
       
        nounID = new HashMap<String, ArrayList<Integer>>();  
        idNoun = new HashMap<Integer, String[]>(); 
        idCount = 0;  
        setUpDict();
        setUpDiGraph();
    } 

    /** Creates a mapping from nouns to the ID of the sets they are part of
    * Also, creates a mapping from IDs to the sets of nouns they address
    */
    private void setUpDict() {
        In reader = new In(synsetFile);
        int id;
        String holder;
        String [] parts;
        String [] nouns;
        ArrayList<Integer> arb;
        while (reader.hasNextLine()) {
            idCount++;
            holder = reader.readLine();
            parts = holder.split(",");
            id = Integer.parseInt(parts[0]);
            nouns = parts[1].split(" ");
            idNoun.put(id, nouns);
            for (String noun : nouns) {
                if (nounID.containsKey(noun)) {
                    nounID.get(noun).add(id);
                } else {
                    arb = new ArrayList<Integer>();
                    arb.add(id);
                    nounID.put(noun, arb);
                }
            }
        }
    }

    /** Sets up a DiGraph with synsets as verticies */
    private void setUpDiGraph() {
        theG = new Digraph(idCount);
        In reader = new In(hyponymFile);
        String holder;
        String [] parts;
        int id;

        while (reader.hasNextLine()) {
            holder = reader.readLine();
            parts = holder.split(",");
            id = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                theG.addEdge(id, Integer.parseInt(parts[i]));
            }
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounID.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounID.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> offspring; 
        Set<String> hypnms = new TreeSet<String>();
        ArrayList<Integer> ids = nounID.get(word);
        if (ids == null) {
            return hypnms;
        }
        for (Integer id : ids) {
            offspring = new TreeSet<Integer>();
            offspring.add(id);
            offspring = GraphHelper.descendants(theG, offspring);
            for (Integer child : offspring) {
                for (String ele : idNoun.get(child)) {
                    hypnms.add(ele);
                }
            }
        }
        return hypnms;
    }
}

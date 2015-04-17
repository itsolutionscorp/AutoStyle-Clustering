package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.*;
// import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
        private String line;
        private String[] synsetNouns;
        private String[] tokens;
        private Integer synsetID;
        private ArrayList<Integer> synsetIDarray;
        private In synsetFile;
        private In hypoFile;
        private Map<Integer, String[]> idMap; //ID points to ArrayList of a synset
        private Map<String, ArrayList<Integer>> nounMap; //Noun points to the ids of synsets it belongs to
        public Digraph dg; // make private after testing

        private Set<Integer> nounIDs;
        private Set<Integer> descendantsID;
        private Set<String> descendantsNoun;


    public WordNet(String synsetFilename, String hyponymFilename)  {
        // Using a map to store WordNet
        idMap = new HashMap<Integer, String[]>();
        nounMap = new HashMap<String, ArrayList<Integer>>();

        // read the hyponym file
        synsetFile = new In(synsetFilename);
        
        while (synsetFile.hasNextLine()) {
            
            // File must be read one line at a time.
            line = synsetFile.readLine();

            // Split the lines by commas and store them in an array.
            tokens = line.split(",");

            // Split the synset into individual nouns.
            synsetNouns = tokens[1].split(" ");


            // Convert synset id from string to type Integer.
            // Then store id in arraylist synsetID.
            synsetID = Integer.parseInt(tokens[0]);

            // put keys (synset id) and vals (array of nouns) in idMap
            idMap.put(synsetID, synsetNouns);

            // Put keys (noun) and vals (array of synset ids) in nounMap.
            for (int i = 0; i < synsetNouns.length; i++) {
                // If nounMap already countains the noun as a key,
                // then update the key's val.
                if (nounMap.containsKey(synsetNouns[i])) {
                    nounMap.get(synsetNouns[i]).add(synsetID);
                } else {
                    synsetIDarray = new ArrayList<Integer>();
                // If nounMap doesn't already contain the noun as a key,
                // put the key respective val into nounMap.
                    synsetIDarray.add(synsetID);
                    nounMap.put(synsetNouns[i], synsetIDarray);
                }
            }
        }

        /** Because we need to know the size of a digraph before in advance,
          * it will be necessary to measure the size of our idMap
          * using .length.
          */
        hypoFile = new In(hyponymFilename);
        dg = new Digraph(idMap.size());
        while (hypoFile.hasNextLine()) {

            // Read the hypo file and store it in an array
            line = hypoFile.readLine();

            // Split the line by ","
            tokens = line.split(",");

            // Add the values into the digraph.
            for (int i = 1; i < tokens.length; i++) {
                dg.addEdge(Integer.parseInt(tokens[0]), 
                           Integer.parseInt(tokens[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounMap.containsKey(noun); //fix
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (nounMap.containsKey(word)){
            // If the word is in data set, returns all descendants
            // in a Set<String>.
            nounIDs = new TreeSet<Integer>();
            for (int i : nounMap.get(word)) {
                nounIDs.add(i);
            }

            descendantsID = GraphHelper.descendants(dg, nounIDs);
            
            descendantsNoun = new TreeSet<String>();
            for (int i : descendantsID) {

                for (String j : idMap.get(i)) {

                    descendantsNoun.add(j);
                }
            }
            return descendantsNoun;
        }
        throw new NoSuchElementException("Data does not contian:" + word);
    }
}


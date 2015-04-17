package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    private HashMap<String, Entry> synsetMap = new HashMap<String, Entry>();
    private HashMap<Integer, Entry> synsetIDMap = new HashMap<Integer, Entry>();
    private Digraph idConnections;
    public WordNet(String synsetFilename, String hypernymFilename) {
        In synset = new In(synsetFilename);
        In hypernym = new In(hypernymFilename);
        int digraphSize = 0;
        while (synset.hasNextLine()) {
            String text = synset.readLine();
            String[] synAndDef = text.split(",");
            int id = Integer.parseInt(synAndDef[0]);
            String[] words = synAndDef[1].split(" "); 
            for (String word : words) {
                if (synsetMap.containsKey(word)) {
                    Entry oldData = synsetMap.get(word);
                    Entry newData = new Entry(id, words, oldData);
                    synsetMap.put(word, newData);
                } else {
                    Entry synsetData = new Entry(id, words, null);
                    synsetMap.put(word, synsetData);

                }
                Entry synsetData = new Entry(id, words, null);
                synsetIDMap.put(id, synsetData);
            }
            digraphSize += 1;
        }
        idConnections = new Digraph(digraphSize);
        while (hypernym.hasNextLine()) {
            String[] currLine = hypernym.readLine().split(",");
            int id = Integer.parseInt(currLine[0]);
            for (int i = 1; i < currLine.length; i += 1) {
                idConnections.addEdge(id, Integer.parseInt(currLine[i]));
            }
        }

    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        HashSet<String> words = new HashSet<String>();
        Entry synset = synsetMap.get(word);
        HashSet<Integer> ids = new HashSet<Integer>();
        while (synset != null) {
            ids.add(synset.getID());
            for (String synonymWord : synset.getSynonyms()) {
                words.add(synonymWord);
            }
            synset = synset.next;
        }
        Set<Integer> descendantIDs = GraphHelper.descendants(idConnections, ids);
        for (int descID : descendantIDs) {
            for (String descWord : synsetIDMap.get(descID).getSynonyms()) {
                words.add(descWord);
            }
        }

        return words;
    }





    /**  Entry class from homework 5, From CS61B and Josh Hug
    */
    public class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(int idNum, String[] syn, Entry n) { 
            id = idNum;
            synonyms = syn;
            next = n;
        }

        public int getID() {
            return id;
        }

        public String[] getSynonyms() {
            return synonyms;
        }
        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */

        /** Stores the key of the key-value pair of this node in the list. */
        private int id;
        /** Stores the value of the key-value pair of this node in the list. */
        private String[] synonyms;
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }
}


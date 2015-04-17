package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;



public class WordNet {
    /* Primary goal is to find the hyponyms (capture idea)/
    hypernyms (straight synonyms) of a word (capture a certain idea)*/
    private TreeMap<Integer, ArrayList<String>> id2Word = 
        new TreeMap<Integer, ArrayList<String>>(); // id to definition
    private TreeMap<String, ArrayList<Integer>> word2ID = 
        new TreeMap<String, ArrayList<Integer>>();
    private Digraph hypGraph; 
    /* Things that hashmaps can do: size(), isEmpty(), 
    get(), containsKey(), put(), putAll() - copies a map, 
    remove(), clear(), containsValue(), keySet, entrySet, values */
    public WordNet(String synsetFilename, String hyponymFilename) {
        //digraph for hyponyms 
        // TO-DO: go through hyponyms file, and make the number be the key, 
        //and add edges for the rest)
        
        
        ArrayList<Integer> allIDS = new ArrayList<Integer>();
        ArrayList<String> values = new ArrayList<String>(); 
        
        In synFile = new In(synsetFilename); // ID, name(string), definition (whatever)
        In hypFile = new In(hyponymFilename); // just numbers
        //THEMAP.get(synsetArr[i]).add(THE ID);
       
        while (!synFile.isEmpty()) {
            String synLine = synFile.readLine();
            String[] synData = synLine.split(","); // each new input value  
            String[] synWords = synData[1].split(" ");     
            Integer synID = Integer.parseInt(synData[0]); 
            for (int i = 0; i < synWords.length; i++) {
                
                if (!id2Word.containsKey(synID)) { //brand new group
                    ArrayList<String> newval = new ArrayList<String>();
                    newval.add(synWords[i]);
                    id2Word.put(synID, newval); 
                    // System.out.println(synWords[i]);
                    // allIDS.add(synID);
                
                } else {
                    id2Word.get(synID).add(synWords[i]);
                }

                if (!word2ID.containsKey(synWords[i])) {
                    ArrayList<Integer> newval = new ArrayList<Integer>();
                    newval.add(synID);
                    word2ID.put(synWords[i], newval);
                    
                } else {
                    word2ID.get(synWords[i]).add(synID);

                }
                
                // if (id2Word.containsKey(synID)) {
                //     ArrayList<String> nw = id2Word.get(i);
                //     ArrayList<Integer> nid = word2ID.get(synWords[i]);
                //     nw.add(synWords[i]);
                //     nid.add(synID);
                //     id2Word.put(synID, nw);
                //     word2ID.put(synWords[i], nid);
                // }
                
            }
        }
        // System.out.println(id2Word);
        // System.out.println("word2ID: " + word2ID.entrySet());
        // System.out.println("id2Word: " + id2Word.entrySet());

        hypGraph = new Digraph(word2ID.size());
        while (!hypFile.isEmpty()) {
            String hypLine = hypFile.readLine();
            String[] hypData = hypLine.split(",");
            Integer hypID = Integer.parseInt(hypData[0]); 
            for (int i = 1; i < hypData.length; i++) {
                Integer temp = Integer.parseInt(hypData[i]);
                hypGraph.addEdge(hypID, temp);
                ///is this how you add an edge??
            }
        }
        //System.out.println("testing2 = " + id2Word.entrySet());
    }
        
    // private static void main(String[] args) {
    //     WordNet out = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");
    // }
    
    public boolean isNoun(String noun) {
        return word2ID.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return word2ID.keySet();
    }
        ///
    
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

    //CONCEPTUAL UNDERSTANDING COURTESY OF: Cesar Villalobos :)       


        Set<String> wordset = new TreeSet<String>();
        Set<Integer> ids = new TreeSet<Integer>();
        Set<Integer> desc = new TreeSet<Integer>(); 

        for (Integer d : word2ID.get(word)) { 
            ids.add(d); // only one id, new hashmap??
        }    

        desc = GraphHelper.descendants(hypGraph, ids);
        // System.out.println("errorfind " + desc);

        // System.out.println("is this: " + id2Word.entrySet());
        for (Integer id : desc) {
        // System.out.println("is this2: " + id2Word.get(id));
            for (String wo : id2Word.get(id)) {
                wordset.add(wo);
                // System.out.println("problemchild " + wo);
            }
        }
        //System.out.println("hyponym is:");
        return wordset;            
    }
}

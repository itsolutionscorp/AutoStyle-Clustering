/* 
Name: Wendy Tang
CS 61B Proj1 
3/3/2015
*/
package ngordnet;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    
    private String[] sLine; //REVISIT: change to private
    private String[] hLine;
    private String[] sRawtokens;
    private String[] hRawtokens;
    private HashMap<Integer, TreeSet<String>> sMap;
    private Digraph graph;
    private HashMap<String, TreeSet<Integer>> sRevmap;
    private TreeSet<Integer> sIdset;

    public WordNet(String synsetFilename, String hyponymFilename) {
        //Each line of synsets11.txt translates into one item in a String[]
        //Same for hyponyms11.txt
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        sLine = synsetFile.readAllLines();
        hLine = hyponymFile.readAllLines();

        //Put String read from synsets11.txt into a HashMap;
        // Map<Keys = Integer ID, Values = String Synsets> 
        sMap = new HashMap<Integer, TreeSet<String>>();
        sRevmap = new HashMap<String, TreeSet<Integer>>();

        String[] tempString;
        String[] tempWords;
        Integer sID;

        for (String s: sLine) {
            tempString = s.split(",");
            sID = Integer.parseInt(tempString[0]);

            // First break up tempString[1] into individual words
            tempWords = tempString[1].split(" ");

            for (int j = 0; j < tempWords.length; j++) {
                //sMap: Key is ID (Integer), Value is TreeSet of Words (String)
                //Check case: if key already exists
                if (j >= 1) {
                    sMap.get(sID).add(tempWords[j]);
                } else {
                    TreeSet<String> sWordset = new TreeSet<String>();
                    sWordset.add(tempWords[j]);
                    sMap.put(sID, sWordset);
                }

                // Implementing sRevmap: Key is word, Value is Set of ID's
                // Check case: if key already exists
                if (sRevmap.containsKey(tempWords[j])) {
                    sRevmap.get(tempWords[j]).add(sID);
                } else {
                    sIdset = new TreeSet<Integer>();
                    sIdset.add(sID);
                    sRevmap.put(tempWords[j], sIdset);
                }
            }
        }
        // Put String 
        // Map<Keys = TreeSet<String> , Values = List<TreeSet<Integer>>>
        // Loop through ID's, get Values, add to set of values
        // If values is already in the set, extend list, add id for key

        //Put String read from hyponyms11.txt into Digraph;
        graph = new Digraph(sMap.size());
        int parent;
        int child;
        for (int m = 0; m < hLine.length; m++) {
            tempString = hLine[m].split(",");
            for (int n = 0; n < tempString.length - 1; n++) {
                parent = Integer.parseInt(tempString[0]);
                child = Integer.parseInt(tempString[n + 1]);
                graph.addEdge(parent,child);
            }
        }
    }

    public boolean isNoun(String noun) {
        return sRevmap.containsKey(noun);
    }

    public Set<String> nouns() {
        return sRevmap.keySet();
    }
    //Set<String>
    public Set<String> hyponyms(String word) {
        // sQuerykeys will store TreeSet<Integer> of matches
        TreeSet<Integer> sQuerykeys = new TreeSet<Integer>();
        sQuerykeys = sRevmap.get(word);
        // sDescendantsstr will store TreeSet<String> of descendants
        TreeSet<String> sDescendantsstr = new TreeSet<String>();

        TreeSet<String> tempDescendant;
        // sDescendantsint will store Object[] of descendants
        Object[] sDescendantsint;

        // Take care of null cases
        if (word == null || word.equals("")) {
            return null;
        } 
        if (sQuerykeys == null) {
            return null;
        } else {
            // Obtain Object[] of descendant integers
            sDescendantsint = GraphHelper.descendants(graph,sQuerykeys).toArray();
        }

        // Loop through sDescendantsint, and get String Values from sMap
        for (int j = 0; j < sDescendantsint.length; j++) {
            // Only have one copy of the word
            // Take only first of sQuerykeys
            Integer descendantInt = (Integer) sDescendantsint[j];
            tempDescendant = sMap.get(descendantInt);
            Iterator<String> tempDescendantIT = tempDescendant.iterator();
            while (tempDescendantIT.hasNext()) {
                sDescendantsstr.add(tempDescendantIT.next());                    
            }
        }
        return sDescendantsstr;
    }
}

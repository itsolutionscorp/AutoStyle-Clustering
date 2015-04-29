package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private HashMap<Integer, ArrayList<String>> sMap; //Lab assistant tipped me.
    private HashMap<Integer, ArrayList<Integer>> hMap;
    //Lab assistant tipped me.
    private Set<String> exists = new HashSet<String>();
    private Digraph digraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In s = new In(synsetFilename); // this is a synset.
        In hIn = new In(hyponymFilename);
        ArrayList<Integer> hyp = new ArrayList<Integer>();
        sMap = new HashMap<Integer, ArrayList<String>>();
        hMap = new HashMap<Integer, ArrayList<Integer>>();

        while (s.hasNextLine()) { //checks if has line.
            ArrayList<String> syn = new ArrayList<String>();
            String synsetLine = s.readLine(); //"000000,action jump,dummy".
            String[] word = synsetLine.split(",", 2);
            Integer sKey = Integer.parseInt(word[0]); //"0000".
            String edit = word[1].split(",")[0]; //"jump line, dummy".
            String[] valueArray = edit.split(" ");
            for (int i = 0; i < valueArray.length; i++) {
                syn.add(valueArray[i]);
                exists.add(valueArray[i]);
            }
            sMap.put(sKey, syn);
        }
        digraph = new Digraph(sMap.size());

        while (hIn.hasNextLine()) {
            String hyponymLine = hIn.readLine(); //"000000,933,2011".
            String[] splitting = hyponymLine.split(",", 2);
            Integer hKey = Integer.parseInt(splitting[0]); //"0000".
            digraph.addEdge(hKey, hKey);
            String[] hypValues = splitting[1].split(",");
            for (int i = 0; i < hypValues.length; i++) {
                Integer hKeyz = Integer.parseInt(hypValues[i]); //"0000".
                hyp.add(hKeyz);
                digraph.addEdge(hKey, hKeyz);
            }
            hMap.put(hKey, hyp);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String item : exists) {
            if (item.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return exists;
    }

    public Set<String> hyponyms(String word) {
        Set<String> returnMe = new HashSet<String>();
        Set<Integer> scenarios = new HashSet<Integer>(); //set of iDs for word.
        for (int key : sMap.keySet()) {
            if (sMap.get(key).contains(word)) {
                scenarios.add(key);
            }
        }
        Set<Integer> iDs = GraphHelper.descendants(digraph, scenarios);
        for (int item : iDs) {
            ArrayList<String> items = sMap.get(item);
            for (int i = 0; i < items.size(); i++) {
                returnMe.add(items.get(i));
            }
        }
        return returnMe;
    }
}

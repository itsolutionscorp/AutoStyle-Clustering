//package
package ngordnet;

//imports
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;
import java.util.ArrayList;

//WorNet class
public class WordNet {
    //variables
    private In synset;
    private In hyponym;
    private HashMap<Integer, ArrayList<String>> synMap;
    private HashMap<Integer, ArrayList<Integer>> hypMap;
    private Set<String> nouns;
    private Set<String> hyponyms;
    private HashSet<Integer> haveBeenChecked;

    //constructor
    public WordNet(String synsetFileName, String hyponymFileName) {
        //create inputs
        synset = new In(synsetFileName);
        hyponym = new In(hyponymFileName);
        nouns = new HashSet<String>();
        //create map
        synMap = new HashMap<Integer, ArrayList<String>>();
        hypMap = new HashMap<Integer, ArrayList<Integer>>();
        String[] lineList;
        String[] hypLineList;
        ArrayList<String> synonyms;
        ArrayList<Integer> hyps;
        String line;
        String hypLine;
        int id;
        int hypId;
        //create synMap
        while (synset.exists() && !synset.isEmpty()) {
            line = synset.readLine();
            lineList = line.split(",");
            id = Integer.parseInt(lineList[0]);
            synonyms = new ArrayList<String>(Arrays.asList(lineList[1].split(" ")));
            synMap.put(id, synonyms);
        }
        //create hypMap
        while (hyponym.exists() && !hyponym.isEmpty()) {
            hypLine = hyponym.readLine();
            hypLineList = hypLine.split(",");
            hypId = Integer.parseInt(hypLineList[0]);
            hyps = new ArrayList<Integer>();
            for (int k = 1; k < hypLineList.length; k++) {
                hyps.add(Integer.parseInt(hypLineList[k]));
            }
            if (hypMap.containsKey(hypId)) {
                hyps.addAll(hypMap.get(hypId));
            }
            hypMap.put(hypId, hyps);
        }
        //initiate nouns set
        Iterator<ArrayList<String>> iter = synMap.values().iterator();
        while (iter.hasNext()) {
            Object[] curr = iter.next().toArray();
            for (int i = 0; i < curr.length; i++) {
                nouns.add((String) curr[i]);
            }
        }
    }

    //methods
    public Set<String> hyponyms(String word) {
        //return set of all hyponyms of word as well as all
        //synonyms of word. If word belongs to multiple synsets, 
        //return all hyponyms of all of these synsets. Do not
        //include hyponyms of synonmys.
        haveBeenChecked = new HashSet<Integer>();
        hyponyms = new HashSet<String>();
        if (isNoun(word)) {
            Iterator<Integer> iter = synMap.keySet().iterator();
            while (iter.hasNext()) {
                int thisKey = iter.next();
                if (!haveBeenChecked.contains(thisKey) && synMap.get(thisKey).contains(word)) {
                    getHyponyms(thisKey);
                }
            }
        }
        return hyponyms;
    }

    private void getHyponyms(int key) {
        //gets hyponyms and nested hyponyms
        Iterator<String> iter = synMap.get(key).iterator();
        while (iter.hasNext()) {
            String word = iter.next();
            hyponyms.add(word);
        }
        if (hypMap.containsKey(key)) {
            Iterator<Integer> hypIter = hypMap.get(key).iterator();
            while (hypIter.hasNext()) {
                int newKey = hypIter.next();
                if  (!haveBeenChecked.contains(newKey)) {
                    getHyponyms(newKey);
                }
            }
        }
        haveBeenChecked.add(key);
    }

    public boolean isNoun(String noun) {
        //returns if noun is a noun in some synset
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        //returns set of all nouns
        return nouns;
    }
}

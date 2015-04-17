package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class WordNet {
    private In syn;
    //read synsetFile;
    private In hyp;
    //read hyponymFile;
    private HashSet<String> stringSynset;
    //store synset String; 
    private HashMap<Integer, HashSet<String>> synsetDict;
    //store the ID as key, words set as value;
    private HashMap<String, HashSet<Integer>> synsetReverDict;
    //store the words set as key, ID array as value;
    private Digraph g;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        syn = new In(synsetFilename);
        hyp = new In(hyponymFilename);
        stringSynset = new HashSet<String>();
        synsetDict = new HashMap<Integer, HashSet<String>>();
        synsetReverDict = new HashMap<String, HashSet<Integer>>();          
        while (syn.hasNextLine()) {
            //check if still can read;
            String result = syn.readLine();
            //get each line out;
            String[] line = result.split(",");
            //get three part,discard the third;
            String synsetWords = line[1];
            //get the words;
            String[] wordsArray = synsetWords.split(" ");
            //get the words array;            
            String synsetIdString = line[0];
            //get the string of the ID number;
            Integer synsetId = Integer.parseInt(synsetIdString);
            //get the integer;
            HashSet<String> wordsSet = new HashSet<String>();
            for (String x: wordsArray) {
                wordsSet.add(x);
                stringSynset.add(x);
                //build the set of all the words;
                if (synsetReverDict.containsKey(x)) {
                    HashSet<Integer> m = synsetReverDict.get(x);
                    m.add(synsetId);
                } else {
                    HashSet<Integer> c = new HashSet<Integer>();
                    c.add(synsetId);
                    synsetReverDict.put(x, c);
                }                    
            }
            //change the words array to words set;            
            synsetDict.put(synsetId, wordsSet);
            //build the dict of map ID to words set;
        }

        g = new Digraph(stringSynset.size()); 
        while (hyp.hasNextLine()) {
            String result = hyp.readLine();
            String[] line = result.split(",");
            String firstString = line[0];
            Integer first = Integer.parseInt(firstString);
            int i = 1;
            while (i < line.length) {
                int x = Integer.parseInt(line[i]);
                g.addEdge(first, x);
                i = i + 1;
            }
        }
    }
    
    public boolean isNoun(String noun) {
        return stringSynset.contains(noun);
    }    

    public Set<String> nouns() {
        return stringSynset;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> hyponymsTotal = new HashSet<String>();
        HashSet<Integer> wordsID = synsetReverDict.get(word);
        Set<Integer> intHyponyms = GraphHelper.descendants(g, wordsID);
        for (Integer x: intHyponyms) {
            if (synsetDict.containsKey(x)) {
                hyponymsTotal.addAll(synsetDict.get(x));
            }
        }
        return hyponymsTotal;
    }
}

package ngordnet;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private In stream;
    private In hyp;
    private HashMap<String, ArrayList<Integer>> nounMap;
    private HashMap<Integer, ArrayList<String>> idNounMap;
    private Digraph hypo;

    public WordNet(String synsetFilename, String hyponymFilename) {
        stream = new In(synsetFilename);
        hyp = new In(hyponymFilename);
        nounMap = new HashMap<String, ArrayList<Integer>>(); //HashMap in the form of <noun, ids>
        idNounMap = new HashMap<Integer, ArrayList<String>>(); //HashMap in the form of <id, nouns>

        for (int j = 0; stream.hasNextLine(); j++) {
            String line = stream.readLine();
            String[] temp = line.split(",");
            ArrayList<Integer> singleKey = new ArrayList<Integer>();
            ArrayList<String> nounID = new ArrayList<String>();
            String[] parts = temp[1].split(" ");
            for (int i = 0; i < parts.length; i++) {
                nounID.add(parts[i]);
            }
            idNounMap.put(Integer.parseInt(temp[0]), nounID);

            singleKey.add(Integer.parseInt(temp[0]));
            if (temp[1].contains(" ")) {
                for (int k = 0; k < parts.length; k++) {
                    if (nounMap.containsKey(parts[k])) { //already has the noun so adding another id
                        ArrayList<Integer> replaced = new ArrayList<Integer>(nounMap.get(parts[k]));
                        replaced.add(Integer.parseInt(temp[0]));
                        nounMap.put(parts[k], replaced);
                    } else { //adding new noun with id
                        nounMap.put(parts[k], singleKey);
                    }
                } 
            } else {
                if (nounMap.containsKey(temp[1])) {
                    ArrayList<Integer> replaced = new ArrayList<Integer>(nounMap.get(temp[1]));
                    replaced.add(Integer.parseInt(temp[0]));
                    nounMap.put(temp[1], replaced);
                } else {
                    nounMap.put(temp[1], singleKey);
                }
            }
        }

        Integer digraphSize = nounMap.size();
        hypo = new Digraph(digraphSize);

        for (int i = 0; hyp.hasNextLine(); i++) {
            String hypline = hyp.readLine();
            String[] temphyp = hypline.split(",");
            for (int j = 1; j < temphyp.length; j++) {
                hypo.addEdge(Integer.parseInt(temphyp[0]), Integer.parseInt(temphyp[j]));
            } 
        }
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> wordID = new TreeSet<Integer>(); //ids of the word
        ArrayList<Integer> idList = nounMap.get(word); 
        for (Integer id : idList) { 
            wordID.add(id);
        }
        Set<Integer> hypoID = GraphHelper.descendants(hypo, wordID); //descendants of all the IDs
        Set<String> hyponyms = new TreeSet<String>();
        for (Integer id : hypoID) {
            ArrayList<String> hyponouns = idNounMap.get(id); 
            for (String noun : hyponouns) {
                hyponyms.add(noun);
            }
        }
        return hyponyms;
    }

    public boolean isNoun(String noun) {
        return (nounMap.containsKey(noun));
    }
        
    public Set<String> nouns() {
        return nounMap.keySet();
    }
}

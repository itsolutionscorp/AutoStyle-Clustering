package ngordnet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private In synsets;
    private In hyponyms;
    private int size;
    private HashMap<String, ArrayList<Integer>> stringToID;
    private HashMap<Integer, ArrayList<String>> idToSynsets;
    private Digraph nounMap;
    private TreeSet<String> allNouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new In(synsetFilename);
        hyponyms = new In(hyponymFilename);
        stringToID = new HashMap<String, ArrayList<Integer>>();
        idToSynsets = new HashMap<Integer, ArrayList<String>>();
        allNouns = new TreeSet<String>();
        
        while (synsets.hasNextLine()) {
            size += 1;
            String readLine = synsets.readLine();
            String[] wholeLine = readLine.split(",");
            int idNumber = Integer.parseInt(wholeLine[0]);
            String[] synset = wholeLine[1].split("\\s+");
            ArrayList<String> synsetHolder = new ArrayList<String>();
            for (String s: synset) {
                synsetHolder.add(s);
                allNouns.add(s);
                if (stringToID.containsKey(s)) {
                    ArrayList<Integer> keyTemp = stringToID.get(s);
                    keyTemp.add(idNumber);
                    stringToID.put(s, keyTemp);
                } else {
                    ArrayList<Integer> keyTemp = new ArrayList<Integer>();
                    keyTemp.add(idNumber);
                    stringToID.put(s, keyTemp);
                }   
            }
            idToSynsets.put(idNumber, synsetHolder);
        }
        
        nounMap = new Digraph(size);
        while (hyponyms.hasNextLine()) {
            String readLine = hyponyms.readLine();
            String[] wholeLine = readLine.split(",");
            int mySynset = Integer.parseInt(wholeLine[0]);
            for (int i = 1; i < wholeLine.length; i += 1) {
                int hyponymID = Integer.parseInt(wholeLine[i]);
                nounMap.addEdge(mySynset, hyponymID);
            }
        }
    }
    
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }
    
    public Set<String> nouns() {
        return allNouns;
    }
    
    public Set<String> hyponyms(java.lang.String word) {
        TreeSet<Integer> synsetsID = new TreeSet<Integer>();
        Set<String> returnVals = new TreeSet<String>();
        if (stringToID.containsKey(word)) {
            ArrayList<Integer> wordIDs = stringToID.get(word);
            for (int i = 0; i < wordIDs.size(); i += 1) {
                synsetsID.add(wordIDs.get(i));
            }
            Set<Integer> values = GraphHelper.descendants(nounMap, synsetsID);
            Object[] hyponymVals = values.toArray();
            for (int i = 0; i < hyponymVals.length; i += 1) {
                ArrayList<String> descendantSynset = idToSynsets.get(hyponymVals[i]);
                for (int x = 0; x < descendantSynset.size(); x += 1) {
                    returnVals.add(descendantSynset.get(x));
                }
            }
        }
        return returnVals;
    }
}

package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;


public class WordNet {

    private ArrayList<ArrayList<Integer>> hypernymParsed;

    private HashMap<Integer, String[]> synsetKeyID;

    private HashMap<String, HashSet<Integer>> synsetKeyNoun;

    private Digraph web;

    private int maxIDNumber;

    public WordNet(String synsetFilename, String hypernymFilename) {
        In hypernymRead = new In(hypernymFilename);
        hypernymParsed = new ArrayList<ArrayList<Integer>>();
        String line;
        String[] splitList = null;
        ArrayList<Integer> toAdd = new ArrayList<Integer>();
        while (hypernymRead.hasNextLine()) {
            line = hypernymRead.readLine();
            splitList = line.split(",");
            for (int i = 0; i < splitList.length; i++) {
                toAdd.add(Integer.parseInt(splitList[i]));
            }
            hypernymParsed.add(toAdd);
            toAdd = new ArrayList<Integer>();      
        }
        In synsetRead = new In(synsetFilename);
        String[] splitSynsets;
        String[] splitInt = null;
        Integer id;
        synsetKeyID = new HashMap<Integer, String[]>();
        synsetKeyNoun = new HashMap<String, HashSet<Integer>>();
        HashSet<Integer> toAddSyn;
        while (synsetRead.hasNextLine()) {
            line = synsetRead.readLine();
            splitInt = line.split(",");
            splitSynsets = splitInt[1].split(" ");
            id = Integer.parseInt(splitInt[0]);
            for (int i = 0; i < splitSynsets.length; i++) {
                if (synsetKeyNoun.get(splitSynsets[i]) == null) {
                    toAddSyn = new HashSet<Integer>();
                    toAddSyn.add(id);
                    synsetKeyNoun.put(splitSynsets[i], toAddSyn);
                } else {
                    synsetKeyNoun.get(splitSynsets[i]).add(id);
                }
            }
            synsetKeyID.put(id, splitSynsets);
        }
        if (splitInt[0] != null) {
            maxIDNumber = Integer.parseInt(splitInt[0]);
            web = new Digraph(maxIDNumber + 1);
            for (int i = 0; i < hypernymParsed.size(); i++) {
                for (int j = 1; j < hypernymParsed.get(i).size(); j++) {
                    web.addEdge(hypernymParsed.get(i).get(0),
                                hypernymParsed.get(i).get(j));
                }
            }
        }
    }   

    public boolean isNoun(String noun) {
        return synsetKeyNoun.keySet().contains(noun);
    }

    public Set<String> nouns() {
        return synsetKeyNoun.keySet();
    }

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> idNumbers = synsetKeyNoun.get(word);
            Set<String> returnedSet = new HashSet<String>();
            Set<Integer> toConvert = GraphHelper.descendants(web, idNumbers);
            for (Integer item: toConvert) {
                String[] wordList = synsetKeyID.get(item);
                for (int i = 0; i < wordList.length; i++) {
                    returnedSet.add(wordList[i]);
                }
            }
            return returnedSet;
        }
        return new HashSet<String>();
    }
}

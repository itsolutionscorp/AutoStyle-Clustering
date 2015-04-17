package ngordnet;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


public class WordNet { 

    private Digraph wordNet;
    private Map<String, Set<Integer>> nounToSynsetId;
    private Map<Integer, Set<String>> synsetIdToSynset;

    public WordNet(String synsetFilename, String hynonymFilename) { 
        nounToSynsetId = new HashMap<String, Set<Integer>>();
        synsetIdToSynset = new HashMap<Integer, Set<String>>();
        int countSynsets = 0;
        In readSynset = new In(synsetFilename); 
        In readHynonym = new In(hynonymFilename);
      //Handles populating nounToSynsetId
        while (readSynset.hasNextLine()) {
            String currLine = readSynset.readLine(); 
            String[] synsetDef = currLine.split(","); 
            Integer synsetId = Integer.parseInt(synsetDef[0]);
            String[] synsets = synsetDef[1].split(" ");
            Set<String> synsetSet = new TreeSet<String>();
            for (String synset : synsets) {
                Set<Integer> ids = nounToSynsetId.get(synset);
                if (ids == null) {
                    ids = new TreeSet<Integer>();
                }
                ids.add(synsetId);
                nounToSynsetId.put(synset, ids);
                synsetSet.add(synset);
            }
            synsetIdToSynset.put(synsetId, synsetSet);
            countSynsets++;
        }
            //Handle population of digraph
        wordNet = new Digraph(countSynsets);
        while (readHynonym.hasNextLine()) {
            String currHynLine = readHynonym.readLine();
            String[] hyponymList = currHynLine.split(",");
            int parent = Integer.parseInt(hyponymList[0]);
            for (int i = 1; i < hyponymList.length; i++) {
                wordNet.addEdge(parent, Integer.parseInt(hyponymList[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return nounToSynsetId.containsKey(noun);
    }

    public Set<String> nouns() {
        return nounToSynsetId.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> wordIds = nounToSynsetId.get(word);
        Set<Integer> childrenIds = GraphHelper.descendants(wordNet, wordIds);
        Set<String> hynonyms = new TreeSet<String>();
        for (Integer id : childrenIds) {
            hynonyms.addAll(synsetIdToSynset.get(id));
        }
        return hynonyms;
    }
}

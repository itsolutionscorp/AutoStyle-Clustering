package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, String> allWords;
    private HashMap<String, Integer> wordsToVal;
    private Digraph digraph;

    public WordNet(String synset, String hypernym) {
        In synsetDoc = new In(synset);
        allWords = new HashMap<Integer, String>();
        wordsToVal = new HashMap<String, Integer>();

        while (synsetDoc.hasNextLine()) {
            String synsetLine = synsetDoc.readLine();
            String[] synArray = synsetLine.split(",");
            int synID = Integer.parseInt(synArray[0]);
            allWords.put(synID, synArray[1]);
            wordsToVal.put(synArray[1], synID);
        }
        In hypernymDoc = new In(hypernym);
        digraph = new Digraph(allWords.size());
        while (hypernymDoc.hasNextLine()) {
            String hypernymLine = hypernymDoc.readLine(); 
            String[] idArray = hypernymLine.split(",");
            int hypernymSynID = Integer.parseInt(idArray[0]);
            for (int i = 1; i < idArray.length; i++) {
                int hyponymID = Integer.parseInt(idArray[i]);
                digraph.addEdge(hypernymSynID, hyponymID);
            }
        }
    }

    public boolean isNoun(String noun) {
        Set<String> nounSet = new HashSet<String>(allWords.values());
        return finalSet(nounSet).contains(noun);
    }

    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>(allWords.values());
        return finalSet(nounSet);
    }

    //returns a set of all the words broken up. Takes care of jump parachute. 
    private Set<String> finalSet(Set<String> values) {
        Set<String> finalSet = new HashSet<String>();
        for (String s : values) {
            if (s.contains(" ")) {
                String[] subSetArray = s.split(" ");
                for (int i = 0; i < subSetArray.length; i++) {
                    finalSet.add(subSetArray[i]);
                }
            } else {
                finalSet.add(s);
            }
        }
        return finalSet;
    }
    //changes the idSet to a stringSet. This feeds to finalSet.
    private Set<String> idToString(Set<Integer> idSet) {
        Set<String> nounSet = new HashSet<String>();
        for (Integer id : idSet) {
            nounSet.add(allWords.get(id));
        }
        return nounSet;
    }

    public Set<String> hyponyms(String word) {
        Integer wordID = 0;
        Set<Integer> idSet = new HashSet<Integer>();
        for (Integer id : allWords.keySet()) {
            String wordSet = allWords.get(id);
            String[] wordArray = wordSet.split(" ");
            for (String wordInArray : wordArray) { 
                if (word.equals(wordInArray)) {
                    idSet.add(id);
                }
            }
        }
        idSet = GraphHelper.descendants(digraph, idSet);
        Set<String> finalNounSet = idToString(idSet);
        return finalSet(finalNounSet);
    }
}

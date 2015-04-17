package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet extends java.lang.Object {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    
    private Map<Integer, List<String>> synsets = new HashMap<Integer, List<String>>();
    private Map<Integer, List<Integer>> hyponyms = new HashMap<Integer, List<Integer>>();
    private Digraph hypDigraph;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        synParse(synsetFilename);
        hypParse(hyponymFilename);
        hypDigraph();
    }
    
    private void hypDigraph() {
        hypDigraph = new Digraph(synsets.size());
        for (Map.Entry<Integer, List<Integer>> entry : hyponyms.entrySet()) {
            Integer key = entry.getKey();
            List<Integer> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                hypDigraph.addEdge(key, value.get(i));
            }
        }
    }

    private void synParse(String synsetFilename) {
        
        In synFile = new In(synsetFilename);
        String word = "";
        int key = 0;
        List<String> synValue = new ArrayList<String>();
        
        while (synFile.hasNextLine()) {
            synValue = new ArrayList<String>();
            boolean isKey = true;
            String line = synFile.readLine();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (isKey) {
                    if (c != ',') {
                        word += c;
                    } else if (c == ',') {
                        key = Integer.parseInt(word);
                        word = "";
                        isKey = false;
                    }
                } else if (!isKey) {
                    if (c == ',') {
                        isKey = true;
                        synValue.add(word);
                        word = "";
                        break;
                    }
                    if (c != ' ') {
                        word += c;
                    } else if (c == ' ') {
                        synValue.add(word);
                        word = "";
                    }
                }
            }
            synsets.put(key, synValue);
        }
    }
    
    private void hypParse(String hyponymFilename) {
        In hypFile = new In(hyponymFilename);
        int key = 0;
        String vertex = "";
        List<Integer> hypValue = new ArrayList<Integer>();
        while (hypFile.hasNextLine()) {
            hypValue = new ArrayList<Integer>();
            boolean isKey = true;
            boolean repeated = false;
            String line = hypFile.readLine();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (isKey) {
                    if (c != ',') {
                        vertex += c;
                    } else if (c == ',') {
                        key = Integer.parseInt(vertex);
                        if (hyponyms.containsKey(key)) {
                            repeated = true;
                        }
                        vertex = "";
                        isKey = false;
                    }
                } else if (!isKey) {
                    if (c != ',') {
                        vertex += c;
                    }
                    if (c == ',') {
                        hypValue.add(Integer.parseInt(vertex));
                        vertex = "";
                    }                   
                    if (isEnd(line, i + 1)) {
                        isKey = true;
                        hypValue.add(Integer.parseInt(vertex));
                        vertex = "";
                        break;
                    }
                }
            }
            if (repeated) {
                for (int i = 0; i < hypValue.size(); i++) {
                    hyponyms.get(key).add(hypValue.get(i));
                }
            } else {
                hyponyms.put(key, hypValue);
            }
        }
    }
    
    private boolean isEnd(String line, int i) {
        try {
            line.charAt(i);
        } catch (StringIndexOutOfBoundsException e) {
            return true;
        }
        return false;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (List<String> value : synsets.values()) {
            for (int i = 0; i < value.size(); i++) {
                if (value.get(i).equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        for (List<String> value : synsets.values()) {
            for (int i = 0; i < value.size(); i++) {
                if (!nounSet.contains(value.get(i))) {
                    nounSet.add(value.get(i));
                }
            }
        }
        return nounSet;
    }
    
    private List<Integer> getHyponyms(List<Integer> synID) {
        List<Integer> hyps = new ArrayList<Integer>();
        List<Integer> getHyps = new ArrayList<Integer>();
        List<Integer> subHyps = new ArrayList<Integer>();
        for (Integer id : synID) {
            hyps.add(id);
        }
        for (Map.Entry<Integer, List<Integer>> hypEntry : hyponyms.entrySet()) {
            Integer hypKey = hypEntry.getKey();
            List<Integer> hypValue = hypEntry.getValue();
            for (Integer id : synID) {
                if (hypKey == id) {
                    for (Integer hypID : hypValue) {
                        hyps.add(hypID);
                    }
                    for (Integer hypID : hypValue) {
                        for (Integer num : hypValue) {
                            if (num == hypID) {
                                getHyps.add(num);
                                subHyps = getHyponyms(getHyps);
                            }
                        }
                    }
                    for (Integer hyp : subHyps) {
                        hyps.add(hyp);
                    }
                }
            }
        }
        return hyps;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> nounSet = new HashSet<String>();
        nounSet.add(word);
        List<Integer> synID = new ArrayList<Integer>();
        List<Integer> hypIDs = new ArrayList<Integer>();
        for (Map.Entry<Integer, List<String>> synEntry : synsets.entrySet()) {
            Integer synKey = synEntry.getKey();
            List<String> synValue = synEntry.getValue();
            for (int i = 0; i < synValue.size(); i++) {
                if (synValue.contains(word)) {
                    synID.add(synKey);
                }
                hypIDs = getHyponyms(synID);
                for (int hyp : hypIDs) {
                    List<String> synList = synsets.get(hyp);
                    for (String syn : synList) {
                        nounSet.add(syn);
                    }
                }
            }
        }
        return nounSet;
    }
}

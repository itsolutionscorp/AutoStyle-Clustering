package ngordnet;

import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;


public class WordNet {
    private String synFile;
    private String hypFile;
    private HashMap<Integer, String> synidList; 
    private HashMap<String, HashSet<Integer>> synwordList;
    private Digraph hypList; 
    private In syn;
    private In hyp;
    public WordNet(String synsetFilename, String hyponymFilename) {
        synFile = synsetFilename;
        hypFile = hyponymFilename;
        synidList = new HashMap<Integer, String>(); 
        synwordList = new HashMap<String, HashSet<Integer>>();
        syn = new In(synFile);
        hyp = new In(hypFile);
        while (syn.hasNextLine()) {
            String files = syn.readLine();
            if (files.isEmpty()) {
                files = syn.readLine();
            }
            String[] a = files.split(",");
            int sid = Integer.parseInt(a[0]);
            String word = a[1];
            String[] parsedWord = word.split("\\s+");
            int i = 0;
            while (i < parsedWord.length) {
                String wordKey = parsedWord[i];
                if (synwordList.containsKey(wordKey)) {
                    synwordList.get(wordKey).add(sid);
                } else if (!synwordList.containsKey(wordKey)) {
                    synwordList.put(wordKey, new HashSet<Integer>());
                    synwordList.get(wordKey).add(sid);
                }
                i += 1;
            }
            if (!synidList.containsKey(sid)) {
                synidList.put(sid, word);
            }
        } 

        hypList = new Digraph(synidList.size());
        while (hyp.hasNextLine()) {
            String files = hyp.readLine(); 
            String[] a = files.split(",");
            int anchor = Integer.parseInt(a[0]);
            int i = 1;
            while (i < a.length) {
                if (a[i] != null) {
                    hypList.addEdge(anchor, Integer.parseInt(a[i])); 
                    i += 1;
                }
            }
        }
    }

    private Set<Integer> idList;
    private Set<String> wordList;
    public boolean isNoun(String input) {
        return (synwordList.containsKey(input));
    }

    public Set<String> nouns() {
        wordList = new HashSet<String>();
        Set<String> nouns = synwordList.keySet();
        return nouns;
    }

    public Set<String> hyponyms(String input) {
        idList = GraphHelper.descendants(hypList, synwordList.get(input));
        wordList = new HashSet<String>();
        for (Integer elem : idList) {
            String longWord = synidList.get(elem); 
            String[] addWord = longWord.split("\\s+");
            int i = 0;
            while (i < addWord.length) {
                wordList.add(addWord[i]);
                i += 1;
            }
        }
        return wordList;
    }
}   



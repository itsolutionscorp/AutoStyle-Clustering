package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Integer[] idArray, hyponymsId;
    private HashMap<Integer, String[]> idToWords; 
    private HashMap<String, ArrayList<Integer>> wordToId;
    private In synfile, hypfile;
    private Digraph graph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synfile = new In(synsetFilename);
        In copySynFile = new In(synsetFilename);
        hypfile = new In(hyponymFilename);
        idToWords = new HashMap<Integer, String[]>();
        wordToId = new HashMap<String, ArrayList<Integer>>();

        while (synfile.hasNextLine()) {
            String synLine = synfile.readLine();
            String[] synParts = synLine.split(",");
            String[] synWords = (synParts[1].split(" "));
            String[] wordsPerSynset = new String[synWords.length];
            Integer synId = Integer.parseInt(synParts[0]);

            for (int j = 0; j < synWords.length; j++) {
                wordsPerSynset[j] = synWords[j];
            }
            idToWords.put(synId, wordsPerSynset);
        } //now have a map of id -> words.

        while (copySynFile.hasNextLine()) {
            String synLine2 = copySynFile.readLine();
            String[] synParts2 = synLine2.split(",");
            String[] synWords2 = (synParts2[1]).split(" ");
            Integer synId2 = Integer.parseInt(synParts2[0]);

            for (int k = 0; k < synWords2.length; k++) {
                String eachWord = synWords2[k];
                if (wordToId.containsKey(eachWord)) {
                    ArrayList<Integer> newIds = (wordToId.get(eachWord));
                    newIds.add(synId2);
                    wordToId.put(eachWord, newIds);
                }   else {
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    ids.add(synId2);
                    wordToId.put(eachWord, ids);

                }

            }

        } //now have a map of word -> id.
    
        graph = new Digraph(idToWords.size());
        while ((hypfile.hasNextLine())) {
            String hynLine = hypfile.readLine();
            String[] hynWords = hynLine.split(",");
            Integer hyper = Integer.parseInt(hynWords[0]);
            for (int i = 1; i < hynWords.length; i++) {
                Integer x = Integer.parseInt(hynWords[i]);
                graph.addEdge(hyper, x);
            }
        } //digraph for hymonyms 
    }

    public boolean isNoun(String  noun) {
        return wordToId.containsKey(noun);
    }

    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            throw new IllegalArgumentException("is not a noun");
        }
        Set<String> theReturn = new TreeSet<String>();
        ArrayList<Integer> wordsIds = new ArrayList<Integer>();
        wordsIds = wordToId.get(word);
        Set<Integer> idset = new TreeSet<Integer>();
        for (int id: wordsIds) {
            idset.add(id);
        }
        Set<Integer> descendents = GraphHelper.descendants(graph, idset);
        for (int id: descendents) {
            String[] words = idToWords.get(id);
            for (int i = 0; i < words.length; i++) {
                theReturn.add(words[i]);
            }
        } return theReturn;
    }   


    public Set<String> nouns() {
        return wordToId.keySet();

    }


}

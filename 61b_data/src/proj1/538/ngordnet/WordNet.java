package ngordnet;

import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Set<String> nouns = new HashSet<String>();
    private HashMap<Integer, List<String>> synsetMap = new HashMap<Integer, List<String>>();
    private HashMap<Integer, int[]> hyponymMap = new HashMap<Integer, int[]>();
    private Digraph di;
    private int vertices = 0; 

    public WordNet(String synsets, String hyponyms) { // set path names for reading
        this.readFiles(synsets, hyponyms); // loads synsets and hypernyms
    }

    public Set<String> nouns() {
        return nouns;
    }

    public boolean isNoun(String word) {
        return nouns.contains(word);
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymsAndSynonyms = new HashSet<String>();
        Iterator entries = synsetMap.keySet().iterator();

        while (entries.hasNext()) {
            int synonymIds = (int) entries.next();
            if (synsetMap.get(synonymIds).contains(word)) {
                for (int i = 0; i < synsetMap.get(synonymIds).size(); i += 1) {
                    hyponymsAndSynonyms.add(synsetMap.get(synonymIds).get(i));
                }
            }
        }
        for (int key : hyponymMap.keySet()) {
            int[] hyponymsArray = hyponymMap.get(key);
            List<String> synonymNames = synsetMap.get(key);
            if (synonymNames.contains(word)) {
                Set<Integer> allHypo = new HashSet<Integer>();
                for (int y = 0; y < hyponymsArray.length; y += 1) {
                    allHypo.add(hyponymsArray[y]);
                }
                Set<Integer> allDescendants = GraphHelper.descendants(this.di, allHypo);
                Iterator d = allDescendants.iterator();
                while (d.hasNext()) {
                    List<String> name = synsetMap.get(d.next());
                    for (int r = 0; r < name.size(); r += 1) {
                        hyponymsAndSynonyms.add(name.get(r));
                    }
                }
            }      
        }
        return hyponymsAndSynonyms;
    }

    private void readFiles(String synsets, String hyponyms) { // loads all synsets
        In synReader = new In(synsets);
        String tempLine = "";
        String[] splitLine;
        String[] synonyms;
        while (synReader.hasNextLine()) {
            this.vertices += 1; // add a vertex for every line
            tempLine = synReader.readLine();
            splitLine = tempLine.split(",");
            synonyms = splitLine[1].split(" "); // splits all synonyms
            ArrayList<String> listSynonyms = new ArrayList<String>();
            for (int x = 0; x < synonyms.length; x += 1) { // iterate through contains
                nouns.add(synonyms[x]); // add each to nouns
                listSynonyms.add(synonyms[x]);
            }
            synsetMap.put(Integer.parseInt(splitLine[0]), listSynonyms);
        }
        di = new Digraph(this.vertices); // make a new digraph with vertices
        In hypoReader = new In(hyponyms);
        while (hypoReader.hasNextLine()) {
            tempLine = hypoReader.readLine();
            splitLine = tempLine.split(",");
            int[] hyponymIds = new int[splitLine.length - 1];
            for (int i = 1; i < splitLine.length; i += 1) {
                di.addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[i]));
                hyponymIds[i - 1] = Integer.parseInt(splitLine[i]); 
            }
            hyponymMap.put(Integer.parseInt(splitLine[0]), hyponymIds);
        }
    }
}

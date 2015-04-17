package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private int vertices;
    private HashSet<String> nounset;
    private HashSet<String> tempset;
    private HashSet<Integer> hyposet;
    private HashSet<Integer> idset;
    private HashMap<Integer, HashSet<String>> idmap; //ID and synset
    private HashMap<Integer, HashSet<Integer>> hypomap; //ID and hypo's id

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hypo = new In(hyponymFilename);
        nounset = new HashSet<String>();
        idmap = new HashMap<Integer, HashSet<String>>();
        hypomap = new HashMap<Integer, HashSet<Integer>>();
        int max = -1;

        // Syn
        while (syn.hasNextLine()) {
            tempset = new HashSet<String>();
            String synLine = syn.readLine();
            String[] synParts = synLine.split(",");
            String synPart1 = synParts[0];  //ID
            String synPart2 = synParts[1];  //Synonym Set
            //String synPart3 = synParts[2];    //Definition
    
            int synID = Integer.parseInt(synPart1); //Turn it into a integer
            if (synID > max) {
                max = synID;
            }   //Get vertices
            String[] synonyms = synPart2.split(" ");
            for (int i = 0; i < synonyms.length; i++) {
                nounset.add(synonyms[i]); //Put it into noun table
                tempset.add(synonyms[i]); //Put it into Synonym Set
            }
            idmap.put(synID, tempset);  //Create a map
        }
        vertices = max + 1;

        // Hypo
        while (hypo.hasNextLine()) {
            hyposet = new HashSet<Integer>();
            String hypoLine = hypo.readLine();
            String[] hypoParts = hypoLine.split(",");

            int hypoID = Integer.parseInt(hypoParts[0]);

            for (int i = 1; i < hypoParts.length; i++) {
                int n = Integer.parseInt(hypoParts[i]);
                hyposet.add(n);
            }
            if (hypomap.containsKey(hypoID)) {
                hypomap.get(hypoID).addAll(hyposet);
            } else {
                hypomap.put(hypoID, hyposet); //Get ID and hypo ids together
            }
        }
    }

    public boolean isNoun(String noun) {
        return (nounset.contains(noun));
    }

    public Set<String> nouns() {
        return nounset;
    }

    public Set<String> hyponyms(String word) {
        idset = new HashSet<Integer>();
        Digraph g = new Digraph(vertices);
        HashSet<String> result = new HashSet<String>();
        for (Integer id : idmap.keySet()) {
            if (idmap.get(id).contains(word)) {
                idset.add(id);
            }
        }

        for (Integer id : hypomap.keySet()) {
            Set<Integer> temp = hypomap.get(id);
            for (int n : temp) {
                g.addEdge(id, n);
            }
        }

        Set<Integer> descend = GraphHelper.descendants(g, idset);
        for (int n : descend) {
            for (String str : idmap.get(n)) {
                result.add(str);
            }
        }
        return result;
    } 
}

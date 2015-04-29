package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordNet {

    private TreeMap<Integer, List<String>> map;
    private Set<String> uniqueNouns;
    private Digraph hyponymGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        map = new TreeMap<Integer, List<String>>();
        uniqueNouns = new TreeSet<String>();

        while (!synsetIn.isEmpty()) {
            String nextLine = synsetIn.readLine();

            //separate number from rest of string
            String numberString = nextLine.substring(0, nextLine.indexOf(','));
            String restString = nextLine.substring(nextLine.indexOf(',') + 1);

            int intKey = Integer.parseInt(numberString);

            //split restString into its synonym(s) and definition
            String synonyms = restString.substring(0, restString.indexOf(','));
            String definition = restString.substring(restString.indexOf(',') + 1);

            String[] splitSynonyms = synonyms.split(" ");

            ArrayList<String> stringVal = new ArrayList<String>();

            for (int i = 0; i < splitSynonyms.length; i++) {
                stringVal.add(splitSynonyms[i]);
                uniqueNouns.add(splitSynonyms[i]);
            }
            stringVal.add(definition);

            map.put(intKey, stringVal);
        }

        hyponymGraph = new Digraph(map.size());

        while (!hyponymIn.isEmpty()) {
            String nextLine = hyponymIn.readLine();
            String[] withoutCommas = nextLine.split(",");

            for (int i = 0; i < withoutCommas.length; i++) {
                int firstVertex = Integer.parseInt(withoutCommas[0]);
                int secondVertex = Integer.parseInt(withoutCommas[i]);
                hyponymGraph.addEdge(firstVertex, secondVertex);
            }
        }
        //hyponymGraph completed
    }

    public boolean isNoun(String noun) {
        //return false;
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        //return null;
        return uniqueNouns;
    }

    private void addVertexNumbers(String word, Set<Integer> num) {
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).contains(word)) {
                num.add(i);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        //return null;

        Set<String> hypos = new TreeSet<String>(); //to store hyponyms of word

        Set<Integer> num = new TreeSet<Integer>(); //to store vertex nums in which word appears
        addVertexNumbers(word, num);

        Set<Integer> hyponymVertices = GraphHelper.descendants(hyponymGraph, num);

        for (int vert: hyponymVertices) {
            for (int i = 0; i < map.get(vert).size() - 1; i++) { //-1 to exclude defn
                hypos.add(map.get(vert).get(i));
            }
        }
        return hypos;
    }
}

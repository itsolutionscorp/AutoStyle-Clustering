package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private HashMap<String, ArrayList<Integer>> ids;
    private HashMap<Integer, ArrayList<String>> reverse;
    private Digraph tree;
    private int numOfIds;

    public WordNet(String synsetFilename, String hyponymFilename) {
        //DO READING HERE
        initSynset(synsetFilename);
        initHyponyms(hyponymFilename);
    }

    private void initSynset(String synsetFilename) {
        In synsetfile = new In(synsetFilename);
        ids = new HashMap<String, ArrayList<Integer>>();
        reverse = new HashMap<Integer, ArrayList<String>>();

        while (synsetfile.hasNextLine()) {
            String line = synsetfile.readLine();
            String[] parts = line.split(",");
            String idNum = parts[0];
            String[] words = parts[1].split(" ");
            String definition = parts[2];
            numOfIds++;

            for (String word: words) {
                if (!ids.containsKey(word)) {
                    ids.put(word, new ArrayList<Integer>());
                }
                ids.get(word).add((Integer.parseInt(idNum)));

                if (!reverse.containsKey(Integer.parseInt(idNum))) {
                    reverse.put(Integer.parseInt(idNum), new ArrayList<String>());
                }
                reverse.get(Integer.parseInt(idNum)).add(word);
            }
        }
    }

    private void initHyponyms(String hyponymFilename) {
        In hyponymfile = new In(hyponymFilename);
        tree = new Digraph(numOfIds);
        while (hyponymfile.hasNextLine()) {
            String list = hyponymfile.readLine();
            String[] hypo = list.split(",");
            int index = 0;
            int parent = Integer.parseInt(hypo[0]);
            for (int i = 1; i < hypo.length; i++) {
                tree.addEdge(parent, Integer.parseInt(hypo[i]));
            }
        }
    }


    public boolean isNoun(String noun) {
        return ids.containsKey(noun);

    }

    public Set<String> nouns() {
        return ids.keySet();

    }

    public Set<String> hyponyms(String word) {
        ArrayList<Integer> idNum = ids.get(word);
        Set<Integer> foo = new HashSet<Integer>(idNum);
        Set<Integer> descends = GraphHelper.descendants(tree, foo);
        Set<String> returningThis = new HashSet<String>();
        returningThis.add(word);
        for (int id : descends) {
            for (String blah: reverse.get(id)) {
                returningThis.add(blah);
            }
             
        }
        return returningThis;
        // return null;
    }


}

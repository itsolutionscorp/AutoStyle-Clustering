package ngordnet;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.ArrayList;

public class WordNet {

    private ArrayList<Synset> synsetsSet; /*I assume that we don't add a synset 
                                            to this data structure twice. Avoid 
                                            implementing comparable in synset. */
    private TreeSet<String> nounsSet; //I dont't need this but it makes isNoun and nouns easier
    private Digraph dg;

    public WordNet(String syn, String hyp) {
        synsetsSet = new ArrayList<Synset>();
        nounsSet = new TreeSet<String>();
        In inSyn = new In(syn);
        int lineCount = 0;
        while (inSyn.hasNextLine()) {
            String newLine = inSyn.readLine();
            String[] splittedLine = newLine.split(",");
            String[] words = splittedLine[1].split(" ");
            for (String word : words) {
                nounsSet.add(word);
            }
            Synset v = new Synset(Integer.parseInt(splittedLine[0]), words, splittedLine[2]);
            lineCount++;
            synsetsSet.add(v);
        }
        dg = new Digraph(lineCount);
        In inHyp = new In(hyp);
        while (inHyp.hasNextLine()) {
            String newLine = inHyp.readLine();
            String[] splittedLine = newLine.split(",");
            int hypernym = Integer.parseInt(splittedLine[0]);
            for (int i = 1; i < splittedLine.length; i++) {
                int hyponym = Integer.parseInt(splittedLine[i]);
                dg.addEdge(hypernym, hyponym);
            }
        }
    }

    public boolean isNoun(String noun) {
        if (nounsSet.contains(noun)) {
            return true;
        }
        return false;
    }
    
    public Set<String> nouns() {
        return nounsSet;

    }

    public Set<String> hyponyms(String noun) {
        TreeSet<String> hyponymSet = new TreeSet<String>();
        TreeSet<Integer> idSet = new TreeSet<Integer>();
        for (Synset tempSynset : synsetsSet) {
            for (String tempNoun : tempSynset.strings) {
                if (tempNoun.equals(noun)) {
                    idSet.add(tempSynset.id);
                }
            }
        }
        
        Set<Integer> hyponymIdSet = GraphHelper.descendants(dg, idSet);
        for (Integer integer : hyponymIdSet) {
            for (Synset tempSynset : synsetsSet) {
                if (integer == tempSynset.id) {
                    for (String hyponym : tempSynset.strings) {
                        hyponymSet.add(hyponym);
                    }
                }
            }
        }
        
        return hyponymSet;
    }

    private class Synset {
        private String[] strings;
        private int id;
        private String definition;

        public Synset(int i, String[] s, String d) {
            id = i;
            strings = s;
            definition = d;
        }

    }

}

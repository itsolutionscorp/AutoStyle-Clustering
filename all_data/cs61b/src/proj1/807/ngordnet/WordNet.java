package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


public class WordNet {
    private Digraph diagraph;
    private int id;
    private int size = 0;
    private In reader;
    private String helperstr;
    private String[] wordlist;
    private Set<String> nounslist = new HashSet<String>();
    private WordSet<Integer, String> wordset = new WordSet<Integer, String>();
    public WordNet(String synsetFilename, String hyponymFilname) {
        reader = new In(synsetFilename);
        while (reader.hasNextLine()) { 
            helperstr = reader.readLine();
            wordlist = helperstr.split(",");
            wordset.put(Integer.parseInt(wordlist[0]), wordlist[1], wordlist[1].split(" "));
            String[] multipleHyponyms = wordlist[1].split(" ");
            for (int i = 0; i < multipleHyponyms.length; i++) {
                nounslist.add(multipleHyponyms[i]);
                size++;
            }
        }
        diagraph = new Digraph(size);
        In hyponymreader = new In(hyponymFilname);
        while (hyponymreader.hasNextLine()) {
            helperstr = hyponymreader.readLine();
            wordlist = helperstr.split(",");
            for (int i = 1; i < wordlist.length; i++) {
                diagraph.addEdge(Integer.parseInt(wordlist[0]), Integer.parseInt(wordlist[i]));
            }
        }
    }
    public boolean isNoun(String noun) {
        if (nounslist.contains(noun)) {
            return true;
        }
        return false;
    }
    public Set<String> nouns() {
        return nounslist; 
    }
    public Set<String> hyponyms(String word) {
        Set<Integer> idhyponymlist = new HashSet<Integer>();
        Iterator<Integer> multipleIdIter = wordset.getId(word).iterator();
        while (multipleIdIter.hasNext()) {
            idhyponymlist.add(multipleIdIter.next());
        }
        Set<Integer> descendants = GraphHelper.descendants(diagraph, idhyponymlist);
        Iterator<Integer> descendantsIter = descendants.iterator();
        Set<String> allHyponyms = new HashSet<String>();
        while (descendantsIter.hasNext()) {
            String [] descedantList = wordset.get(descendantsIter.next()).split(" ");
            for (int i = 0; i < descedantList.length; i++) {
                allHyponyms.add(descedantList[i]);
            }
        }
        return allHyponyms;
    }
}

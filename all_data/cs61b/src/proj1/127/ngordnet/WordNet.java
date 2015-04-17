package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private HashMap<Integer, Word> nouns;
    private In synsetScan, hyponymScan;
    private Digraph g;
    private int count;
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetScan = new In(synsetFilename);
        hyponymScan = new In(hyponymFilename);
        nouns = new HashMap<Integer, Word>();
        count = 0;
        //Read synsetfiles
        while (synsetScan.hasNextLine()) { 
            //read in the synset files. Create a hashmap keyed by ID and value Word
            String line = synsetScan.readLine();
            int identify = Integer.parseInt(line.substring(0, line.indexOf(",")));
            line = line.substring(line.indexOf(",") + 1);
            String words = line.substring(0, line.indexOf(","));
            line = line.substring(line.indexOf(",") + 1);
            String def = line;
            nouns.put(identify, new Word(identify, words, def));
            count++;
        }
        
        g = new Digraph(count);
   
        while (hyponymScan.hasNextLine()) { // add all the edges
            String seq = hyponymScan.readLine();
            int base = Integer.parseInt(seq.substring(0, seq.indexOf(",")));
            int connect;
            seq = seq.substring(seq.indexOf(",") + 1);
            while (seq.contains(",")) {
                connect = Integer.parseInt(seq.substring(0, seq.indexOf(",")));
                seq = seq.substring(seq.indexOf(",") + 1);
                g.addEdge(base, connect);
            }
            g.addEdge(base, Integer.parseInt(seq));
        }
    }

    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Word w: nouns.values()) {
            for (String phrase: w.getWord()) {
                if (phrase.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (Word w: nouns.values()) {
            for (String phrase: w.getWord()) {
                allNouns.add(phrase);   
            }
        }
        return allNouns;
    }

    public Set<String> hyponyms(String someWord) {
        HashSet<Integer> baseID = new HashSet<Integer>();
        for (Word w: nouns.values()) { // creates a set of IDs containing the word
            for (String phrase: w.getWord()) {
                if (phrase.equals(someWord)) {
                    baseID.add(w.getID());
                }
            }
        }
        Set<Integer> descendID = GraphHelper.descendants(g, baseID);
        HashSet<String> allHyponyms = new HashSet<String>();
        for (int x: descendID) {
            for (String s : nouns.get(x).getWord()) {
                allHyponyms.add(s);
            }
        }
        return allHyponyms;
    }

    private class Word {
        private int id;
        private String meaning;
        private HashSet<String> phrase;
        public Word(int ids, String words, String mean) {
            this.id = ids;
            int step = 0;
            phrase = new HashSet<String>();
            while (words.contains(" ")) { //extract all the words
                phrase.add(words.substring(0, words.indexOf(" ")));
                words = words.substring(words.indexOf(" ") + 1);
                step += 1;
            }
            phrase.add(words);   
            this.meaning = mean;
        }
        public int getID() {
            return id;
        }
        public HashSet<String> getWord() {
            return phrase;
        }
        public String getDef() {
            return meaning;
        }
    }
}

package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.util.Iterator;

public class WordNet {
    private Digraph d;
    private ArrayList<String[]> synsetList;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);

        int count = 0;
        synsetList = new ArrayList<String[]>();
        while (synset.hasNextLine()) {
            // Kak Wong piazza comment on how to split using comma
            String s = synset.readLine();
            String[] split = s.split(",");
            synsetList.add(split);
            count += 1;

        }

        d = new Digraph(count);
        while (hyponym.hasNextLine()) {
            String h = hyponym.readLine();
            String[] id = h.split(",");
            int current = Integer.parseInt(id[0]);
            for (int i = 1; i < id.length; i++) {
                d.addEdge(current, Integer.parseInt(id[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        //How to split whitespace "\\s+" from stack overflow
        //http://stackoverflow.com/questions/225337/
        //how-do-i-split-a-string-with-any-whitespace-chars-as-delimiters
        String[] split = noun.split("\\s+");
        if (split.length > 1) {
            return false;
        }
        for (int i = 0; i < synsetList.size(); i++) {
            String words = synsetList.get(i)[1];
            if (words.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> wordSet = new TreeSet<String>();
        for (int i = 0; i < synsetList.size(); i++) {
            String words = synsetList.get(i)[1];
            String[] wordList = words.split("\\s+");
            for (int j = 0; j < wordList.length; j++) {
                wordSet.add(wordList[j]);
            }
        }
        return wordSet;
    }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<Integer> numbers = new TreeSet<Integer>();
        for (int i = 0; i < synsetList.size(); i++) {
            String words = synsetList.get(i)[1];
            if (words.contains(word)) {
                numbers.add(i);
            }
        }
        
        Set<Integer> hyps = GraphHelper.descendants(d, numbers);
        Iterator<Integer> iterator = hyps.iterator();
        Set<String> wordSet = new TreeSet<String>();
        while (iterator.hasNext()) {
            String w = synsetList.get(iterator.next())[1];
            String[] wList = w.split("\\s+");
            for (int j = 0; j < wList.length; j++) {
                wordSet.add(wList[j]);
            }
        }
        return wordSet;
    }
}

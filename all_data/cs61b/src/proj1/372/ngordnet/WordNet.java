package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    
    private HashMap numbertoWords;
    private HashMap wordstoNumber;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {
        numbertoWords = new HashMap();
        wordstoNumber = new HashMap();
        In file = new In(synsetFilename);
        while (file.hasNextLine()) {
            String curLine = file.readLine();
            String[] split = curLine.split(",");       
            numbertoWords.put(split[0], split[1]);
            String[] splitWords = split[1].split(" ");
            for (int i = 0; i < splitWords.length; i++) {
                if (wordstoNumber.containsKey(splitWords[i])) {
                    String newVal = wordstoNumber.get(splitWords[i]) + "," + split[0];
                    wordstoNumber.put(splitWords[i], newVal);
                } else {
                    wordstoNumber.put(splitWords[i], split[0]);
                }
            }
        }
        In file2 = new In(hyponymFilename);
        hyponyms = new Digraph(wordstoNumber.size());
        while (file2.hasNextLine()) {
            String curLine2 = file2.readLine();
            String[] split2 = curLine2.split(",");
            for (int i = 1; i < split2.length; i++) {
                hyponyms.addEdge(Integer.parseInt(split2[0]), Integer.parseInt(split2[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordstoNumber.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordstoNumber.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> set = new TreeSet<String>();
        Set<Integer> vertice = new TreeSet<Integer>();
        String number = (String) wordstoNumber.get(word);
        String[] numbers = number.split(",");
        for (int p = 0; p < numbers.length; p++) {
            vertice.add(Integer.parseInt(numbers[p]));
        }
        Set<Integer> graphwords = GraphHelper.descendants(hyponyms, vertice);
        for (Integer n : graphwords) {
            String words = (String) numbertoWords.get(Integer.toString(n));
            if (words != null) {
                String[] stringSplit = words.split(",");
                for (int i = 0; i < stringSplit.length; i++) {
                    String[] wordsSplit = stringSplit[i].split(" ");
                    for (int k = 0; k < wordsSplit.length; k++) {
                        set.add(wordsSplit[k]);
                    }
                }   
            }
        }
        return set;
    }
}

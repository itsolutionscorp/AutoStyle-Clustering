package ngordnet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private ArrayList<Dictionary> words;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * @throws FileNotFoundException */
    public WordNet(String synsetFilename, String hyponymFilename) {
        words = new ArrayList<Dictionary>();

        In f = new In(synsetFilename);

        /*Go through each word and add it to the list dictionary.*/
        while (f.hasNextLine()) {
            String line = f.readLine();
            String[] content = line.split(",");
            String[] syns = content[1].split(" ");
            words.add(new Dictionary(syns, content[2]));
        }
        f.close();

        In f2 = new In(hyponymFilename);
        /*Add hyponyms.*/
        while (f2.hasNextLine()) {
            String line = f2.readLine();
            String[] content = line.split(",");
            int index = Integer.parseInt(content[0]);
            String[] hyponyms = new String[content.length - 1];
            for (int k = 1; k < content.length; k++) {
                hyponyms[k - 1] = content[k];
            }
            /*Add hyponyms to strict location.*/
            words.get(index).addHyponyms(hyponyms);
        }
        f2.close();
        System.out.println(words.size());

    }
    /* Creates a Dictionary that stores the word and its definition */
    private class Dictionary {
        String[] words;
        String def;

        /*Keep in mind these are the number references, not words.*/
        ArrayList<String> hyponyms;

        public Dictionary(String[] wordz, String defz) {
            this.words = wordz;
            this.def = defz;
            hyponyms = new ArrayList<String>();
        }

        /*Adds a list of hyponyms to our existing list.*/
        public void addHyponyms(String[] hyps) {
            for (String elem: hyps) {
                if (!hyponyms.contains(elem)) {
                    hyponyms.add(elem);
                }
            }
        }

        public boolean contains(String word) {
            for (String elem: words) {
                if (elem.equals(word)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Dictionary elem: words) {
            if (elem.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Dictionary element: words) {
            for (String elem: element.words) {
                nouns.add(elem);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> nouns = new HashSet<String>();
        for (Dictionary element: words) {
            if (element.contains(word)) {
                for (String syn: element.words) {
                    nouns.add(syn);
                }
                for (String hyp: element.hyponyms) {
                    for (String elem: getHyponyms(Integer.parseInt(hyp))) {
                        nouns.add(elem);
                    }
                }
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms that belong to the word at
      * given code, including itself.
      */
    private Set<String> getHyponyms(int code) {
        Set<String> nouns = new HashSet<String>();
        Dictionary word = words.get(code);
        for (String syn: word.words) {
            nouns.add(syn);
        }
        for (String hyp: word.hyponyms) {
            for (String elem: getHyponyms(Integer.parseInt(hyp))) {
                nouns.add(elem);
            }
        }
        return nouns;
    }
}

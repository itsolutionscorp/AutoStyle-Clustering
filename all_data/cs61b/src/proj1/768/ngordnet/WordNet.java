package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class WordNet {
    private SynsetList synsets;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        synsets = new SynsetList();

        while (synsetIn.hasNextLine()) {
            String[] data = synsetIn.readLine().split(",");
            String[] synonyms = data[1].split(" ");
            Synset s = new Synset(Integer.parseInt(data[0]), synonyms);
            synsets.addSynset(s);
        }

        while (hyponymIn.hasNextLine()) {
            String[] data = hyponymIn.readLine().split(",");
            Synset hypernym = synsets.get(Integer.parseInt(data[0]));
            for (int i = 1; i < data.length; i++) {
                Synset hyponym = synsets.get(Integer.parseInt(data[i]));
                hypernym.hyponyms.addSynset(hyponym);
                hyponym.hypernyms.addSynset(hypernym);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> result = new TreeSet<String>();
        for (Iterator<Synset> i = synsets.iterator(); i.hasNext();) {
            for (String str : i.next().synonyms) {
                result.add(str);
            }   
        }
        return result;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>();

        //find all synsets containing the word WORD
        SynsetList containsWord = new SynsetList();
        for (Iterator<Synset> i = synsets.iterator(); i.hasNext();) {
            Synset s = i.next();
            for (String str : s.synonyms) {
                if (str.equals(word)) {
                    containsWord.addSynset(s);
                    break; //break loop and go to next synset
                }               
            }
        }

        for (Iterator<Synset> i = containsWord.iterator(); i.hasNext();) {
            Synset s = i.next();

            //add all hyponyms
            result.addAll(findAllHyponymWords(s));
            
            //add all synonyms
            for (String synonym : s.synonyms) {
                result.add(synonym);
            }
        }

        return result;
    }

    private Set<String> findAllHyponymWords(Synset s) {
        if (s == null) {
            return null;
        } else if (s.hyponyms == null) {
            return null;
        } else {
            Set<String> result = new TreeSet<String>();
            for (Iterator<Synset> i = s.hyponyms.iterator(); i.hasNext();) {
                Synset syn = i.next();
                for (String str : syn.synonyms) {
                    result.add(str);
                }
                
                Set<String> recurresult = findAllHyponymWords(syn);
                if (recurresult != null) {
                    result.addAll(recurresult);
                }
            }
            return result;
        }
    }

    private class Synset {
        private SynsetList hypernyms; //parent
        private SynsetList hyponyms; //child
        private String[] synonyms; //words and collocations
        private int id;

        private Synset(int idval, String[] synonymsval) {
            id = idval;
            synonyms = synonymsval;
            this.hypernyms = new SynsetList();
            this.hyponyms = new SynsetList();
        }
    }

    private class SynsetList implements Iterable<Synset> {
        private Synset[] list;
        private int occupants;

        private SynsetList() {
            list = new Synset[100];
            occupants = 0;
        }

        private void addSynset(Synset ele) {
            if (occupants + 1 == list.length) {
                Synset[] resizelist = new Synset[list.length + 100];
                for (int i = 0; i < occupants; i++) {
                    resizelist[i] = list[i];
                }
                list = resizelist;
            }
            list[occupants] = ele;
            occupants++;
        }

        private Synset get(int id) {
            for (int i = 0; i < occupants; i++) {
                if (list[i].id == id) {
                    return list[i];
                }
            }
            return null;
        }

        public Iterator<Synset> iterator() {
            return new SynsetListIterator();
        }

        private class SynsetListIterator implements Iterator {
            private int currPos;

            private SynsetListIterator() {
                currPos = 0;
            }

            public Synset next() {
                Synset s = list[currPos];
                currPos++;
                return s;
            }

            public boolean hasNext() {
                if (list[currPos] == null) {
                    return false;
                }
                return true;
            }

            public void remove() {
                throw new UnsupportedOperationException("CHALLENGE NOT EXCEPTED!");
            }
        }
    }
}

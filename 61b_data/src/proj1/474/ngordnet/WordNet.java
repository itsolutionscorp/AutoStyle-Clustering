package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/** WordNet
 *  @author Ian MacGregor
 */

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph wGraph;
    private Map<Integer, Word> wMap;
    private Map<String, Set<Integer>> wMapInv;
    private Set<String> wSet;

    public WordNet(String synsetFilename, String hyponymFilename) {

        try {
            this.wMap = new HashMap<Integer, Word>();
            this.wMapInv = new HashMap<String, Set<Integer>>();
            this.wSet = new HashSet<String>();

            // Parsing through synsetFilename
            Integer id = 0;
            String[] syns;
            String gloss;
            String[] wordSyn;
            String line = "";
            BufferedReader br1 = new BufferedReader(new FileReader(synsetFilename));
            while ((line = br1.readLine()) != null) {
                wordSyn = line.split(",");
                id = Integer.parseInt(wordSyn[0]);
                syns = wordSyn[1].split(" ");
                gloss = wordSyn[2];
                this.wMap.put(id, new Word(id, syns, gloss));
                for (String syn : syns) {
                    this.wSet.add(syn);
                    if (wMapInv.containsKey(syn)) {
                        this.wMapInv.get(syn).add(id);
                    } else {
                        Set<Integer> idSet = new HashSet<Integer>();
                        idSet.add(id);
                        this.wMapInv.put(syn, idSet);
                    }
                }
            }

            // Parsing through hyponymFilename
            String[] hypoArr;
            Integer base;
            Integer graphSize = id + 1;
            this.wGraph = new Digraph(graphSize);
            BufferedReader br2 = new BufferedReader(new FileReader(hyponymFilename));
            while ((line = br2.readLine()) != null) {
                hypoArr = line.split(",");
                base = Integer.parseInt(hypoArr[0]);
                for (int i = 1; i < hypoArr.length; i += 1) {
                    this.wGraph.addEdge(base, Integer.parseInt(hypoArr[i]));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hypos = new HashSet<String>();
        if (isNoun(word)) {
            for (Integer id : wMapInv.get(word)) {
                Set<Integer> ints = new HashSet<Integer>();
                ints.add(id);
                Set<Integer> setInts = GraphHelper.descendants(wGraph, ints);
                for (Integer i : setInts) {
                    ints.add(i);
                }
                for (Integer i : ints) {
                    Word w = wMap.get(i);
                    for (String wSyn : w.synonyms()) {
                        hypos.add(wSyn);
                    }
                }
            }
        }
        return hypos;
    }

    public class Word {
        private Integer id;
        private String[] synonyms;
        private String gloss;

        public Word(Integer i, String[] s, String g) {
            this.id = i;
            this.synonyms = s;
            this.gloss = g;
        }

        public Integer id() {
            return id;
        }

        public String[] synonyms() {
            return synonyms;
        }

        public String gloss() {
            return gloss;
        }
    }
}

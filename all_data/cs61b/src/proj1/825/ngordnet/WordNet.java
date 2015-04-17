package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private In words;
    private In groupings;
    private In groupingscopy;
    private HashMap wordsdict;
    private Digraph wordnet;
    private Set<String> allkeys;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        words = new In(synsetFilename);
        groupings = new In(hyponymFilename);
        groupingscopy = new In(hyponymFilename);
        wordsdict = new HashMap();
        // finding number of different vertices
        Set<String> vertexlist = new HashSet<String>();
        while (groupingscopy.hasNextLine()) {
            // break each line
            String line = groupingscopy.readLine();
            String[] eachnum = line.split("[,]");
            for (String num : eachnum) {
                vertexlist.add(num); // sets wont add duplicates
            }
        }
        // make digraph with total
        wordnet = new Digraph(vertexlist.size());
        // assemble wordnet
        while (groupings.hasNextLine()) {
            // break each line
            String line = groupings.readLine();
            String[] eachnum = line.split("[,]");
            int[] eachnumI = new int[eachnum.length];
            for (int i = 0; i < eachnum.length; i++) {
                eachnumI[i] = Integer.parseInt(eachnum[i]);
            }
            // add vertices and pointers("edges")
            for (int i = 1; i < eachnumI.length; i++) {
                wordnet.addEdge(eachnumI[0], eachnumI[i]);
            }
        }
        // fill dictionary
        while (words.hasNextLine()) {
            // break each line
            String otherline = words.readLine();
            String[] eachpiece = otherline.split("[,]");
            // if multiple words per key
            if (eachpiece[1].split("[ ]").length > 1) {
                String[] eachword = eachpiece[1].split("[ ]");
                String[] subwordlist = new String[eachword.length];
                for (int i = 0; i < eachword.length; i++) {
                    subwordlist[i] = eachword[i];
                }
                wordsdict.put(eachpiece[0], subwordlist);
            } else {
                wordsdict.put(eachpiece[0], eachpiece[1]); // second param =
                                                           // termdef
            }
        }
        allkeys = wordsdict.keySet();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (wordsdict.containsValue(noun)) {
            return true;
        } else {
            // check through arrays
            allkeys = wordsdict.keySet();
            for (String key : allkeys) {
                if (!((wordsdict.get(key)).getClass()).equals(String.class)) {
                    for (String subs : (String[]) wordsdict.get(key)) {
                        if (subs.equals(noun)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        // goes through lists or adds string
        allkeys = wordsdict.keySet();
        for (String key : allkeys) {
            if (!((wordsdict.get(key)).getClass()).equals(String.class)) {
                for (String subs : (String[]) wordsdict.get(key)) {
                    nounSet.add(subs);
                }
            } else {
                nounSet.add((String) wordsdict.get(key));
            }
        }
        return nounSet;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        // create Set<Integer> for descendants method
        Stack<String> indicators = new Stack<String>();
        Set<String> finalhyponyms = new HashSet<String>();
        allkeys = wordsdict.keySet();
        for (String key : allkeys) {
            if (!((wordsdict.get(key)).getClass()).equals(String.class)) {
                for (String subs : (String[]) wordsdict.get(key)) {
                    if (subs.equals(word)) {
                        indicators.push(key);
                    }
                }
            } else {
                if (wordsdict.get(key).equals(word)) {
                    indicators.push(key);
                }
            }
        }
        HashSet<Integer> descSet = new HashSet<Integer>();
        while (!indicators.isEmpty()) {
            descSet.add(Integer.parseInt(indicators.pop())); // set of only the
                                                             // one vertex we
                                                             // need
        }
        Set<Integer> subInts = GraphHelper.descendants(wordnet, descSet);
        // convert returned Set<Integer> into Set<String> so we can find nouns
        HashSet<String> subStrings = new HashSet<String>(subInts.size());
        for (Integer intt : subInts) {
            subStrings.add(intt.toString());
        }
        // convert substring keys(which are int strings) to noun strings through
        // dict
        for (String s : subStrings) {
            if (!((wordsdict.get(s)).getClass()).equals(String.class)) {
                for (String subs : (String[]) wordsdict.get(s)) {
                    finalhyponyms.add(subs);
                }
            } else {
                finalhyponyms.add((String) wordsdict.get(s));
            }
        }
        finalhyponyms.add(word);
        return finalhyponyms;
    }
}

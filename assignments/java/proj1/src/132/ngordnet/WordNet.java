package ngordnet;

/**
 * Created by riyana on 3/6/2015.
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.List;


public class WordNet {
    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     */

    private In synFile;
    private In hypFile;
    private Digraph digraph;
    private Map<Integer, List<String>> idToNouns = new HashMap<>();
    private Map<String, Set<Integer>> nounsToIds = new HashMap<>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        synFile = new In(synsetFilename);
        hypFile = new In(hyponymFilename);

        String[] synLine;
        while (synFile.hasNextLine()) {
            synLine = synFile.readLine().split(",");
            ArrayList<String> arr = new ArrayList<>(Arrays.asList(synLine[1].split(" ")));
            idToNouns.put(Integer.parseInt(synLine[0]), arr);
        }

        digraph = new Digraph(idToNouns.size());
        String[] hypLine;
        int index;
        while (hypFile.hasNextLine()) {
            hypLine = hypFile.readLine().split(",");
            index = 1;
            while (index < hypLine.length) {
                digraph.addEdge(Integer.parseInt(hypLine[0]), Integer.parseInt(hypLine[index]));
                index++;
            }
        }

        Set<String> nouns = nouns();
        Set<Integer> currNounsToIds = new HashSet<>();
        int currKey;
        for (String noun : nouns) {
            Set<Integer> keys = idToNouns.keySet();
            Iterator<Integer> i = keys.iterator();
            while (i.hasNext()) {
                currKey = i.next();
                for (String s : idToNouns.get(currKey)) {
                    if (s.equals(noun)) {
                        currNounsToIds.add(currKey);
                    }
                }
            }
            nounsToIds.put(noun, currNounsToIds);
            currNounsToIds = new HashSet<>();

        }


    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Collection<List<String>> values = idToNouns.values();
        for (List<String> v : values) {
            for (String s : v) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> retVal = new HashSet<>();
        Set<Integer> temp = GraphHelper.descendants(digraph, idToNouns.keySet());
        for (Integer i : temp) {
            for (String s : idToNouns.get(i)) {
                retVal.add(s);
            }

        }
        return retVal;
    }

    /**
     * Returns the set of all digraph of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all digraph of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include digraph of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> retVal = new HashSet<>();
        Set<Integer> hyponymIds = GraphHelper.descendants(digraph, nounsToIds.get(word));
        Iterator<Integer> idsIter = hyponymIds.iterator();
        int currId;
        List<String> synsetLst;
        while (idsIter.hasNext()) {
            currId = idsIter.next();
            synsetLst = idToNouns.get(currId);
            for (String s : synsetLst) {
                retVal.add(s);
            }
        }
        return retVal;
    }
}

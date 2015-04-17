package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Arrays;


public class WordNet {
    private Digraph diNet;
    private LinkedHashSet<String> synsets;
    private LinkedHashSet<String> nouns;

    public WordNet(String synsetFile, String hyponymFile) {
        synsets = new LinkedHashSet<String>();
        nouns = new LinkedHashSet<String>();
        In synsetStream = new In(synsetFile);
        In hyponymStream = new In(hyponymFile);

        if (synsetStream == null) {
            throw new RuntimeException("Bad Synset File");
        }
        if (hyponymStream == null) {
            throw new RuntimeException("Bad Hyponym File");
        }

        String synsetRead = synsetStream.readLine();
        String[] synset; //the full synset data
        while (synsetRead != null) {
            synset = synsetRead.split(",");
            Set<String> synsetCollection = new HashSet<String>(Arrays.asList(synset));
            //taken from StackOverFlow:
            //http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set

            synsets.add(synsetRead);

            Set<String> nounCollection = new HashSet<String>(Arrays.asList(synset[1].split(" ")));
            //taken from StackOverFlow:
            //http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set
            nouns.addAll(nounCollection);
            synsetRead = synsetStream.readLine();
        }

        diNet = new Digraph(synsets.size());
        String hyponymRead = hyponymStream.readLine();
        String[] hyponymRAW;
        int[] hyponymParsed;
        int hyponymID;
        while (hyponymRead != null) {
            hyponymRAW = hyponymRead.split(","); //splits the hyponym data line into each individual
            // number (still strings)
            hyponymParsed = new int[hyponymRAW.length]; //makes it the appropriate length.
            for (int i = 0; i < hyponymRAW.length; i += 1) {
                hyponymParsed[i] = Integer.parseInt(hyponymRAW[i]);
            }

            hyponymID = hyponymParsed[0];
            for (int i : hyponymParsed) {
                if (i != hyponymID) {
                    diNet.addEdge(hyponymID, i);
                }
            }

            hyponymRead = hyponymStream.readLine();
        }
    }

    public boolean isNoun(String word) {
        return nouns.contains(word);
    }

    public Set<String> nouns() {
        return nouns;
    }


    public Set<String> hyponyms(String word) {
        Set<Integer> locations = located(synsets, word);
        Set<Integer> related = GraphHelper.descendants(diNet, locations);
        Set<String> returnVal = new LinkedHashSet<String>();

        for (int i : related) {
            String[] synset = get(synsets, i).split(",");
            Set<String> nounCollection = new HashSet<String>(Arrays.asList(synset[1].split(" ")));
            //taken from StackOverFlow:
            //http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set
            returnVal.addAll(nounCollection);
        }

        return returnVal;
    }

    private Set<Integer> located(Collection<String> sets, String word) {
        HashSet<Integer> locations = new HashSet<Integer>();
        Iterator<String> iter = sets.iterator();

        while (iter.hasNext()) {
            String curr = iter.next();
            String[] fields = curr.split(",");
            Set<String> words = new HashSet<String>(Arrays.asList(fields[1].split(" ")));
            //taken from StackOverFlow:
            //http://stackoverflow.com/questions/3064423/java-easily-convert-array-to-set

            if (words.contains(word)) {
                locations.add(Integer.parseInt(fields[0]));
            }
        }

        return locations;
    }

    private String get(Collection<String> coll, int i) {
        Iterator<String> iter = coll.iterator();
        while (iter.hasNext()) {
            String curr = iter.next(); //curr's should be
            if (Integer.parseInt(curr.split(",")[0]) == i) {
                return curr;
            }
        }
        return null;
    }
}

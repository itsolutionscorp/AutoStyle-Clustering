package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

public class WordNet {
    /** stores the synset data */
    private HashMap<Integer, ArrayList<String>> synsetMapIntStr;
    private HashMap<String, ArrayList<Integer>> synsetMapStrInt;

    /** stores the hyponym heirarchy */
    private Digraph hyponymDigraph;

    private int vertices;
    private Set<String> allNouns = new HashSet<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        synsetMapIntStr = new HashMap<Integer, ArrayList<String>>();
        synsetMapStrInt = new HashMap<String, ArrayList<Integer>>();
        /** Read the synset file, build the synsetList and make the digraph's vertices */
        In synsetIn = new In(synsetFilename);
        int index = 0;
        while (synsetIn.hasNextLine()) {
            String line = synsetIn.readLine();
            int firstComma = line.indexOf(',');
            line = line.substring(firstComma + 1);
            int secondComma = line.indexOf(',');
            String nouns = line.substring(0, secondComma);
            int space = 0;
            ArrayList<String> nounList = new ArrayList<String>();
            while (space != -1) {
                space = nouns.indexOf(' ');
                if (space == -1) {
                    nounList.add(nouns);
                    allNouns.add(nouns);
                    addToSynsetMapStrInt(nouns, index);
                } else {
                    String noun = nouns.substring(0, space);
                    nounList.add(noun);
                    allNouns.add(noun);
                    addToSynsetMapStrInt(noun, index);
                    nouns = nouns.substring(space + 1);
                }
            }
            synsetMapIntStr.put(index, nounList);
            index += 1;
        }
        vertices = index;
        hyponymDigraph = new Digraph(vertices);

        /** Read the hypernm file, make the digraph's edges */
        In hypernymIn = new In(hypernymFilename);
        while (hypernymIn.hasNextLine()) {
            String line = hypernymIn.readLine();
            int comma = line.indexOf(',');
            String num = line.substring(0, comma);
            int vertex = Integer.parseInt(num);
            while (comma != -1) {
                line = line.substring(comma + 1);
                comma = line.indexOf(',');
                if (comma == -1) {
                    num = line;
                } else {
                    num = line.substring(0, comma);
                }
                int edge = Integer.parseInt(num);
                hyponymDigraph.addEdge(vertex, edge);
            }
        }
    }

    private void addToSynsetMapStrInt(String word, int index) {
        ArrayList<Integer> indexList;
        if (!synsetMapStrInt.containsKey(word)) {
            indexList = new ArrayList<Integer>();
        } else {
            indexList = synsetMapStrInt.get(word);
        }
        indexList.add(index);
        synsetMapStrInt.put(word, indexList);
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> setHyp = new HashSet<String>();
        if (!isNoun(word)) {
            setHyp.add(word);
            return setHyp;
        }
        ArrayList<Integer> listOfVertices = synsetMapStrInt.get(word);
        int length = listOfVertices.size();
        for (int i = 0; i < length; i += 1) {
            int vertex = listOfVertices.get(i);
            setHyp.addAll(wordsAtVertex(vertex));
            setHyp.addAll(wordsAdjacentRecursion(vertex));
        }
        return setHyp;
    }

    private Set<String> wordsAtVertex(int vertex) {
        Set<String> setHyp = new HashSet<String>();
        ArrayList<String> wordsList = synsetMapIntStr.get(vertex);
        int length = wordsList.size();
        for (int i = 0; i < length; i += 1) {
            String word = wordsList.get(i);
            setHyp.add(word);
        }
        return setHyp;
    }

    private Set<String> wordsAdjacentRecursion(int vertex) {
        Set<String> setHyp = new HashSet<String>();
        if (hyponymDigraph.outdegree(vertex) == 0) {
            return setHyp;
        }
        Iterator<Integer> iterHyp = hyponymDigraph.adj(vertex).iterator();
        while (iterHyp.hasNext()) {
            int index = (int) iterHyp.next();
            setHyp.addAll(wordsAtVertex(index));
            setHyp.addAll(wordsAdjacentRecursion(index));
        }
        return setHyp;
    }
}

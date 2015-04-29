package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.Iterator;



public class WordNet {

    private HashMap<String, Set<Integer>> idMap;
    private HashMap<Integer, String[]> wordMap;
    private In synsetReader, hyponymReader;
    private int vertices;
    private Digraph g;
    private String[] strholder, idholder, splitid;
    private int[] intholder;
    private Set<Integer> idList;
    private Set<String> nounList;

    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        //initializes variables
        synsetReader = new In(synsetFilename);
        hyponymReader = new In(hyponymFilename);
        vertices = 0;
        nounList = new TreeSet<String>();
        idMap = new HashMap<String, Set<Integer>>();
        wordMap = new HashMap<Integer, String[]>();

        while (synsetReader.hasNextLine()) {
            //for the digraph
            vertices += 1;

            idholder = synsetReader.readLine().split(",");
            splitid = idholder[1].split(" ");
            for (int j = 0; j < splitid.length; j += 1) {
                if (!idMap.containsKey(splitid[j])) {
                    idList = new TreeSet<Integer>();
                    idList.add(Integer.parseInt(idholder[0]));
                    idMap.put(splitid[j], idList);
                }
                idMap.get(splitid[j]).add(Integer.parseInt(idholder[0]));
            }
            wordMap.put(Integer.parseInt(idholder[0]), splitid);
            for (int i = 0; i < splitid.length; i += 1) {
                nounList.add(splitid[i]);
            }
        }
        //makes the digraph
        g = new Digraph(vertices);
        while (hyponymReader.hasNextLine()) {
            strholder = hyponymReader.readLine().split(",");
            intholder = new int[strholder.length];
            for (int i = 0; i < strholder.length; i += 1) {
                intholder[i] = Integer.parseInt(strholder[i]);
            }
            for (int j = 0; j < intholder.length - 1; j += 1) {
                g.addEdge(intholder[0], intholder[j + 1]);
            }
        }
    }

    public boolean isNoun(String noun) {
        if (nounList.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return nounList;
    }

    public Set<String> hyponyms(String word) {
        if (!idMap.containsKey(word)) {
            return null;
        }
        Set<Integer> hyponymInt = GraphHelper.descendants(g, idMap.get(word));
        Set<String> hyponymSet = new TreeSet<String>();
        for (Iterator<Integer> it = hyponymInt.iterator(); it.hasNext(); ) {
            Integer i = it.next();
            for (int j = 0; j < wordMap.get((int) i).length; j += 1) {
                hyponymSet.add(wordMap.get((int) i)[j]);
            }
        }
        return hyponymSet;
    }
}

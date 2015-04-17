package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class WordNet {
    private In synsetReader;
    private In hyponymReader;
    private ArrayList<HashSet<String>> ids;
    private HashSet<Synset> synsets;
    private Digraph connections;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetReader = new In(synsetFilename);
        hyponymReader = new In(hyponymFilename);
        String[] synsetLines = synsetReader.readAllLines();
        String[] hyponymLines = hyponymReader.readAllLines();
        synsets = new HashSet<Synset>();
        connections = new Digraph(synsetLines.length);
        ids = new ArrayList<HashSet<String>>(synsetLines.length);

        setVertices(synsetLines);
        setConnections(hyponymLines);
    }

    private void setVertices(String[] synsetLines) {
        for (int i = 0; i < synsetLines.length; i += 1) {
            String[] words = synsetLines[i].split(",");
            Synset synset = new Synset(i, words[1].split(" "), words[2]);
            synsets.add(synset);
            ids.add(synset.getWords());
        }
    }

    private void setConnections(String[] hyponymLines) {
        for (String line : hyponymLines) {
            int indexFirst = line.indexOf(",");
            int hyper = Integer.parseInt(line.substring(0, indexFirst));
            String[] hyposStr = line.substring(indexFirst + 1).split(",");
            int[] hypos = new int[hyposStr.length];
            for (int i = 0; i < hyposStr.length; i += 1) {
                hypos[i] = Integer.parseInt(hyposStr[i]);
            }
            for (int hypo : hypos) {
                connections.addEdge(hyper, hypo);
            }
        }
    }

    public boolean isNoun(String noun) {
        for (Synset syn : synsets) {
            if (syn.getWords().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        HashSet<String> all = new HashSet<String>();
        for (Synset syn : synsets) {
            all.addAll(syn.getWords());
        }
        return all;
    }

    public Set<String> hyponyms(String word) {
        //GraphHelper help = new GraphHelper();
        Set<Integer> descIDs = GraphHelper.descendants(connections, findAll(word));
        HashSet<String> hyponyms = new HashSet<String>();
        for (int id : descIDs) {
            hyponyms.addAll(ids.get(id));
        }
        return hyponyms;
    }

    private Set<Integer> findAll(String str) {
        HashSet<Integer> all = new HashSet<Integer>();
        for (Synset syn : synsets) {
            if (syn.hasWord(str)) {
                all.add(syn.getID());
            }
        }
        return all;
    }

    // - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Everything past this line is just testing
    // - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    // public static void main(String[] args) {
    //     WordNet wn = new WordNet("./wordnet/synsets.txt", "./wordnet/hyponyms.txt");
        
    //     // Testing synsets
    //     //System.out.println(wn.isNoun("leap"));
    //     //System.out.println(wn.isNoun("catcatcat"));

    //     // Testing connections
    //     //System.out.println(wn.connections.toString())

    //     System.out.println(wn.hyponyms("entity"));
    // }

    // // Just for testing purposes
    // private void printList(String[] lst) {
    //     StringBuffer buff = new StringBuffer("-");
    //     for (String string : lst) {
    //         buff.append(" ");
    //         buff.append(string);
    //     }
    //     System.out.println(buff.toString());
    // }

}

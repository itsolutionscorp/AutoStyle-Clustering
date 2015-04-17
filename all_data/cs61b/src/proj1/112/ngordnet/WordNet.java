package ngordnet;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;



public class WordNet {
    private String synsetpath;
    private String hyppath;
    private TreeSet<String> nouns = new TreeSet<String>();
    private Digraph struct;
    private int id = 0;
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetpath = synsetFilename;
        hyppath = hyponymFilename;

        In in = new In(synsetpath);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] rawTokens = line.split(",");
            String word = rawTokens[1];
            String[] checkspace = word.split(" ");

            if (checkspace.length > 1) {
                for (int i = 0; i < checkspace.length; i++) {
                    nouns.add(checkspace[i]);
                }
            } else {
                nouns.add(word);
            }
        }

        in = new In(synsetpath);
        while (in.hasNextLine()) { //checking the size
            String line = in.readLine();
            id++;
        }
        struct = new Digraph(id);


        in = new In(hyppath);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] rawTokens = line.split(",");
            int first = Integer.parseInt(rawTokens[0]);
            for (int i = 1; i < rawTokens.length; i++) {
                struct.addEdge(first, Integer.parseInt(rawTokens[i]));
            }
        }




    }



    public boolean isNoun(String noun) {
        return nouns.contains(noun);

    }
    public Set<String> nouns() {
        return nouns;

    }
    public Set<String> hyponyms(String word) {
        In in = new In(synsetpath);
        In in2 = new In(synsetpath);
        In in3 = new In(hyppath);
        In in4 = new In(synsetpath);

        TreeSet<Integer> ids = new TreeSet<Integer>();
        while (in2.hasNextLine()) { //looking for the id the synset
            String line = in2.readLine();
            String[] rawTokens = line.split(",");
            String word2 = rawTokens[1];
            String[] checkspace = word2.split(" ");
            if (checkspace.length > 1) {
                for (int i = 0; i < checkspace.length; i++) {
                    if (checkspace[i].equals(word)) {
                        ids.add(Integer.parseInt(rawTokens[0])); 
                        break;
                    }
                }

            } else {
                if (checkspace[0].equals(word)) {
                    ids.add(Integer.parseInt(rawTokens[0])); 
                }
            }
        }
        TreeSet<Integer> subids = new TreeSet<Integer>();
        subids.addAll(GraphHelper.descendants(struct, ids));

        TreeSet<String> subwords = new TreeSet<String>();

        for (Integer lookup: subids) {
            while (in4.hasNextLine()) { //looking for the id of the words
                String line = in4.readLine();
                String[] rawTokens = line.split(",");
                String word2 = rawTokens[1];
                if (lookup == Integer.parseInt(rawTokens[0])) {
                    String[] checkspace = word2.split(" ");
                    if (checkspace.length > 1) {
                        for (int i = 0; i < checkspace.length; i++) {
                            subwords.add(checkspace[i]);
                        }
                    } else {
                        subwords.add(word2);

                    }
                    break;
                }
            }
        }
        return subwords;
    }
}

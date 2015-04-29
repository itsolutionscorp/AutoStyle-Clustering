package ngordnet;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import edu.princeton.cs.algs4.Digraph;
import java.io.IOException;

public class WordNet {
    
    private List<String> syn;
    private List<String> hyp;
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            syn = Files.readAllLines(Paths.get(synsetFilename), Charset.defaultCharset());
            hyp = Files.readAllLines(Paths.get(hyponymFilename), Charset.defaultCharset());
        } catch (IOException ex) {
            System.out.println("Error: Filepath not found");
        }
        g = new Digraph(Integer.parseInt(syn.get(syn.size() - 1).split(",")[0]) + 1);
        for (String line : hyp) {
            for (int hCount = 1; hCount < line.split(",").length; hCount += 1) {
                g.addEdge(Integer.parseInt(line.split(",")[0]), 
                          Integer.parseInt(line.split(",")[hCount]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String line : syn) {
            for (String synonym: line.split(",")[1].split(" ")) {
                if (synonym.equals(noun)) {
                    return true;
                }
            }       
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();
        Set<String> repeatCheck = new HashSet<String>();
        for (String line : syn) {
            for (String synonym: line.split(",")[1].split(" ")) {
                if (!repeatCheck.contains(synonym)) {
                    nounSet.add(synonym);
                    repeatCheck.add(synonym);
                }
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> relatedWords = new HashSet<String>();
        Set<String> repeatCheck = new HashSet<String>();
        Set<Integer> synIndex = new HashSet<Integer>();
        for (String line : syn) {
            for (String synonym: line.split(",")[1].split(" ")) {
                if (synonym.equals(word)) {
                    synIndex.add(Integer.parseInt(line.split(",")[0]));
                    for (String wordToAdd : line.split(",")[1].split(" ")) {
                        if (!repeatCheck.contains(wordToAdd)) {
                            relatedWords.add(wordToAdd);
                            repeatCheck.add(wordToAdd);
                        }
                    }
                }
            }       
        }
        Set<Integer> hypIndex = GraphHelper.descendants(g, synIndex);
        for (int index : hypIndex) {
            for (String line : syn) {
                if (index == Integer.parseInt(line.split(",")[0])) {
                    for (String synonym: line.split(",")[1].split(" ")) {
                        for (String wordToAdd : line.split(",")[1].split(" ")) {
                            if (!repeatCheck.contains(wordToAdd)) {
                                relatedWords.add(wordToAdd);
                                repeatCheck.add(wordToAdd);
                            }
                        }
                    }
                }
            }
        }
        return relatedWords;
    }
}

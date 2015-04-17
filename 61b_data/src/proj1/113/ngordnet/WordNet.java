package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;

public class WordNet {
    private Digraph dia;
    private Map<Integer, String> idSynset;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        idSynset = new HashMap<Integer, String>();
        int vertices = synsetFile.readAllLines().length; 
        dia = new Digraph(vertices);

        synsetFile = new In(synsetFilename); //gets us length of digraph
        readSynsetLines(synsetFile); 
        createEdges(hyponymFile);
        
    }
    private void createEdges(In hyponyms) {
        int comma, first;
        String line;
        while (hyponyms.hasNextLine()) {
            line = hyponyms.readLine();
            if (line.length() != 0) {
                comma = line.indexOf(",");
                first = Integer.parseInt(line.substring(0, comma));
                line = line.substring(comma + 1);

                for (String x: ParseLine.nounsInLine(line, ",")) {
                    dia.addEdge(first, Integer.parseInt(x));
                }
            }
        }
    }

    private void readSynsetLines(In file) {
        int comma, id;
        String line, synset;
        while (file.hasNextLine()) {
            line = file.readLine();
            if (line.length() != 0) {
                comma = line.indexOf(","); //get id
                id = Integer.parseInt(line.substring(0, comma));
                line = line.substring(comma + 1);

                comma = line.indexOf(","); //get synset
                synset = line.substring(0, comma);

                idSynset.put(id, synset); // add to dictionary
            }
        }
    }
    public Set<String> hyponyms(String word) {
        //use graph helper to get the edges that are descendants
        //then map those edges with the dictionary
        if (!isNoun(word)) {
            throw new IllegalArgumentException("Not a valid noun"); 
        }
        Set<Integer> ids = getID(word);
        Set<Integer> descendantsIds = GraphHelper.descendants(dia, ids);
        Set<String> descendantsNames = new HashSet<String>();
        for (Integer id: descendantsIds) {
            String synset = idSynset.get(id);
            LinkedList<String> names = ParseLine.nounsInLine(synset, " ");
            for (String name: names) {
                descendantsNames.add(name);
            }
        }
        return descendantsNames;
    }

    private Set<Integer> getID(String word) {  
        Set<Integer> idsOfWord = new HashSet<Integer>();
        Set<Integer> keys = idSynset.keySet();
        for (Integer id: keys) {
            String synset = idSynset.get(id);
            for (String x: ParseLine.nounsInLine(synset, " ")) {
                if (x.equals(word)) {
                    idsOfWord.add(id);
                }
            }
        }
        return idsOfWord;
    }

    public boolean isNoun(String noun) {
        Set<String> values = nouns();
        return values.contains(noun);
    }
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        Collection<String> values = idSynset.values();
        for (String synset: values) {
            for (String word: ParseLine.nounsInLine(synset, " ")) {
                nouns.add(word);
            }
        }
        return nouns;
    }
}

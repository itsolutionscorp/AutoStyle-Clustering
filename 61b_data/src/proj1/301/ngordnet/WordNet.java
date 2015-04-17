package ngordnet;
import edu.princeton.cs.introcs.In;
import java.nio.file.InvalidPathException;
import edu.princeton.cs.algs4.Digraph;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;


public class WordNet {

    private HashMap<Integer, Data> idMap;
    private HashMap<String, Data> stringMap;
    private Digraph graph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In sFile = new In(synsetFilename);
        if (!sFile.exists()) {
            throw new InvalidPathException(synsetFilename, "File Not Found");
        }

        In hFile = new In(hyponymFilename);
        if (!hFile.exists()) { 
            throw new InvalidPathException(hyponymFilename, "File Not Found");
        }

        idMap = new HashMap<Integer, Data>();
        stringMap = new HashMap<String, Data>();

        while (sFile.hasNextLine()) { 
            String line = sFile.readLine();
            String [] s = line.split(",");
            int id = Integer.parseInt(s[0]);
            Set<String> keys = new HashSet<String>(Arrays.asList(s[1].split(" ")));
            Data d = new Data();
            d.setSynonyms(keys);
            d.setDefinition(s[2]);
            d.setId(id);
            idMap.put(id, d);
            for (String l : keys) {
                stringMap.put(l, d);
            }
        }

        graph = new Digraph(idMap.size());

        while (hFile.hasNextLine()) {
            String line = hFile.readLine();
            String [] s = line.split(",");
            int id = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++) {
                int hypId = Integer.parseInt(s[i]);
                graph.addEdge(id, hypId);
            }
        }
    }

    public boolean isNoun(String word) {
        return stringMap.containsKey(word);
    }

    public Set<String> nouns() {
        Set<String> s = new HashSet<String>();
        for (int i : idMap.keySet()) {
            s.addAll(idMap.get(i).getSynonyms());
        }
        return s;
    }

    private Set<String> getAllHyponyms(int i) {
        Set<String> s = new HashSet<String>();
        for (int j : graph.adj(i)) {
            Set<String> t = idMap.get(j).getSynonyms();
            s.addAll(t);
            s.addAll(getAllHyponyms(j));
        }
        return s;
    }

    public Set<String> hyponyms(String word) {
        Set<String> s = new HashSet<String>();
        if (stringMap.containsKey(word)) {
            for (int i : idMap.keySet()) {
                if (idMap.get(i).getSynonyms().contains(word)) {
                    s.addAll(idMap.get(i).getSynonyms());
                    s.addAll(getAllHyponyms(i));
                }
            }
            s.add(word);
            if (stringMap.containsKey(word)) {
                s.addAll(stringMap.get(word).getSynonyms());
            }
            return s;
        }
        s.add(word);
        if (stringMap.containsKey(word)) {
            s.addAll(stringMap.get(word).getSynonyms());
        }
        return s;
    }
}

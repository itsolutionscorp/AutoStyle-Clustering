package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class WordNet {
    private Digraph digraph;
    private HashMap<Integer, String> hashmap;

    public WordNet(String synpath, String hyppath) {
        hashmap = new HashMap<Integer, String>();
        In syn = new In(synpath);
        int count = 0;
        while (syn.hasNextLine()) {
            StringTokenizer synTokens = new StringTokenizer(syn.readLine(), ",");
            int synId = Integer.parseInt(synTokens.nextToken());
            hashmap.put(synId, synTokens.nextToken());
            count++;
        }
        In hyp = new In(hyppath);
        digraph = new Digraph(count);
        while (hyp.hasNextLine()) {
            StringTokenizer hypTokens = new StringTokenizer(hyp.readLine(), ",");
            int hypId = Integer.parseInt(hypTokens.nextToken());
            while (hypTokens.hasMoreTokens()) {
                digraph.addEdge(hypId, Integer.parseInt(hypTokens.nextToken()));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet<String>();
        for (int id : hashmap.keySet()) {
            StringTokenizer synonyms = new StringTokenizer(hashmap.get(id), " ");
            while (synonyms.hasMoreTokens()) {
                if (synonyms.nextToken().equals(word)) {
                    result.addAll(hypHelper(id));
                }
            }
        }
        return result;
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> nouns() {
        HashSet<String> result = new HashSet<String>();
        for (int id : hashmap.keySet()) {
            StringTokenizer synonyms = new StringTokenizer(hashmap.get(id), " ");
            while (synonyms.hasMoreTokens()) {
                result.add(synonyms.nextToken());
            }
        }
        return result;
    }

    private Set<String> hypHelper(int id) {
        StringTokenizer synonyms = new StringTokenizer(hashmap.get(id), " ");
        HashSet<String> result = new HashSet<String>();
        while (synonyms.hasMoreTokens()) {
            result.add(synonyms.nextToken());
        }
        for (int hypId : digraph.adj(id)) {
            result.addAll(hypHelper(hypId));
        }
        return result;
    }
}

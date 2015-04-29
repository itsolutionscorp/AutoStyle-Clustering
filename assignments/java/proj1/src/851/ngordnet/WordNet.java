package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;


public class WordNet  {
    private HashMap<Integer, String> synset = new HashMap<Integer, String>();
    private Digraph hyponym;

    public WordNet(String synsetFile, String hyponymFile)  {
        In synsetRead = new In(synsetFile);
        In hyponymRead = new In(hyponymFile);
        while (synsetRead.hasNextLine()) {
            String line = synsetRead.readLine();
            int start = (line.indexOf(",") + 1);
            String subline = line.substring(line.indexOf(",") + 1);
            int stop = subline.indexOf(",") + start;
            int key = Integer.parseInt(line.substring(0, (start - 1)));
            String value = line.substring(start, stop);
            synset.put(key, value);
        }
        hyponym = new Digraph(synset.size());
        while (hyponymRead.hasNextLine()) {
            String line = hyponymRead.readLine();
            int key = Integer.parseInt(line.substring(0, line.indexOf(",")));
            line = line.substring((line.indexOf(",") + 1));
            while (line.contains(",")) {
                String subset = line.substring(0, line.indexOf(","));
                hyponym.addEdge(key, Integer.parseInt(subset));
                line = line.substring((line.indexOf(",") + 1));
            }
            hyponym.addEdge(key, Integer.parseInt(line));
        }
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> keys = synset.keySet();
        Set<Integer> id = new TreeSet<Integer>();
        for (Iterator<Integer> keyIt = keys.iterator(); keyIt.hasNext();) {
            Integer key = keyIt.next();
            String nextLine = synset.get(key);
            if (nextLine.contains(word)) {
                while (nextLine.contains(" ")) {
                    String subline = nextLine.substring(0, nextLine.indexOf(" "));
                    if (subline.equals(word)) {
                        id.add(key);
                    }
                    nextLine = nextLine.substring(nextLine.indexOf(" ") + 1);
                }
                if (nextLine.equals(word)) {
                    id.add(key);
                }
            }
        }
        Set<Integer> hyponymKeys = GraphHelper.descendants(hyponym, id);
        Set<String> hyponyms = new TreeSet<String>();
        for (Iterator<Integer> hyIt = hyponymKeys.iterator(); hyIt.hasNext();) {
            String nextHyponym = synset.get(hyIt.next());
            while (nextHyponym.contains(" ")) {
                String subHyp = nextHyponym.substring(0, nextHyponym.indexOf(" "));
                hyponyms.add(subHyp);
                nextHyponym = nextHyponym.substring((nextHyponym.indexOf(" ") + 1));
            }
            hyponyms.add(nextHyponym);
        }
        return hyponyms;
    }

    public boolean isNoun(String noun) {
        return (nouns().contains(noun));
    }

    public Set<String> nouns() {
        Set<Integer> keys = synset.keySet();
        Set<String> nouns = new TreeSet<String>();
        for (Iterator<Integer> keyIt = keys.iterator(); keyIt.hasNext();) {
            Integer key = keyIt.next();
            String nextNoun = synset.get(key);
            while (nextNoun.contains(" ")) {
                String subNoun = nextNoun.substring(0, nextNoun.indexOf(" "));
                nouns.add(subNoun);
                nextNoun = nextNoun.substring((nextNoun.indexOf(" ") + 1));
            }
            nouns.add(nextNoun);
        }
        return nouns;
    }
}

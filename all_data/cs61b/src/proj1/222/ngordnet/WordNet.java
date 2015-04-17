package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
public class WordNet {
    private HashMap<Integer, Set<String>> list1;
    private HashMap<String, Set<Integer>> list2;
    private In reader1;
    private In reader2;
    private Digraph relationship;
    public WordNet(String synsets, String hyponyms) {
        organize1(synsets);
        organize2(hyponyms);
    }
    public boolean isNoun(String noun) {
        return list2.containsKey(noun);
    }
    public Set<String> nouns() {
        return list2.keySet();
    }
    public Set<String> hyponyms(String word) {
        if (list2.containsKey(word)) {
            Set<Integer> numbers = new TreeSet<Integer>();
            Set<String> hyps = new TreeSet<String>();
            for (int x: list2.get(word)) {
                numbers.add(x);
            }
            numbers = GraphHelper.descendants(relationship, numbers);
            for (int x: numbers) {
                for (String y: list1.get(x)) {
                    hyps.add(y);
                }
            }
            return hyps;
        }
        System.out.println("No such word exist");
        return null;
    }
    private void organize1(String synsets) {
        list1 = new HashMap<Integer, Set<String>>();
        list2 = new HashMap<String, Set<Integer>>();
        reader1 = new In(synsets);
        while (reader1.hasNextLine()) {
            String line  = reader1.readLine();
            String[] placeholders = line.split(",");
            int value = Integer.parseInt(placeholders[0]);
            String[] noun = placeholders[1].split(" ");
            Set<String> nouns = new TreeSet<String>();
            for (int a = 0; a < noun.length; a++) {
                String word = noun[a];
                nouns.add(word);
                Set<Integer> numbers = new TreeSet<Integer>();
                if (list2.containsKey(word)) {
                    list2.get(word).add(value);
                } else {
                    numbers.add(value);
                    list2.put(word, numbers);
                }
            }
            list1.put(value, nouns);
        }
    }

    private void organize2(String hyponyms) {
        relationship = new Digraph(list1.size());
        reader2 = new In(hyponyms);
        while (reader2.hasNextLine()) {
            String line = reader2.readLine();
            String[] placeholders = line.split(",");
            int value = Integer.parseInt(placeholders[0]);
            for (int i = 1; i < placeholders.length; i++) {
                relationship.addEdge(value, Integer.parseInt(placeholders[i]));
            }
        }
    }
}

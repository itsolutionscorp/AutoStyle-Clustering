package ngordnet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
//import ngordnet.GraphHelper;
import java.util.Iterator;


public class WordNet {
    class Content {
        private Set<String> syns;
        private String def;
        public Content(String newSyns, String newDef) {
            String[] splitted = newSyns.split(" ");
            syns = new HashSet<String>();       
            for (int i = 0; i < splitted.length; i++) {
                syns.add(splitted[i]);
            }
            def = newDef;
        }

        public Set<String> getSyns() {
            return syns;
        }

        public String getDef() {
            return def;
        }
    }
    private List<Content> content = new ArrayList<>();
    private Digraph g;
    private Map<String, List<Integer>> wordmap;

    public WordNet(String filename, String hyponymFilename) {
        In synsets = new In(filename);
        In hyponyms = new In(hyponymFilename);
        wordmap = new HashMap<String, List<Integer>>();

        while (!synsets.isEmpty()) {
            String line = synsets.readLine();
            String[] args = line.split(",");
            int num = Integer.parseInt(args[0]);
            content.add(num, new Content(args[1], args[2]));

            String[] synonyms = args[1].split(" ");     
            for (int i = 0; i < synonyms.length; i++) {
                if (wordmap.containsKey(synonyms[i])) {
                    wordmap.get(synonyms[i]).add(num);
                } else {
                    List<Integer> newList = new ArrayList<Integer>();
                    newList.add(num);
                    wordmap.put(synonyms[i], newList);
                }
            }
        }
        g = new Digraph(content.size());

        while (!hyponyms.isEmpty()) {
            String line = hyponyms.readLine();
            String[] args = line.split(",");
            for (int i = 1; i < args.length; i++) {
                g.addEdge(Integer.parseInt(args[0]), Integer.parseInt(args[i]));
            }
        }
    }

    public Set<String> hyponyms(String query) {
        if (!wordmap.containsKey(query)) {
            System.out.println("no corresponding words");
            return null;
        } else {
            Set<String> ans = new HashSet<String>();
            List<Integer> wordlst = wordmap.get(query);
            for (int i = 0; i < wordlst.size(); i++) {
                ans.addAll(content.get(wordlst.get(i)).getSyns());
                Set<Integer> tbq = new HashSet<Integer>();
                tbq.add(wordlst.get(i));
                Iterator<Integer> hypono = GraphHelper.descendants(g, tbq).iterator();
                while (hypono.hasNext()) {
                    int index = hypono.next();
                    Object[] syns = (content.get(index).getSyns().toArray());
                    for (int j = 0; j < syns.length; j++) {
                        ans.add((String) (syns[j]));
                    }
                }
            }

            return ans;     
        }
    }

    public  boolean isNoun(String query) {
        return (wordmap.containsKey(query));
    }

    public Set<String> nouns() {
        return wordmap.keySet();
    }
}

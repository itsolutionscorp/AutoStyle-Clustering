package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;

public class WordNet { // Creates a WordNet using files form SYNSETFILENAME and
                       // HYPONYMFILENAME

    private Digraph g;
    private TreeMap<Integer, String[]> t;
    private TreeSet<String> ts;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In i1 = new In(synsetFilename);
        In i2 = new In(hyponymFilename);
        // GENERATING TREE MAP
        t = new TreeMap<Integer, String[]>();
        int i;
        int j;
        String[] wordsArray;
        String[] splittedLine;
        String st;
        i = 0;
        j = 0;
        while (i1.hasNextLine()) {
            j += 1;
            st = i1.readLine();
            splittedLine = st.split(",");
            st = splittedLine[1];
            wordsArray = st.split(" ");
            t.put(i, wordsArray);
            i += 1;
        }
        // Tree Print Test Noun Set Generator
        // System.out.println("BEGIN INITIALIZATION AND PRINTING TEST OF WORDNET");
        // System.out.println("TESTING TREE MAP");
        ts = new TreeSet<String>();
        Set<Integer> keySet = t.keySet();
        Iterator<Integer> keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            int help = keyIterator.next();
            String[] temp = t.get(help);
            ts.add(temp[0]);
            // System.out.print(help + " --> " + temp[0] + " ");
            for (int z = 1; z < temp.length; z++) {
                ts.add(temp[z]);
                // System.out.print(temp[z] + " ");
            }
            // System.out.println("");
        }
        // System.out.println(" ");
        // End Tree Print Test

        // DONE GENERATING TREE MAP

        // GENERATING GRAPH
        g = new Digraph(j);
        int h;
        int k;
        int l;
        String[] sp;
        String s;
        while (i2.hasNextLine()) { // This makes all the edges in the graph
            s = i2.readLine();
            sp = s.split(",");
            l = sp.length;
            h = Integer.parseInt(sp[0]);
            i = 1;
            while (i < l) {
                k = Integer.parseInt(sp[i]);
                g.addEdge(h, k);
                i += 1;
            }
        }
        // Graph Print Test
        // System.out.println("TESTING GRAPH PRINT");
        // System.out.println(g);
        // System.out.println("DONE INITIALIZING AND PRINTING WORDNET");
        // End Graph Print Test
        // DONE GENERATING GRAPH
    }

    public boolean isNoun(String noun) {
        Set<String> s = this.hyponyms(noun);
        return (s.size() > 0);
    }

    public Set<String> nouns() {
        return ts;
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> hs = new TreeSet<String>();
        int i = 0;
        int j = t.size();
        int h;
        String[] holder;
        TreeSet<Integer> found = new TreeSet<Integer>();
        while (i < j) {
            holder = t.get(i);
            for (int z = 0; z < holder.length; z++) {
                if (holder[z].equals(word)) {
                    for (int y = 0; y < holder.length; y++) {
                        hs.add(holder[y]);
                    }
                    Iterable<Integer> below = g.adj(i);
                    for (Integer x : below) {
                        found.add(x);
                    }
                }
            }
            i = i + 1;
        }
        Set<Integer> des = GraphHelper.descendants(g, found);
        Iterator<Integer> underneath = des.iterator();
        while (underneath.hasNext()) {
            h = underneath.next();
            holder = t.get(h);
            for (String p : holder) {
                hs.add(p);
            }
        }
        return hs;
    }
}

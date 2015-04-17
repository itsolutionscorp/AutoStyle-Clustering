package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;



public class WordNet {
    private Map wordlist = null;
    private int numLine = 0;
    private Digraph g;
    private Set<String> wordSet = new TreeSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In in1 = new In(synsetFilename);
        In in2 = new In(hyponymFilename);

        getWordList(in1);
        getHyponymList(in2);
    }
    private void getWordList(In in) {
        String readstring;
        String[] splitwords;
        String[] lst;
        int id;
        while (in.hasNextLine()) {
            numLine += 1;
            readstring = in.readLine();
            splitwords = readstring.split(",");
            id = Integer.parseInt(splitwords[0]);
            lst = splitwords[1].split(" ");
            for (int i = 0; i < lst.length; i++) {
                wordlist = new Map(id, lst[i], wordlist);
            }
        }
    }

    private void getHyponymList(In in) {
        int hypernym;
        String readHyponyms;
        String[] splitHyponyms;
        g = new Digraph(numLine);
        while (in.hasNextLine()) {
            readHyponyms = in.readLine();
            splitHyponyms = readHyponyms.split(",");
            hypernym = Integer.parseInt(splitHyponyms[0]);
            for (int i = 1; i < splitHyponyms.length; i++) {
                g.addEdge(hypernym, Integer.parseInt(splitHyponyms[i]));
            }
        }
    }
    public boolean isNoun(String noun) {
        Set<String> nounlist = new TreeSet<String>();
        nounlist = nouns();
        if (nounlist.contains(noun)) {
            return true;
        }
        return false;
    }
    public Set<String> nouns() {
        Map temp = wordlist;

        while (temp != null) {
            if (!wordSet.contains(temp.words)) {
                wordSet.add(temp.words);
            }
            temp = temp.next;
        }
        return wordSet;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new TreeSet<String>();
        Set<Integer> idlist = new TreeSet<Integer>();
        Set<Integer> hyponymlist = new TreeSet<Integer>();
        Set<String> synonymList = new TreeSet<String>();
        if (isNoun(word)) {
            idlist = synsetsID(word);
            hyponymlist = GraphHelper.descendants(g, idlist);
            for (int i : hyponymlist) {
                synonymList = wordlist.get(i);
                for (String hypo : synonymList) {
                    hyponymSet.add(hypo);
                }
            }
        }
        return hyponymSet;
    }
    private Set<Integer> synsetsID(String word) {
        Set<Integer> idList = new TreeSet<Integer>();
        Map temp = wordlist;
        while (temp != null) {
            if (temp.words.equals(word)) {
                idList.add(temp.idnum);
            }
            temp = temp.next;
        }
        return idList;
    }

    private class Map {
        private int idnum;
        private String words;
        private Map next;
        public Map(int first, String second, Map nxt) {
            idnum = first;
            words = second;
            next = nxt;
        }
    
        public Set<String> get(int num) {
            Map temp = wordlist;
            Set<String> setOfWords = new TreeSet<String>();
            while (temp != null) {
                if (temp.idnum == num) {
                    setOfWords.add(temp.words);
                }
                temp = temp.next;
            }
            return setOfWords; 

        }

    }
}

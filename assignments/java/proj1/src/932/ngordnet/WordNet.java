package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class WordNet {
    private In filenoun; 
    private In filehyp;

    private Digraph g;
    private HashMap<Integer, String> hMnoun;

    private ArrayList<String> allnouns;
    private ArrayList<String> allnounsF;

    private Set<String> setofnouns;
    
    public WordNet(String synsetFilename, String hyponymFilename) {

        filenoun = new In(synsetFilename);
        filehyp = new In(hyponymFilename); 

        hMnoun = new HashMap<Integer, String>();

        allnouns = new ArrayList<String>();
        allnounsF = new ArrayList<String>();
        while (filenoun.hasNextLine()) {
            String temp = filenoun.readLine();
            String[] listofwords = temp.split(",");
            hMnoun.put(Integer.parseInt(listofwords[0]), listofwords[1]);
            allnouns.add(listofwords[1]);
        }

        for (String w: allnouns) {
            String[] l = w.split(" ");
            for (String c: l) {
                allnounsF.add(c);
            }
        }

        g = new Digraph(hMnoun.size());

        while (filehyp.hasNextLine()) {
            int count = 1;
            String temp = filehyp.readLine();
            String[] listofwords2 = temp.split(",");
            int[] lowi = new int[listofwords2.length];
            for (int counter = 0; counter < listofwords2.length; counter++) {
                lowi[counter] = Integer.parseInt(listofwords2[counter]);
            }
            while (count < listofwords2.length) {
                g.addEdge(lowi[0], lowi[count]);
                count++;
            }
        }
    }

    public boolean isNoun(String noun) {

        setofnouns = new HashSet<String>(allnounsF);

        for (String w: setofnouns) {
            if (w.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {

        setofnouns = new HashSet<String>(allnounsF);
        return setofnouns;

    }

    public Set<String> hyponyms(String word) {

        Set<String> liststr = new TreeSet<String>();
        ArrayList<Integer> index = new ArrayList<Integer>();

        for (int i = 0; i < hMnoun.size(); i++) {
            String[] temp = hMnoun.get(i).split(" ");
            for (String w: temp) {
                if (w.equals(word)) {
                    index.add(i);
                }
            }
        }

        for (int i: index) {
            Set<Integer> addkey = new TreeSet<Integer>();
            addkey.add(i);
            for (Integer m: GraphHelper.descendants(g, addkey)) {
                String[] temp2 = hMnoun.get(m).split(" ");
                for (String w: temp2) {
                    liststr.add(w);
                }
            }
        }

        return liststr;

    } //

}


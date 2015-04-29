package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private TreeMap<String, ArrayList<WordNode>> wordMap = new TreeMap();
    private ArrayList<WordNode> nodes = new ArrayList();
    private int alphaLen = 0;

    public boolean isNoun(String noun) {
        if (noun != null && wordMap.get(noun) != null) {
            return true;
        }
        return false;
    }

    /*public ArrayList<WordNode> nodes() {
        return nodes;
    }*/

    public Set<String> nouns() {
        //String[] retr = new String[alphaLen];
        ArrayList<String> retr = new ArrayList(alphaLen);
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).lenWords(); j++) {
                retr.add(nodes.get(i).word(j));
            }
        }
        Set<String> retrRetr = new HashSet(retr);
        return retrRetr;
    }

    public WordNet(String synFile, String hypoFile) {
        In synsets = new In(synFile);
        In hyponyms = new In(hypoFile);
        ArrayList<String> curr;
        int i = 0;
        while (synsets.hasNextLine()) {
            curr = parseWords(synsets.readLine());
            nodes.add(new WordNode(this.nodes));
            for (int j = 0; j < curr.size(); j++) {
                alphaLen++;
                nodes.get(i).addWord(curr.get(j));
                ArrayList<WordNode> old = wordMap.get(curr.get(j));
                if (old == null) {
                    old = new ArrayList(1);
                    old.add(nodes.get(i));
                } else {
                    old.add(nodes.get(i));
                }
                wordMap.put(curr.get(j), old);
            }
            i++;
        }
        int first = -1;
        String[] num;
        while (hyponyms.hasNextLine()) {
            num = hyponyms.readLine().split(",", -1);
            first = Integer.parseInt(num[0]);
            for (int k = 1; k < num.length; k++) {
                nodes.get(first).addLink(Integer.parseInt(num[k]));
            }
        }
    }

    private static ArrayList<String> parseWords(String theStrings) {
        char[] cArray = theStrings.toCharArray();
        ArrayList<String> retr = new ArrayList();
        int j = 0;
        retr.add("");
        boolean started = false;
        for (int i = 0; i < cArray.length; i++) {
            if (!started && cArray[i] == ',') {
                started = true;
            } else if (cArray[i] == ',') {
                break;
            } else if (started) {
                if (cArray[i] == ' ') {
                    j++;
                    retr.add("");
                } else {
                    retr.set(j, retr.get(j) + cArray[i]);
                }
            }
        }
        return retr;
        /*ArrayList<String> retr=new ArrayList();
        retr.add("asdf");
        retr.add("fdfddf");
        return retr;*/
    }

    public Set<String> hyponyms(String base) {
        ArrayList<ArrayList<String>> retr = new ArrayList();
        int index = 0;
        for (int i = 0; i < wordMap.get(base).size(); i++) {
            retr.add(wordMap.get(base).get(i).getHyponyms());
            index += retr.get(i).size();
        }
        ArrayList<String> strRetr = new ArrayList(index);
        for (int i = 0; i < retr.size(); i++) {
            for (int j = 0; j  < retr.get(i).size(); j++) {
                strRetr.add(retr.get(i).get(j));
            }
        }
        Set<String> retrRetr = new HashSet(strRetr);
        return retrRetr;
    }
}


package ngordnet;  
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Map<Integer, List<String>> syn = new HashMap<Integer, List<String>>();

    private Map<Integer, List<Integer>> hyp = new HashMap<Integer, List<Integer>>();

    private Digraph g;

    private String tempString2;

    private String tempHypS;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synIn = new In(synsetFilename);
        while (synIn.hasNextLine()) {
            String tempKey = "";
            String tempVal = "";
            tempString2 = synIn.readLine();
            for (int i = 0; i < tempString2.length(); i++) {
                Character c = tempString2.charAt(i);
                if (c.equals(',')) {
                    break;
                }
                tempKey += c;
            }
            int startingIndex = 0;
            for (int i = 0; i < tempString2.length(); i++) {
                Character d = tempString2.charAt(i);
                if (d.equals(',')) {
                    startingIndex = i;
                    break;
                }
            }
            for (int i = startingIndex + 1; i < tempString2.length(); i++) {
                Character e = tempString2.charAt(i);
                if (e.equals(',')) {
                    break;
                }
                tempVal += e;
            }
            List<String> allVal = new ArrayList<String>();
            String tempX = "";
            int i = 0;
            while (i < tempVal.length()) {
                Character f = tempVal.charAt(i);
                if (!f.equals(' ')) {
                    if (i == tempVal.length() - 1) {
                        tempX += f;
                        allVal.add(tempX);
                    }
                    tempX += f;
                    i += 1;
                } else if (f.equals(' ')) {
                    allVal.add(tempX);
                    tempX = "";
                    i += 1;
                }
            }
            if (allVal.size() > 1) {
                int tempIntKey = Integer.parseInt(tempKey);
                syn.put(tempIntKey, allVal);
            } else {
                int tempIntKey = Integer.parseInt(tempKey);
                syn.put(tempIntKey, allVal);
            }
        }
        makeHypDi(synsetFilename, hyponymFilename);

    }

    private void makeHypDi(String synsetFilename, String hyponymFilename) {
        In hypIn = new In(hyponymFilename);
        while (hypIn.hasNextLine()) {
            String tempKeyH = "";
            String tempValH = "";
            tempHypS = hypIn.readLine();
            for (int i = 0; i < tempHypS.length(); i++) {
                Character cc = tempHypS.charAt(i);
                if (cc.equals(',')) {
                    break;
                }
                tempKeyH += cc;
            }
            int startingIndex2 = 0;
            for (int i = 0; i < tempHypS.length(); i++) {
                Character d = tempHypS.charAt(i);
                if (d.equals(',')) {
                    startingIndex2 = i;
                    break;
                }
            }
            for (int i = startingIndex2 + 1; i < tempHypS.length(); i++) {
                Character h = tempHypS.charAt(i);
                if (i == tempHypS.length()) {
                    break;
                }
                tempValH += h;
            }           
            List<Integer> allValH = new ArrayList<Integer>();
            String tempH = "";
            int o = 0;
            while (o < tempValH.length()) {
                Character p = tempValH.charAt(o);
                if (p.equals(',')) {
                    allValH.add(Integer.parseInt(tempH));
                    tempH = "";
                    o += 1;
                } else if (o == tempValH.length() - 1) {
                    tempH += p;
                    allValH.add(Integer.parseInt(tempH));
                    break;
                } else {
                    tempH += p;
                    o += 1;
                }
            }
            int tempKeyHI = Integer.parseInt(tempKeyH);

            if (hyp.containsKey(tempKeyHI)) {
                List<Integer> tempHH = hyp.get(tempKeyHI);
                for (int i = 0; i < allValH.size(); i++) {
                    tempHH.add(allValH.get(i));
                }
                hyp.put(tempKeyHI, tempHH);
            } else {
                hyp.put(tempKeyHI, allValH);

            }
        }
        Set<Integer> l = hyp.keySet();
        List<Integer> m = new ArrayList<Integer>();
        for (Integer i : l) {
            m.add(i);
        }
        g = new Digraph(syn.size());
        for (int i = 0; i < m.size(); i++) {
            List<Integer> listV = new ArrayList<Integer>();
            listV = hyp.get(m.get(i));
            for (int j = 0; j < listV.size(); j++) {
                int a = m.get(i);
                int b = listV.get(j);
                g.addEdge(a, b);
            }
        }
    }

    public boolean isNoun(String noun) {
        for (int i = 0; i < syn.size(); i++) {
            List<String> tempList = syn.get(i);
            for (int j = 0; j < tempList.size(); j++) {
                if (tempList.get(j).equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (int i = 0; i < syn.size(); i++) {
            List<String> tempList = syn.get(i);
            for (int j = 0; j < tempList.size(); j++) {
                allNouns.add(tempList.get(j));
            }
        }
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> allHyp = new HashSet<String>();
        Set<Integer> key = new TreeSet<Integer>();
        for (int i = 0; i < syn.size(); i++) {
            List<String> tempList = syn.get(i);
            for (int j = 0; j < tempList.size(); j++) {
                if (tempList.get(j).contains(word)) {
                    key.add(i);
                }
            }           
        }
        Set<Integer> hippos = GraphHelper.descendants(g, key);
        for (Integer hip: hippos) {
            if (hip != null) {
                List<String> temphip = syn.get(hip);
                if (temphip != null) {
                    for (String valhip : temphip) {
                        allHyp.add(valhip);
                    }
                }
            }
        }
        return allHyp;
    }

}

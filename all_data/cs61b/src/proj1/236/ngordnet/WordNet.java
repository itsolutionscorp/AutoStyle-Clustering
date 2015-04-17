package ngordnet;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, ArrayList<String>> nouns = new HashMap<Integer, ArrayList<String>>();
    private Map<Integer, Set<Integer>> hypernymId =  
            new HashMap<Integer, Set<Integer>>();
    private Map<String, Set<Integer>> hypernym =  
            new HashMap<String, Set<Integer>>();
    private Map<String, Set<String>> synonyms = 
            new HashMap<String, Set<String>>();
    private Map<Integer, String> definition = new HashMap<Integer, String>();
    private Set<String> nounSet = new HashSet<String>();
    private In in, in2;

    public WordNet(String sysnetFile, String hypernymFile) {
        in = new In(sysnetFile);
        in2 = new In(hypernymFile);
        sysnetFileReader();
        hypernymFileReader();
    }

    private void sysnetFileReader() {
        ArrayList<String> nouns2;
        Set<String> nouns3;
        String tempString;
        String[] tempArray;
        String[] tempsysnet;
        while ((tempString = in.readLine()) != null) {
            tempArray = tempString.split(",");
            tempsysnet = tempArray[1].split(" ");
            nouns2 = new ArrayList<String>(Arrays.asList(tempsysnet));
            for (String item : tempsysnet) {
                nounSet.add(item);
                if (synonyms.containsKey(item)) {
                    synonyms.get(item).addAll(nouns2);
                } else {
                    nouns3 = new HashSet<String>();
                    for (String tempItem : nouns2) {
                        nouns3.add(tempItem);
                    }
                    synonyms.put(item, nouns3);
                }
            }
            Integer index = Integer.parseInt(tempArray[0]);
            definition.put(index, tempArray[2]);
            nouns.put(index, nouns2);
        }
        in.close();
    }

    private void hypernymFileReader() {
        Set<Integer> hyponymlist;
        String tempString;
        String[] tempArray;
        int hypernymIndex;
        int tempIndex;
        Set<String> tempSet2;
        Set<String> tempSet;
        while ((tempString = in2.readLine()) != null) {
            hyponymlist = new HashSet<Integer>();
            tempSet = new HashSet<String>();
            tempArray = tempString.split(",");
            hypernymIndex = Integer.parseInt(tempArray[0]); //Read in sysnet ID of hypernym
            for (int i = 1; i < tempArray.length; i++) {
                tempIndex = Integer.parseInt(tempArray[i]); //Read sysnet IDs of hyponyms
                hyponymlist.add(tempIndex);
                for (String tempNoun2 : nouns.get(tempIndex)) {
                    if (!hypernym.containsKey(tempNoun2)) { //Link hyponym sysnet ID to hyponyms
                        Set<Integer> temp = new HashSet<Integer>();
                        temp.add(tempIndex);
                        hypernym.put(tempNoun2, temp);
                    } else {
                        Set<Integer> temp = new HashSet<Integer>();
                        temp.add(tempIndex);
                        hypernym.get(tempNoun2).add(tempIndex);
                    }
                }
            }
            if (hypernymId.containsKey(hypernymIndex)) { //Link hypernym sysnet ID to hypernym word
                Set<Integer> tempSet1 = new HashSet<Integer>();
                for (Integer num : hyponymlist) {
                    tempSet1.add(num);
                }
                hypernymId.get(hypernymIndex).addAll(tempSet1);
            } else {
                Set<Integer> tempSet1 = new HashSet<Integer>();
                for (Integer num : hyponymlist) {
                    tempSet1.add(num);
                }
                hypernymId.put(hypernymIndex , tempSet1);
            }
            hyponymlist.add(hypernymIndex);
            for (String item : nouns.get(hypernymIndex)) {
                if (hypernym.containsKey(item)) {
                    Set<Integer> tempSet1 = new HashSet<Integer>();
                    for (Integer num : hyponymlist) {
                        tempSet1.add(num);
                    }
                    hypernym.get(item).addAll(tempSet1);
                } else {
                    Set<Integer> tempSet1 = new HashSet<Integer>();
                    for (Integer num : hyponymlist) {
                        tempSet1.add(num);
                    }
                    hypernym.put(item, tempSet1);
                }
            }
        }
        in2.close();
    }

    public boolean isNoun(String noun) {
        for (Map.Entry<Integer, ArrayList<String>> entry : nouns.entrySet()) {
            if (entry.getValue().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        return nounSet;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> hypo1 = hypernym.get(word);
        Set<String> result = new HashSet<String>();
        for (Integer num : hypo1) {
            if (hypernymId.containsKey(num)) {
                result.addAll(hyponymHelper(num));
            } else {
                result.addAll(nouns.get(num));
            }
        }
        return result;
    }
    
    private Set<String> hyponymHelper(Integer id) {
        Set<Integer> hypoId = hypernymId.get(id);
        Set<String> wordSet = new HashSet<String>();
        wordSet.addAll(nouns.get(id));
        for (Integer num : hypoId) {
            if (!num.equals(id) && hypernymId.containsKey(num)) {
                wordSet.addAll(hyponymHelper(num));
            } else {
                wordSet.addAll(nouns.get(num));
            }
        }
        return wordSet;
    }
}

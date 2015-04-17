package ngordnet;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

import java.util.Set;
import java.util.TreeSet;

public class WordNet { 
    private  ArrayList<ArrayList<String>> myList = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<Integer>> hypList = new ArrayList<ArrayList<Integer>>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        In ins = new In(synsetFilename);
        while (ins.hasNextLine()) {
            ArrayList<String> empty1 = new ArrayList<String>();
            String[] words = ins.readLine().split(",");
            for (String ss : words[1].split(" ")) {
                empty1.add(ss);
            }    
            myList.add(empty1); 
        }

        In inh = new In(hyponymFilename);
        while (inh.hasNextLine()) {
            ArrayList<Integer> empty2 = new ArrayList<Integer>();
            for (String h : inh.readLine().split(",")) {
                empty2.add(Integer.parseInt(h));
            }
            hypList.add(empty2);
        }
    }

    public boolean isNoun(String noun) {
        for (ArrayList<String> a : myList) {
            for (String s : a) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> nounList = new TreeSet<String>();
    /* returns a list(can be for-ed) containing all nouns */
        for (ArrayList<String> a : myList) {
            for (String s : a) {
                if (!nounList.contains(s)) {
                    nounList.add(s);
                }
            }
        }
        return nounList;
    }

    private ArrayList<Integer> allHypoIndex(String word) {
        ArrayList<Integer> hypindex = new ArrayList<Integer>();
        // add all synsets
        for (ArrayList<Integer> h : hypList) {
            int index = h.get(0);
            if (myList.get(index).contains(word)) {
                hypindex.add(index);
            }
        }

        if (!hypindex.isEmpty()) {
            return allHypoIndexHelper(hypindex);
        }

        // for (int i = 0; i < myList.size(); i++) {
        //     if (myList.get(i).contains(word) && !hypindex.contains(i)) {
        //         hypindex.add(i);
        //     }
        // }
        return hypindex;
    }

    private ArrayList<Integer> allHypoIndexHelper(ArrayList<Integer> helper) {
        ArrayList<Integer> newhelper = new ArrayList<Integer>();
        for (int num : helper) {
            for (ArrayList<Integer> h : hypList) {
                if (h.get(0) == num) {
                    for (int subnum : h) {
                        if (!newhelper.contains(subnum)) {
                            newhelper.add(subnum);
                        }
                    }
                }
            }
        }
        if (newhelper.equals(helper)) {
            return helper;
        } else {
            return (allHypoIndexHelper(newhelper));
        }
    }

    public Set<String> hyponyms(String word) {
    /* returns a list(can be for-ed) containing all hyponym of *word */

        Set<String> hList = new TreeSet<String>();

        ArrayList<Integer> allSubIndex = allHypoIndex(word);
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).contains(word) && !allSubIndex.contains(i)) {
                allSubIndex.add(i);
            }
        }

             /* add hyponyms in hList */
        for (int subIndex : allSubIndex) {
            ArrayList<String> m = myList.get(subIndex);
            for (String s : m) {
                if (!hList.contains(s)) {
                    hList.add(s);
                }
            }
        }

        // for (int subIndex : allSubIndex){
        //  if (!hList.contains(String.valueOf(subIndex)))
        //      hList.add(String.valueOf(subIndex));
        // }

        return hList;
    }
    





}

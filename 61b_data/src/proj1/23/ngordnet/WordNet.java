package ngordnet; 

import java.io.File; 
import java.io.FileReader; 
import java.io.BufferedReader; 
import java.util.TreeMap; 
import java.util.ArrayList; 
import java.util.Set; 
import java.util.Iterator; 
import java.util.HashSet; 
import java.io.IOException;

public class WordNet {
    
    private TreeMap<Integer, ArrayList<String>> mysynset; 
    private TreeMap<Integer, ArrayList<Integer>> myhypset;
    private TreeMap<String, ArrayList<Integer>> myhelper; 

    public WordNet(String synsetFilename,  String hyponymFilename) {
        mysynset = new TreeMap<Integer, ArrayList<String>>(); 
        myhelper = new TreeMap<String, ArrayList<Integer>>();
        myhypset = new TreeMap<Integer, ArrayList<Integer>>(); 

        try {
            File mysynsetfile = new File(synsetFilename); 
            FileReader mysynsetfilereader = new FileReader(mysynsetfile); 
            BufferedReader mysynsetreader = new BufferedReader(mysynsetfilereader); 

            
            File myhyponymfile = new File(hyponymFilename); 
            FileReader myhyponymfilereader = new FileReader(myhyponymfile); 
            BufferedReader myhyponymreader = new BufferedReader(myhyponymfilereader); 
            
            String mysynline; 
            String myhypline; 

            while ((mysynline = mysynsetreader.readLine()) != null) {
                String[] mysynsplit = mysynline.split(","); 
                Integer pointer = Integer.parseInt(mysynsplit[0]); 
                ArrayList<String> words = new ArrayList<String>(); 
                String[] mysynsplitwords = mysynsplit[1].split(" "); 
                for (String syn : mysynsplitwords) {
                    if (myhelper.containsKey(syn)) {
                        myhelper.get(syn).add(pointer);
                    } else {
                        ArrayList<Integer> pointerarr = new ArrayList<Integer>();
                        pointerarr.add(pointer);
                        myhelper.put(syn, pointerarr);
                    }
                    words.add(syn); 
                }
                mysynset.put(pointer, words); 
            } 

            while ((myhypline = myhyponymreader.readLine()) != null) {
                String[] myhypsplit = myhypline.split(","); 
                Integer first = Integer.parseInt(myhypsplit[0]); 
                String[] tokens = new String[myhypsplit.length - 1];
                System.arraycopy(myhypsplit, 1, tokens, 0, myhypsplit.length - 1);
                ArrayList<Integer> ints = new ArrayList<Integer>(); 
                for (String hyp : tokens) {
                    ints.add(Integer.parseInt(hyp)); 
                }
                if (myhypset.containsKey(first)) {
                    for (Integer hoop : ints) {
                        myhypset.get(first).add(hoop);
                    }
                } else {
                    myhypset.put(first, ints); 
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(); 
        }
    }
    
    public Set<String> hyponyms(String word) {
        HashSet<String> goal = new HashSet<String>();
        for (Integer stool : myhelper.get(word)) {
            for (String aool : mysynset.get(stool)) {
                goal.add(aool);
            }
            for (String bool : directHyp(stool)) {
                goal.add(bool);
            }
        }
        goal.add(word);
        return goal;
    }
    private Set<String> directHyp(Integer index) {
        HashSet<String> goal = new HashSet<String>();
        for (String pool : mysynset.get(index)) {
            goal.add(pool);
        }
        if (myhypset.containsKey(index)) {
            for (Integer hool : myhypset.get(index)) {
                if (myhypset.containsKey(hool)) {
                    for (String xool : directHyp(hool)) {
                        goal.add(xool);
                    }
                }
                for (String gool : mysynset.get(hool)) {
                    goal.add(gool);
                }
            }
        }
        return goal;
    }

    public boolean isNoun(String noun) {
        Set<String> holder = nouns(); 
        return holder.contains(noun); 
    }

    public Set<String> nouns() {
        ArrayList<String> myjustthe = new ArrayList<String>(); 
        HashSet<String> just = new HashSet<String>(); 
        Set<Integer> hodar = mysynset.keySet(); 
        Iterator<Integer> honeder = hodar.iterator(); 
        while (honeder.hasNext()) {
            myjustthe = mysynset.get(honeder.next()); 
            Iterator<String> goon = myjustthe.iterator();  
            while (goon.hasNext()) {
                just.add(goon.next()); 
            }
        }
        return just; 
    }
    
}

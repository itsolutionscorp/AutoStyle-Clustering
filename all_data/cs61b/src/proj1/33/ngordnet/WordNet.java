package ngordnet;

//import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class WordNet {
    //maps the ID to the synset
    private Map<Integer, SynEntry> words = new HashMap<Integer, SynEntry>();
    //maps all words to the synset of that ID
    private Map<String, ArrayList<Integer>> wordID = new HashMap<String, ArrayList<Integer>>();


    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);

        //words phase
        while (synsets.hasNextLine()) {
            String synLine = synsets.readLine();
            updateWordsMap(synLine);
        }
        //hyponyms phase
        while (hyponyms.hasNextLine()) {
            String nums = hyponyms.readLine();
            int comma = nums.indexOf(',');
            if (comma == -1) {
                int target = Integer.parseInt(nums);
                words.get(target).updateHyponyms(null);  
            }
            int target = Integer.parseInt(nums.substring(0, comma));
            nums = nums.substring(comma + 1);
            if (words.containsKey(target)) {
                words.get(target).updateHyponyms(nums);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        //check each word entry
        return wordID.containsKey(noun);
    }


    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordID.keySet();
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    * Can include itself 
    */
    public Set<String> hyponyms(String word) {
        List<Integer> containers = wordID.get(word);
        Set<String> give = new HashSet<String>(0);
        if (containers != null) {
            //for each synset with word
            for (Integer i : containers) {
                SynEntry target = words.get(i);
                if (target == null) {
                    throw new NullPointerException("SynEntry for ID " + i + " does not exist");
                }
                //for each word in the synset
                for (String s : target.synset) {
                    if (!give.contains(s)) {
                        give.add(s);
                    }
                }
                //for each hyponym
                Set<String> bigGive = recursiveHypos(target.hyponyms);
                for (String s : bigGive) {
                    give.add(s);
                }
            }
        }
        return give;
    }

    //Takes in the wordIDs to be checked
    private Set<String> recursiveHypos(List<Integer> localIDs) {
        Set<String> give = new HashSet<String>(0);
        for (Integer i : localIDs) {
            SynEntry local = words.get(i);
            if (local != null) {
                for (String s : local.synset) {
                    give.add(s);
                }
                for (String s : recursiveHypos(local.hyponyms)) {
                    give.add(s);
                }
            }
        }
        return give;
    }


    private class SynEntry {
        int synsetID;
        List<Integer> hyponyms;
        String[] synset;
        String definition;
        String type;

        SynEntry(int id, String[] ss, String def) {
            this(id, ss, def, new ArrayList<Integer>(0));
        }

        SynEntry(int id, String[] ss, String def, List<Integer> hypos) {
            synsetID = id;
            hyponyms = hypos;
            this.synset = ss;
            this.definition = def;
        }

        //takes a string of nums and adds them to this's hyponyms
        void updateHyponyms(String nums) {
            if (nums == null) {
                return;
            }
            int comma = nums.indexOf(',');
            while (comma > -1) {
                hyponyms.add(Integer.parseInt(nums.substring(0, comma)));
                nums = nums.substring(comma + 1);
                comma = nums.indexOf(',');
            }
            hyponyms.add(Integer.parseInt(nums));
        }
    }

    //adds a synset to the word map
    private void updateWordsMap(String synLine) {
        //get synset ID
        int comma = synLine.indexOf(',');
        int index = Integer.parseInt(synLine.substring(0, comma));
        synLine = synLine.substring(comma + 1);

        //get definition
        comma = synLine.indexOf(',');
        String synDef = synLine.substring(comma + 1);
        synLine = synLine.substring(0, comma);

        //make ArrayList of synonyms. Converst + casts it later
        List<String> set = new ArrayList<String>(0);
        while (synLine.indexOf(' ') != -1) {
            String temp = synLine.substring(0, synLine.indexOf(' '));
            synLine = synLine.substring(synLine.indexOf(' ') + 1);
            set.add(temp); 
        //add to the words ID Map
            addToWordID(temp, index);
        }
        set.add(synLine);
        addToWordID(synLine, index);

        //add to the words map
        words.put(index, new SynEntry(index, set.toArray(new String[0]), synDef));
    }

    private void addToWordID(String syn, int index) {
        if (syn == null) {
            return;
        }
        if (!wordID.containsKey(syn)) {
            ArrayList<Integer> temp = new ArrayList<Integer>(0);
            temp.add(index);
            wordID.put(syn, temp); 
        } else {
            ArrayList<Integer> temp = wordID.get(syn);
            if (temp != null && !temp.contains(index)) {
                temp.add(index);
            }
        }
    }
}

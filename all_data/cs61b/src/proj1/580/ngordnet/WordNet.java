package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.Scanner;

import java.util.Set;

//reference: MethodSignature file provided in the skeleton 

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */


    private SynMap dictionary;


    public WordNet(String synsetFilename, String hyponymFilename) {
        dictionary = new SynMap(synsetFilename);
        In reader = new In(hyponymFilename);
        String curr;
        Scanner linescanner;
        int currid;
        SynNode cnode;
        String currname;
      
        while (reader.hasNextLine()) {
            curr = reader.readLine();
            linescanner = new Scanner(curr).useDelimiter(",");
            currid = linescanner.nextInt();
            cnode = dictionary.get(currid);       
            while (linescanner.hasNextInt()) {
                SynNode hypo = dictionary.get(linescanner.nextInt());
                cnode.add(hypo);                             
            }
        }


    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (noun.split(" ").length > 1) {
            return false;
        }
        for (SynNode curr: dictionary.values()) {
            if (curr.syn.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return removeRepeat(dictionary.nouns);

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet<String>();
        for (SynNode temp: dictionary.values()) {
            if (temp.syn.contains(word)) {
                result.add(temp.syn);
                temp.children();
                for (SynNode temp1: SynNode.allhypo) {
                    result.add(temp1.syn);
                }
            }
        }
        SynNode.allhypo.clear();
        return removeRepeat(result);
    }

    //removeRepeat remove repeated noun in set, and separte synonym.
    private Set<String> removeRepeat(Set<String> original) {
        HashSet<String> result = new HashSet<String>();
        Scanner s1;
        for (String e: original) {
            s1 = new Scanner(e);
            while (s1.hasNext()) {
                String t = s1.next();
                if (!result.contains(t)) {
                    result.add(t);
                }
            }
        }
        return result;
    }
}

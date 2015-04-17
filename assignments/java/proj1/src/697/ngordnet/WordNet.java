package ngordnet;
import java.util.*;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;


public class WordNet {
	private HashMap<Integer,Object[]> synset;
	private HashMap<Integer, int[]> hyposet;
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    
    public WordNet(String synsetFilename, String hypernymFilename) {
    	In syn_content = new In(synsetFilename);
        In hyp_content = new In(hypernymFilename);
    	synset = sortSyn(syn_content);
    	hyposet = sortHyp(hyp_content);
    }


    private HashMap<Integer, Object[]> sortSyn(In in) {
        HashMap<Integer, Object[]> synset_map = new HashMap<Integer, Object[]>();
        while (in.hasNextLine()) {
            String thisLine = in.readLine();
    	    String[] tokens = thisLine.split(",");
            int id = Integer.parseInt(tokens[0]);
            String hyposet = tokens[1];
            String[] hyponym_arr = hyposet.split(" ");
            String[] def = new String[tokens.length-2];
            System.arraycopy(tokens, 2, def, 0, tokens.length-2);
            int i = 0;
            String definition = "";
            while (i < def.length) {
                definition += def[i];
                definition += " ";
                i++;
            }
            Object[] hyp_val = new Object[2];
            hyp_val[0] = hyponym_arr;
            //we secure the definition
            hyp_val[1] = definition;
            synset_map.put(id, hyp_val);            
    	}
		return synset_map;
    }

    private HashMap<Integer, int[]> sortHyp(In in) {
            HashMap<Integer, int[]> hypset_map = new HashMap<Integer, int[]>();
            while (in.hasNextLine()) {
                String thisLine = in.readLine();
                String[] hyp_set = thisLine.split(",");
                int key = Integer.parseInt(hyp_set[0]);
                String[] x = new String[hyp_set.length-1];
                System.arraycopy(hyp_set, 1, x, 0, x.length);
                int[] hippocito = new int[x.length];
                int count = 0;
                for (String num : x) {
                    hippocito[count] = Integer.parseInt(num);
                    count++;
                }

                hypset_map.put(key, hippocito);
                // else {
                //     int[] x = new int[1];
                //     x[0] = Integerhyp_set[1];
                //     hypset_map.put(key, x);
            }
		return hypset_map;
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
    	for (int i = 0; i < this.synset.size(); i++) {
    		Object[] val = synset.get(i);
    		String[] synsetito = (String[]) val[0];
    		for (int j = 0; j < synsetito.length; j++) {
    			if (noun.equals(synsetito[j])) {
    				return true;
    			}
    		}		
    	}
    	return false;
    }

    /* Returns the set of all nouns. 
	 * add(E e) :
	 * Adds the specified element to this set if it is not already present (optional operation).*/
    public Set<String> nouns() {
    	Set<String> nouns = new HashSet<String>(); 
    	for (int i = 0; i < this.synset.size(); i++) {
    		Object[] val = this.synset.get(i);
    		String[] hyponyms = (String[]) val[0];
    		for (int j = 0; j < hyponyms.length; j++) {
    			nouns.add(hyponyms[j]);
    		}
    	}
    	return nouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
    	Set<String> hypohash = new HashSet<String>(); 
    	int wordId = 0;
    	int[] hypNums;
    	//add itself
        hypohash.add(word);
        /** this forloop is to find the word's hyponym id numbers */
        for (int i = 0; i < this.synset.size(); i++) {
            //check if the word matchs
            String[] x = (String[]) this.synset.get(i)[0];
            //this.synset.get(i)[0][0] is the word
            if (word.equals(x[0])) {
                wordId = i;
                //I'm saving the hyponyms
                break;
            }
        }
        hypNums = this.hyposet.get(wordId);
        for (int i = 0; i < hypNums.length; i++) {        
    		Object[] val = this.synset.get(hypNums[i]);
    		String[] hyps = (String[]) val[0];
    		for (int j = 0; j < hyps.length; j++) {
                hypohash.add(hyps[j]);
    		}
    	}
    	// //what we have here is the ids of our hyponyms
    	// hypNums = this.hyposet.get(wordId);
    	// for (int k = 0; k < hypNums.length; k++) {
    	// 	int hId = hypNums[k];
    	// 	String[] hypocitos = (String[]) this.synset.get(hId)[0];
    	// 	for (String noun : hypocitos) {
    	// 		hypohash.add(noun);
    	// 	}
    	// }
    	return hypohash;
    }
}


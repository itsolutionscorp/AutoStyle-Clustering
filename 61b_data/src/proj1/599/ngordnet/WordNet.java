package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* This class will use a Digraph under the hood
 to store a graph of all synsets to their hyponyms.  */

public class WordNet {
	private In synset_file;
	private In hyponym_file;

	private Digraph graph_of_hyponyms;
	private List<java.lang.String> list_of_synsets;
	private Map<String, Integer> synsetToNumberMap;
	private Map<String, List<Integer>> wordToSynsetNumbersMap;

	public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
		synset_file = new In(synsetFilename);		// Use In to read in the two files and store the In object as instance variables
		hyponym_file = new In(hyponymFilename);
		list_of_synsets = new ArrayList<java.lang.String>();			// Initalize list_of_synsets, an instance variable, to be an empty Arraylist
		synsetToNumberMap = new TreeMap<String, Integer>();					// Initialize synsetToNumberMap be an empty list.  It will map synsets to their index in the file
		wordToSynsetNumbersMap = new TreeMap<String, List<Integer>>();

		/* Add each synset to list_of_synsets, and map each synset to its index from the file with synsetToNumberMap. 
		Then, map each word to a list of numbers whose synsets the word appears in, with wordToSynsetNumbersMap.
		*/
		while (synset_file.hasNextLine()) {							
			String line = synset_file.readLine();
			String[] comma_split_list = line.split(",");
			
			int index_of_synset = Integer.parseInt(comma_split_list[0]);
			String synset_of_line = comma_split_list[1];
			
			list_of_synsets.add(synset_of_line);
			synsetToNumberMap.put(synset_of_line, index_of_synset);

			String[] wordsOfLine = synset_of_line.split(" ");		// we map each word to a list of numbers whose synsets the word appears in.
			for (String word : wordsOfLine) {
				if (!wordToSynsetNumbersMap.containsKey(word)) {
					wordToSynsetNumbersMap.put(word, new ArrayList<Integer>());
				}
				wordToSynsetNumbersMap.get(word).add(index_of_synset);
			}
			
			
		}

		graph_of_hyponyms = new Digraph(list_of_synsets.size());		// Initialize graph_of_hyponyms, a digraph instance variable, to be a graph with as many vertices as there are synsets in the input file
		while (hyponym_file.hasNextLine()) {							// Traverse the hyponym file, creating an edge between every parent-child pair of synsets
			String numbers = hyponym_file.readLine();
			String[] comma_split_numbers = numbers.split(",");
			String parentNumberString = comma_split_numbers[0];
			int parentInt = Integer.parseInt(parentNumberString);
			for (int i = 1; i < comma_split_numbers.length; i += 1) {
				int childInt = Integer.parseInt(comma_split_numbers[i]);
				graph_of_hyponyms.addEdge(parentInt, childInt);
			}
		}	
	}

	




	// Returns whether NOUN is a noun
	public boolean isNoun(java.lang.String noun) {
		if (wordToSynsetNumbersMap.containsKey(noun)) {
			return true;
		}
		return false;
	}
	
	// Returns a set of all the nouns in this wordnet
	public Set<java.lang.String> nouns() {
		Set<String> nounSet = new TreeSet<String>();
		for (String synset : list_of_synsets) {				// Examine each word in each synset
			String[] listifiedSynset = synset.split(" ");
			for (String word: listifiedSynset) {
				if (isNoun(word)) {
					nounSet.add(word);
				}
			}
		}
		return nounSet;
	}

	// Returns a set of all *words* (not synsets) that descend from the input WORD (the input is a word not a synset)
	public Set<java.lang.String> hyponyms(java.lang.String word) {
		Set<Integer> appearanceIndicesOfWord = new TreeSet<Integer>(wordToSynsetNumbersMap.get(word));			// get the set of indices that WORD appears in
		List<String> listOfDescendantSynsets = new ArrayList<String>();									// initialize listOfChildren
		
		Set<Integer> setOfDescendantIndices = GraphHelper.descendants(graph_of_hyponyms, appearanceIndicesOfWord);			// this returns a set of indices of all the descendants of all the parents
		
		for (int number : setOfDescendantIndices) {											// fill listOfChildren with the descendants corresponding to the indices in setOfChildrenIndices
			listOfDescendantSynsets.add(list_of_synsets.get(number));
		}

		Set<String> setOfDescendantWords = new TreeSet<String>();					// for each synset in listOfDescendantSysnets, we add each constituent word to the setOfDescendantWords, and return this set
		for (String synset: listOfDescendantSynsets) {
			String[] listifiedSynset = synset.split(" ");
			for (String w : listifiedSynset) {
				setOfDescendantWords.add(w);
			}
		}
		return setOfDescendantWords;			// return the set of descendant words
	}
	
}
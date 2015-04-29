package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import ngordnet.GraphHelper;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class WordNet {
  private Digraph myWordNet; // will show relationship between indecise of strings
	private String[][] nameList; // will hold Synsets (String[])  at their appropraite index
	private int nameListLength;
																

  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
	public WordNet(String synsetFilename, String hyponymFilename) {
		// first, we'll setup our nameList
		nameList = new String[1][]; // initialize to hold 1. We'll dynamically size it later
    In synsets = new In(synsetFilename);
		if (!synsets.exists()) { // if the synsets doesn't exist (bad filename)
			throw new IllegalArgumentException("synset filename does not exist");
		}
		int i = 0; // will use this to know how big our Diagraph should be and to index nameList
		int j = 0; // will be the second index for nameList
		String curLine;  // will store our current line
		int startOfWord; // will tell us the index of the start of the current word
		int endOfWord;   // will tell us the index of the end of the current word
		int secondComma; // will store where our next comma is, so we can index from there
		String[] syn; // will store our list of synsets
		while(!synsets.isEmpty()) {
      if (i >= nameList.length) {
        // let it be dynamically sized
				nameListExtender(nameList.length*2); // call our function to extend
			} 
			nameList[i] = new String[1]; // initialize at length 1
      curLine = synsets.readLine();
			startOfWord = curLine.indexOf(",", 0)+1; /* find the first index of first word
                                                * (will be one after the comma) */
			secondComma = curLine.indexOf(",", startOfWord); // find the second comma
      // as long as we haven't gone past the 2nd comma or found an incorrect startOfWord
			while ((startOfWord < secondComma)||(startOfWord != -1)) { 
				if (j >= nameList[i].length) { // if we need to dynamically resize
					nameList[i] = stringListExtender(nameList[i], nameList[i].length*2);
				}
        endOfWord = curLine.indexOf(" ", startOfWord);
      	// if we couldn't find a space before the second comma or at all
				if ((endOfWord > secondComma)||(endOfWord == -1)) { 
					nameList[i][j] = curLine.substring(startOfWord, secondComma);
					break;
				}else{
					nameList[i][j] = curLine.substring(startOfWord, endOfWord);
					j += 1; // incrememnt j
					startOfWord = endOfWord + 1; // the char after the space will be the next word
				}
			}
			i += 1; // increment what line we're on
			j = 0;  // reset j
		}
		synsets.close();

		nameListLength = i; // we'll use this to know the length later
    this.myWordNet = new Digraph(i); // make Diagraph of size i
		In hypnonymFile = new In(hyponymFilename);
		if (!hypnonymFile.exists()) { // if hypnonymFile doesn't exist (bad filename)
			throw new IllegalArgumentException("hyponym filename does not exist");
		}
		int startOfIndex; // will tell us the start of the index
		int nextComma; // will tell us where the next comma is
		int hyper; // will store the index of our hypernym
		int hypo;  // will store the index of our hyponym
		while(!hypnonymFile.isEmpty()) {
      curLine = hypnonymFile.readLine();
      nextComma = curLine.indexOf(",", 0); // gives us the next comma
			// next, we'll find the hypernym index
      hyper = Integer.parseInt(curLine.substring(0, nextComma)); 
			while (true) { // until we hit the break
				startOfIndex = nextComma+1;
				nextComma = curLine.indexOf(",", nextComma+1);
				if (nextComma == -1) {
					hypo = Integer.parseInt(curLine.substring(startOfIndex, curLine.length()));
					myWordNet.addEdge(hyper, hypo);
					break;
				}
				hypo = Integer.parseInt(curLine.substring(startOfIndex, nextComma));
        myWordNet.addEdge(hyper, hypo);
			}
		}
	}

  /* Returns true if NOUN is a word in some synset. */
	public boolean isNoun(String noun) {
    for(int i = 0; i<nameListLength; i++) {
			for(int j = 0; j<(nameList[i]).length; j++) {
				if (nameList[i][j] != null) {
				  if (noun.compareTo(nameList[i][j]) == 0) { // if the strings are equal
				  	return true;
				  }
				}
			}
		}
		return false;
	}

  /* Returns the set of all nouns. */
	public Set<String> nouns() {
		Set<String> ret = new TreeSet<String>();  
		for(int i = 0; i<nameListLength; i++) {
			for(int j = 0; j<nameList[i].length; j++) {
				if (nameList[i][j] != null) {
				  ret.add(nameList[i][j]);
				}
			}
		}
		return ret;
	}

	  /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
  public Set<String> hyponyms(String word) {
    // first, we need to find the indexes of word in nameList
		int[] wordIndex = getIndexes(word);
		if (wordIndex[0] == -1) {
			throw new IllegalArgumentException("hyponyms argument is not present in list");
		}
		Set<String> ret = new TreeSet<String>(); // we'll return this
		for (int index : wordIndex) {
			if (index == -1) {
				break;
			}
		  Set<Integer> indexSet = new TreeSet<Integer>(); // we'll use this to find descendants
  		indexSet.add(index);
  		for (int i : GraphHelper.descendants(myWordNet, indexSet)) {
  			for (String str : nameList[i]){
  				if (str == null) {
  					break;
  				}
  				ret.add(str);
  			}
  		}
		}
		return ret;
	}

	// returns the index of the word within nameList
  private int[] getIndexes(String word) {
		// initialize these at 1
		int[] indexList = new int[1];
		indexList[0] = -1; // will let us check for errors
		int indexListIndex = 0;
		for (int i = 0; i < nameListLength; i+=1) {
			// dynamic allocation
			if (indexListIndex >= indexList.length) {
				indexList = Arrays.copyOf(indexList, indexList.length*2);
				// fills new spots with -1, to check for errors
				Arrays.fill(indexList, indexList.length/2, indexList.length, -1); 
			}
			for(int j = 0; j < nameList[i].length; j+=1){
				if (nameList[i][j] == null) {
					break;
				}
				if (word.compareTo(nameList[i][j]) == 0)  {
					indexList[indexListIndex] = i;
					indexListIndex += 1;
				}
			}
		}
		return indexList; // we couldn't find it
	}

	private void nameListExtender(int newSize){
		String[][] temp = new String[newSize][];
		for (int i = 0; i < nameList.length; i++) {
			temp[i] = nameList[i];
		}
		nameList = temp;
	}

	// copies the old string array to a longer array
	private String[] stringListExtender(String[] oldList, int newSize){
		String[] ret = new String[newSize];
		for (int i = 0; i < oldList.length; i++) {
			ret[i] = oldList[i];
		}
		return ret;
	}

}

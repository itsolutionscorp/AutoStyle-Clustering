package ngordnet;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class WordNet {
	private Map<String, String> synset;
	private List<String[]> hyponyms;
	private Set<WordNode> allWordNodeList;
	//private Set<WordNode> wordNodeTree;


	public WordNet(String syns, String hypon) {
		/** This is the constructor for the WordNet class.
		  * What this is supposed to do is take in the files
		  * and make a graph out of the given arguments.
		  */
		try {
			Path synset_file = Paths.get(syns);
			Path hypo_file = Paths.get(hypon);
			getSynset(synset_file);
			getHyponyms(hypo_file);
			makeBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isNoun(String word) {
		//actually returns if the word passed in is in the synset
		boolean bool = synset.containsValue(word);
		return bool;
	}

	public Set<String> hyponyms(String word) {
		//for a given word, return its hyponyms
		Set<String> returns = new HashSet<String>();
		if (isNoun(word)) {
			WordNode temp = new WordNode(word);
			Iterator<WordNode> x = allWordNodeList.iterator();
			while (x.hasNext()) {
				WordNode wntemp = x.next();
				System.out.println(wntemp.word);
				if (WordNode.compareNodes(temp, wntemp)) {
					System.out.println(WordNode.compareNodes(temp, wntemp));
					wntemp.addHyponyms(returns);
				}
			}
		}
		return returns;
	}

	public Set<String> nouns() {
		//returns all nouns in the WordNet
		Iterator<String> x = synset.values().iterator();
		Set<String> nouns = new HashSet<String>();
		while (x.hasNext()) {
			String temp = x.next();
			nouns.add(temp);
		}
		return nouns;
	}

	private void getHyponyms(Path filename) throws IOException {
		//This method should parse through the file given
		//And update the files
		//Based on the code found here: 
		//"http://stackoverflow.com/questions/15903170/read-and-split-a-text-file-java"

		Scanner sc = new Scanner(filename);
		hyponyms = new ArrayList<String[]>();
		while(sc.hasNextLine()) {
			String[] relation = sc.nextLine().split(",");
			hyponyms.add(relation);
		}
	}

	private void getSynset(Path filename) throws IOException {
		//Look above for a similar implementation
		Scanner sc = new Scanner(filename);
		synset = new HashMap<String, String>();
		while(sc.hasNextLine()) {
			String nextline = sc.nextLine();
			String[] relation = nextline.split(",");
			synset.put(relation[0], relation[1]);
		}	
	}

	private void makeBase() {
		//This method uses the files hyponyms and synsets to
		//create a Set of WordNodes that will later be interated
		//through to create a full web

		allWordNodeList = new HashSet<WordNode>();
		for(int i = 0; i<hyponyms.size()-1; i+= 1) {
			String[] temp = new String[hyponyms.get(i).length];
			for(int j = 0; j<hyponyms.get(i).length-1; j+=1) {
				temp[j] = synset.get(hyponyms.get(i)[j]);
			}
			ArrayList<WordNode> wntemp = new ArrayList<WordNode>();
			WordNode parent = new WordNode(temp[0]);
			for(int k = 1; k<temp.length-1; k+=1) {
				WordNode wn = new WordNode(parent, temp[k]);
				wntemp.add(wn);
				allWordNodeList.add(wn);
			}
			parent.hypo = wntemp;
			allWordNodeList.add(parent);
		}
	}

	// private void makeConnections() {
	// 	//
	// }

	public static void main(String[] args) throws IOException {
		//Construct the WordNet
		// assert (args.length == 2) : "Please input two files";
		// WordNet wn = new WordNet(args[0], args[1]);


	}

	/*************** Here lies the new nested class *************************/

	public static class WordNode {
		public WordNode parent;
		public String word;
		public List<WordNode> hypo;

		private WordNode(String word) {
			this.parent = null;
			this.word = word;
			this.hypo = null;
		}

		private WordNode(WordNode parent, String word) {
			this.parent = parent;
			this.word = word;
			this.hypo = null;
		}

		private WordNode(WordNode parent, String word, List hypo) {
			this.parent = parent;
			this.word = word;
			this.hypo = hypo;
		}

		public static boolean compareNodes(WordNode wna, WordNode wnb) {
			//compares two nodes to see if they are equal
			if (wna.word.equals(wnb.word)) {
				return true;
			} else return false;
		}

		public void addHyponyms(Set<String> someset) {
			if (this.hypo == null) {
				System.out.println("did nothing");
				//Do Nothing
			} else {
				Iterator<WordNode> x = this.hypo.iterator();
				System.out.println(x.next());
				while (x.hasNext()) {
					WordNode blahblah = x.next();
					System.out.println(blahblah);
					someset.add(blahblah.word);
				}
			}
		}
	}

}

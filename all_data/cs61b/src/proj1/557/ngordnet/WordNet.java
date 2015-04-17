package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.*;
import edu.princeton.cs.introcs.In;


public class WordNet {

	private ArrayList<String[][]> WordNetDict = new ArrayList<String[][]>();
	private Digraph HyponymNet;

	private ArrayList<String> isNounList;
	private ArrayList<String> notNounList;

	public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename){
		In synsetPlaceHolder = new In(synsetFilename);
		In hyponymPlaceHolder = new In(hyponymFilename);

		String[] synsetFileString = synsetPlaceHolder.readAllLines();
		String[] hyponymFileString = hyponymPlaceHolder.readAllLines();

		String[] individualSynset;
		String[] words;
		String[] def ;
		String[][] pkg;
		String[] wordsList;

		String[] individualHyponymLine;
		isNounList = new ArrayList<String>();
		notNounList = new ArrayList<String>();

		for (int i = 0 ; i < synsetFileString.length ; i++){
			individualSynset = synsetFileString[i].split(",");

			words = individualSynset[1].split(" ");
			def = new String[]{individualSynset[2]} ;

			pkg = new String[][]{words, def};
			WordNetDict.add(pkg);
		}

		HyponymNet = new Digraph(WordNetDict.size());
		int a ;
		int b ;
		for(int j = 0 ; j < hyponymFileString.length ; j++ ){
			individualHyponymLine = hyponymFileString[j].split(",");
			a = Integer.parseInt(individualHyponymLine[0]);
			for (int k = 1 ; k < individualHyponymLine.length ; k++){
				b = Integer.parseInt(individualHyponymLine[k]);
				HyponymNet.addEdge(a, b);
			}

		}  
	}

	public boolean isNoun(java.lang.String noun){
		if (isNounList.contains(noun)){
			return true;
		}
		if (notNounList.contains(noun)){
			return false;
		}                                        
		for (int i = 0 ; i < WordNetDict.size(); i ++){
			if(Arrays.asList(WordNetDict.get(i)[0]).contains(noun)){
				isNounList.add(noun);
				return true;
			}
		}
		notNounList.add(noun);

		return false;
	}

	public java.util.Set<java.lang.String> nouns(){
		Set<String> result = new HashSet<String>();
		String[][] currpkg;
		for (int i = 0 ; i < WordNetDict.size(); i++){
			currpkg = WordNetDict.get(i);
			for (int j = 0 ; j < currpkg[0].length ; j++){
				result.add(currpkg[0][j]);
			}

		}
		return result;
	}

	public java.util.Set<java.lang.String> hyponyms(java.lang.String word){
		Set<Integer> result = new HashSet<Integer>();
		String[][] currpkg;
		String[] currSet;
		String[] currHypSet;
		Set<Integer> sameWord = new TreeSet<Integer>();
		Set<String> resultInWords = new TreeSet<String>();

		for (int id = 0 ; id < WordNetDict.size(); id++){
			currpkg = WordNetDict.get(id);
			if(Arrays.asList(currpkg[0]).contains(word)){
				result.add(id);

			}

		}
		Set<Integer> AllHyponyms = GraphHelper.descendants(HyponymNet, result);
		for (int iid : result){
			currSet = WordNetDict.get(iid)[0];
			for (String k : currSet){
				resultInWords.add(k);

			}

		}
		for (int hypid : AllHyponyms){
			currHypSet = WordNetDict.get(hypid)[0];
			for (String j : currHypSet){
				resultInWords.add(j);

			}

		}
		return resultInWords;
	}
	
	public static void main(java.lang.String[] args){

	}
}


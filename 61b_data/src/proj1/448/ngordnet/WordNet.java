package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import ngordnet.GraphHelper;
import ngordnet.wm;
import java.util.ArrayList;
import java.util.List;
import ngordnet.GraphHelper;
import java.util.HashSet;

public class WordNet {
	private List<wm> data = new ArrayList<wm>();
	private Digraph graph;

	public WordNet (String file1, String file2){
		In stream1 = new In(file1);
		In stream2 = new In(file2);

		while (true){
			try{
				String line = stream1.readLine();
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);

				wm thisDate = new wm(tokens[1], tokens[2]);

				data.add(id, thisDate);

			}catch(NullPointerException e){
				break;
			}
		}

		graph = new Digraph(data.size());

		while (true){
			try{
				String line = stream2.readLine();
				String[] tokens = line.split(",");

				int root = Integer.parseInt(tokens[0]);

				for (String sbranch : tokens){
					int branch = Integer.parseInt(sbranch);
					if (branch!= root){
						graph.addEdge(root, branch);
					}
				}
			}catch(NullPointerException e){
				break;
			}
		}
	}

	public boolean isNoun(String s3){
		List<String> testList;
		for (wm elem : this.data){
			testList = elem.getWord();
			for (String elementString : testList){
				if (elementString.equals(s3)){
					return true;
				}
			}
		}
		return false;
	}

	public Set<String> nouns(){
		Set<String> returnList = new HashSet<String>();
		for (wm elem : this.data){
			for (String elementString : elem.getWord()){
				returnList.add(elementString);
			}
		}
		return returnList;
	}

	public Set<String> hyponyms(String s4){
		Set<String> returnList = new HashSet<String>();

		List<String> testList;
		List<wm> testElems = new ArrayList<wm> ();
		
		for (wm elem : this.data){
			testList = elem.getWord();
			for (String elementString : testList){
				if (elementString.equals(s4)){
					testElems.add(elem);
				}
			}
		}

		if (testElems.size() == 0){
			System.out.println(s4+" is not in the datebase");
			return null;
		}

		Set<Integer> rootInt = new TreeSet<Integer>();

		for (wm testElem : testElems){
			rootInt.add(this.data.indexOf(testElem));
		}
		
		Set<Integer> tempTree = GraphHelper.descendants(this.graph, rootInt);

		wm testElem;
		for (int eachIndex : tempTree){
			testElem = this.data.get(eachIndex);
			for (String elementString : testElem.getWord()){
				returnList.add(elementString);
			}
		}

		return returnList;
	}
}
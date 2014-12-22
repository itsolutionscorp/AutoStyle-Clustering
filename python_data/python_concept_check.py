import sys
import os
from collections import defaultdict
import betterast

concepts = ["Recursion", "NestedLoops", "Call", "Continue", "AugAssign", "Exec", "Global", "Yield", "TryFinally", "ClassDef", "With", "Nonlocal", "BinOp", "UnaryOp", "Lambda", "IfExp", "Dict", "Set", "ListComp", "DictComp", "GeneratorExp", "YieldFrom", "Tuple", "List", "In","If", "SetComp"]
concept_descriptors = {"Continue":"Use of continue keyword"
 ,"AugAssign":"Use of an augmented assignment"
 ,"Exec":"Use of the exec statement"
 ,"Global":"Use of the global construct"
 ,"Yield":"Use of yield"
 ,"TryFinally":"Use of finally"
 ,"ClassDef":"Class definition"
 ,"With":"Use of the with statement"
 ,"Nonlocal":"Use of nonlocal"
 ,"BinOp":"Use of a binary operation"
 ,"UnaryOp":"Use of a unary operation"
 ,"Lambda":"Use of a lambda function"
 ,"IfExp":"Use of an if expression"
 ,"Dict":"Use of a dictionary"
 ,"Set":"Use of a set"
 ,"ListComp":"Use of list comprehension"
 ,"DictComp":"Use of dictionary comprehension"
 ,"GeneratorExp":"Use of a generator expression"
 ,"YieldFrom":"Use of yield from"
 ,"Tuple":"Use of tuples"
 ,"List":"Use of a list"
 ,"If":"Use of an if statement"
 ,"SetComp": "Use of set comprehension"
 ,"In": "Use of the in construction"}

def loop_descriptors(i):
	if i == 0:
		return "no"
	if i == 1:
		return "single"
	if i == 2:
		return "double nested"
	if i == 3:
		return "triple nested"
	if i == 4:
		return "quadruple nested"
	return "extremely deeply nested"

def groupby(mylist):
	group = defaultdict(int)
	grouped = []
	for item in mylist:
		group[item]+=1
	for item in group:
		grouped.append(item)# + "  (x" + str(group[item]) + ")")
	# if len(grouped) > 1 and "Use of a single loop" in group:
	# 	# print "Use of a single loop" + "  (x" + str(group["Use of a single loop"]) + ")"
	# 	grouped.remove("Use of a single loop" + "  (x" + str(group["Use of a single loop"]) + ")")
	return grouped

def tree_iter(node):
	func_defs = []
	recursions = set()
	calls = []
 	queue = [node]
 	loops = []
 	loop_tracker = []
 	total_loops=0;
 	loop_count = 0
 	while len(queue) > 0:
 	   	temp_node = queue.pop(0)
 	   	label = temp_node.label
 	   	if temp_node in loop_tracker:
 	   		loop_tracker.remove(temp_node)
 	   		if len(loop_tracker) == 0:
 	   			loops.append("Use of a " + loop_descriptors(loop_count) + " loop")
 	   			loop_count = 0
 	   	if label[0:11] == 'FunctionDef':
 	   		func_defs.append(label[12:]) 
 	   	elif label in func_defs:
			recursions.add("Recursion on " +label)
		elif label == "Call":
			if temp_node.children[0].label not in func_defs:
				calls.append("Call to "+temp_node.children[0].label)
		elif label == "For" or label == "While":
			if len(queue) > 0:
				loop_tracker.append(queue[0])
			loop_count+=1
			total_loops+=1;
 	   	queue = temp_node.children + queue
 	if loop_count > 0:
 		loops.append("Use of a " + loop_descriptors(loop_count) + " loop")
 	loops = groupby(loops)
 	loops.append("Use of " + str(total_loops) + " loop(s) in total.")
 	return groupby(calls) + loops + list(recursions)


def concept_check(code, tree):
	matches = []
	matches += tree_iter(tree)
	for concept in concepts[3:]:
		if concept in code:
			matches.append(concept_descriptors[concept])
	return matches

def main():
	home = sys.argv[1].rstrip("/")
	directory = home+ "/ast_text/"

	files = [directory+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+f))]

	for f in files:
		lines = open(f, "r").readlines()
		lines = [i.strip("\n") for i in lines]
		treelines = [(int(i.split(":")[0]), ''.join(i.split(":")[1:])) for i in lines]
		lines = [n.split(":")[1] for n in lines]
		# print treelines
		tree = betterast.build_tree(treelines)
		matches = concept_check(lines, tree)
		sol = os.path.split(f)[1]
		f2 = open(home + "/concepts/" + sol, "w")
		matches = [item + "\n" for item in matches]
		f2.writelines(matches)
		print "Done: " + str(f)

if __name__ == '__main__':
	if len(sys.argv) != 2:
		print >> sys.stderr, "Usage: python_chain.py <path to home directory>"
		exit(0)
	main()

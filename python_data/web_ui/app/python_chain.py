import sys
import os
import numpy as np
from collections import defaultdict



def chain(visualize, start, home):
	directory = home+ "/concepts/"

	files = [directory+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+f))]

	concepts = {}
	for f in files:
		lines = open(f, "r").readlines()
		lines = [i.strip("\n") for i in lines]
		sol = os.path.split(f)[1].strip(".txt")
		concepts[sol] = lines

	scores = []
	scores_reverse = {}

	directory = home+ "/abc_score/"
	files = [directory+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+f))]
	for f in files:
		score = open(f, "r").readline().rstrip("\n")
		sol = os.path.split(f)[1].strip(".txt")
		scores.append((float(score),sol))
		scores_reverse[sol] = float(score)

	m = np.loadtxt(home+"/distmatrix/python_dist_matrix", delimiter=" ")
	min_abc = min(scores)[0]

	soln = start
	chain = [soln]
	diffs = []
	sortedscores = sorted(scores)

	rejected_concepts = set()

	##################### HELPER FUNCTIONS #####################
	def difference(val,soln):
		s_val = set(concepts[val])
		s_soln = set(concepts[soln])
		if len(s_val.intersection(s_soln)) > 0 and len(s_soln.symmetric_difference(s_val)) > 0 and len(s_soln.symmetric_difference(s_val)) < 5 and len(s_val.intersection(rejected_concepts)) <2:
			return 1
		return 0

	def find_min(soln_list, soln):
		i, lowest = int(soln_list[-1]), m[int(soln)][int(soln_list[-1])]
		for option in soln_list[::-1]:
			editdist = int(m[int(soln)][int(option)])
			diff = difference(option,soln)
			if editdist < lowest and diff:
				i,lowest = int(option), editdist
		rejected_concepts.union(set(concepts[str(i)]).difference(set(concepts[soln])))
		diffs.append(concepts[soln])
		return str(i)

	def get_better(soln):
		val = scores_reverse[soln]
		index = [i for i,item in enumerate(sortedscores) if item[0]  == val and item[1] == soln][0]
		while index > 0 and val - sortedscores[index][0] < (0.1*val):
			index-=1
		k = 0
		while index-k > 0 and val - sortedscores[index-k][0] < (0.5*val):
			k+=1
		matches = [i[1] for i in sortedscores[index-k:index+1]]
		# print sortedscores[index-k:index+1]
		better = find_min(matches, soln)
		return better

	#################### END HELPER FUNCTIONS #######################

	while scores_reverse[soln] > min_abc:
		cur = scores_reverse[soln]
		soln = get_better(soln)
		if soln == None:
			break
		chain.append(soln)
		if (cur,soln) in sortedscores:
			sortedscores.remove((cur,soln)) 


	chain_concepts = []
	for i,item in enumerate(chain):
		if not int(visualize):
			print("[--- score: " + str(scores_reverse[item]) + "  --  id: "+str(item)+" ---]")
			print concepts[item]
			chain_concepts += [c for c in concepts[item]] 
			print open(home+"/code/"+item + ".py", "r").read()
		elif int(visualize) == 1:
			if i != len(chain)-1:
				print (str(item) + ' -> '+ str(chain[i+1]) + ";")
	if int(visualize) == 2:
		print len(chain)
		for item in chain:
			print item

	chain_concepts = set(chain_concepts)
	concept_weights = {}
	# k = [-20,-10,-6, -3, -1, 1,3,6,10,20]
	def y(x,k):
		x -= k/2
		return (x^3)

	# def f(x,k):


	for concept in chain_concepts:
		weight = 0
		for i,item in enumerate(chain):
			if concept in concepts[item]:
				# weight +=(k[i])
				weight +=y(i, len(chain))
				# if i < len(chain)*0.5:
				# 	break
		concept_weights[concept] = weight
	
	cw = sorted(concept_weights, key=concept_weights.get)[::-1]
	for item in cw:
		print item+ ": ", concept_weights[item]

	print "\n================== FEEDBACK ==================="
	count = 1
	i=0
	print "\nInclude/keep the following in your solution:"
	while i < len(cw) and count <= 2:
		if cw[i] in concepts[chain[1]] or (cw[i] in concepts[chain[0]] and cw[i] in concepts[chain[len(chain)-1]]):
			print "	-", cw[i]
			count+=1
		i+=1;

	cwr = cw[::-1]
	count = 1
	limit = len(cw)
	i = 0
	print "\nRemove the following from your solution:"
	while i < limit and count <= 2:
		if cwr[i] in concepts[chain[0]] and (cwr[i] not in concepts[chain[1]] or cwr[i] not in concepts[chain[2]]):
			print "	-", cwr[i]
			count+=1
		i+=1;		
	print "\n==============================================="
if __name__ == '__main__':
	start = sys.argv[2]
	home = sys.argv[1].rstrip("/")
	if len(sys.argv) == 3:
		chain(0, start,home)
	elif len(sys.argv) == 4:
		chain(sys.argv[3], start,home)
	else:
		print >> sys.stderr, "Usage: python_chain.py <path to home directory> <starting submission>"

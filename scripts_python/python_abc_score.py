import sys
import os
import math
import operator
import re

def loop_score(i):
	multiplier = 1 #int(re.findall(r'\d+', i)[0])
	i = i[:i.index("loop")-1]
	if i == "Use of no":
		return 0
	if i == "Use of a single":
		return 0
	if i == "Use of a double nested":
		return 3 * multiplier
	if i == "Use of a triple nested":
		return 5 * multiplier
	if i == "Use of a quadruple nested":
		return 7 * multiplier
	return 9 * multiplier

def main(home_dir):
	directory = home_dir + "/ast_text/"
	if not os.path.exists(home_dir+"/"+"abc_score"):
		os.makedirs(home_dir+"/"+"abc_score")
	files = [directory+"/"+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+"/"+f))]
	scores = {}
	code = {}
	for f in files:
		lines = open(f, "r").readlines()
		lines = [i.strip("\n") for i in lines]
		lines = [n.split(":")[1] for n in lines]
		code[os.path.split(f)[1].strip(".txt")] = lines

	concepts = {}
	directory = home_dir+ "/concepts/"
	files = [directory+f for f in os.listdir(directory) if (not f.startswith('.') and not os.path.isdir(directory+f))]
	for f in files:
		lines = open(f, "r").readlines()
		lines = [i.strip("\n") for i in lines]
		loops = sum([loop_score(i) for i in lines if 'loop' in i and 'loop(s)' not in i])
		recursion = sum([1 for i in lines if 'Recursion' in i])
		sol = os.path.split(f)[1].strip(".txt")
		concepts[sol] = recursion + loops
	for i in code:
		assign, branch, cond, calls = 0,0,0,0
		for key in code[i]:
			if key in ["Assign","AugAssign"]:
				assign+=1
			elif key in ["Call", "In"]:
				calls+=1
			elif key in ["Call", "While", "For", "Raise", "Break"]:
				branch+=1
			elif key in ["Compare","Try", "ExceptHandler"]:
				cond+=1
		cpts = concepts[i]
		score = round(math.sqrt(branch*branch + assign*assign + cond*cond + 0.4*(calls*calls) + cpts*cpts),2)
		scores[i] = score
		open(home_dir+"/"+"abc_score/"+str(i) + ".txt", "w").write(str(score))
	summary = open(home_dir+"/"+"abc_score_summary/summary.txt", "w")
	for key in scores:
		summary.write(str(key) + ": " + str(scores[key]) + "\n")
	max_key = max(scores.iteritems(), key=operator.itemgetter(1))[0]
	min_key = min(scores.iteritems(), key=operator.itemgetter(1))[0]
	summary.write("min: " + str(scores[min_key]) + "   (soln " + str(min_key)+ ")\n")
	summary.write("max: " + str(scores[max_key]) + "   (soln " + str(max_key)+ ")\n")
if __name__ == '__main__':
	if len(sys.argv) != 2:
		print >> sys.stderr, "Usage: python_abc_score.py <path to home directory>"
		exit(-1)
	main(sys.argv[1].rstrip("/"))
	print "Successfully completed!"

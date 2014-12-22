import os
import sys

def main(home_dir):
	r = set()
	files = os.listdir(home_dir+"/code")
	for i in range(0, 284):
		if str(i)+".py" in files:
			print "chaining: " + str(i)
			q = os.popen('python python_chain.py ' + home_dir +" "+ str(i) + " 1").readlines()
			# q = os.popen('python python_marker_rank.py ' + home_dir +" "+ str(i) + " 1").readlines()
			for m in q:
				r.add(m)
	f = open(home_dir + "/visualize_chain/concepts_chain_graph2.dot", "w")
	f.writelines(['digraph G{'])
	f.writelines(r)
	f.writelines(['}'])

if __name__ == '__main__': 
	if len(sys.argv) != 2:
		print "Usage python_chain_graph.py <path/to/home_dir>"
		exit(0)
	main(sys.argv[1].rstrip("/"))
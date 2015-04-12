#!/usr/bin/env python
import style_chain

def rnd(x, base=5):
    val = int(base * round(float(x)/base))
    if val > 30:
    	return 30
    return val

def main():
	colors = {0: '#323ea0', 5: '#3c4abe', 10:'#485565' , 15:'#757fd3', 20:'#929add', 25:'#bfc4eb', 30:'#dde0f4'}
	r = set()
	for i in xrange(200):
		node = style_chain.generate_chain(i, 1, 1, '../').head
		while (node):
			if node.next:
				print node.flog_score
				to_add = str(node.index) + " [style=filled, fillcolor = " +colors[rnd(node.flog_score)]+ "] -> " + str(node.next.index)+ " [style=filled, fillcolor = " +colors[rnd(node.next.flog_score)]+ "];\n"
				print to_add
				r.add(to_add)
			node = node.next
	f = open("chains_by_level.dot", "w")
	f.writelines(['digraph G{'])
	f.writelines(r)
	f.writelines(['}'])

if __name__ == '__main__':
    main()
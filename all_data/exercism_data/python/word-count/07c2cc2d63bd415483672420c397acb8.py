#
# Author: klemm89
#

def word_count(str):
	counts = dict()
	words = str.split(' ')
	for line in words:
	    words = line.split()
	    for word in words:
	        if word not in counts:
	            counts[word] = 1
	        else:
	            counts[word] += 1
	return counts

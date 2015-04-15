from collections import defaultdict


def word_count(words):

	d = defaultdict(int)

	for word in words.split():
		d[word] += 1

	#print d
	return d




#wordcount('one of each')

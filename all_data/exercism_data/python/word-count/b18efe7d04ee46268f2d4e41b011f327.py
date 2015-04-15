from collections import defaultdict


def word_count(str):

	words = str

	d = defaultdict(int)

	for word in words.split():
		d[word] += 1

	print d
	return d




#wordcount('one of each')

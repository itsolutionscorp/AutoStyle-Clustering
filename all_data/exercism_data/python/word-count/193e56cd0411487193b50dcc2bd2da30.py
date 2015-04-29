def word_count(phrase):
	import re
	count = {}
	for w in re.split("\s+", phrase.replace('\n',' ')):
		count[w] = count.get(w, 0) + 1
	return count

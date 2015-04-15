def word_count(phrase):
	import re
	punctuation = (',', '.', ';', ':', '?')
	count = {}
	for w in re.split("\s+", phrase.replace('\s+',' ')):
		if w.endswith(punctuation):
			w = w[:-1]
		count[w] = count.get(w, 0) + 1
	return count

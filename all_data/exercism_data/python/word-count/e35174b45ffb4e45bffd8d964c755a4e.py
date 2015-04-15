import re

def word_count(words):
	d = {}
	words = re.split('\s+', words)
	for w in words:
		d[w] = d.get(w, 0) + 1
	return d

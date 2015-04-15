// word_count.py
def word_count(s):
	d = {}
	words = s.split()
	for w in words:
		if w in d.keys():
			d[w] = 1
		else:
			d[w] += 1
	return d

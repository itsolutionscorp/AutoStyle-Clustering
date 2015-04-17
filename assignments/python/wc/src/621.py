import collections
def word_count(w):
	words = w.split()
	counter = collections.Counter(words)
	return dict(counter)

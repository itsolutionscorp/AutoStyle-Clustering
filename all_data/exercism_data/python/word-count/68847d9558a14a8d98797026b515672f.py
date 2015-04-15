import collections

def word_count(w):
	# Split with no separator argument will automatically deal with repeated whitespace
	words = w.split()
	
	# This does the counting for us. Make the returned list into a dict and return
	counter = collections.Counter(words)
	return dict(counter)

import re

def word_count(string):
	wc = {}
	# Remove special characters
	str = re.sub('\W+',' ', string)
	# Normalize string and split it into a list of words.
	words = str.lower().split()

	for word in words:
		if wc.has_key(word):
			wc[word] += 1
		else:
			wc[word] = 1
	return wc

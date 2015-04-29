import re
def word_count(string):
	wc = {}
	str = re.sub('\W+',' ', string)
	words = str.lower().split()
	for word in words:
		if wc.has_key(word):
			wc[word] += 1
		else:
			wc[word] = 1
	return wc

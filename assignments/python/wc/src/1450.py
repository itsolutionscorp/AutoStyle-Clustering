import re
def word_count(string):
	wc = {}
	words = re.sub('\W+', ' ', string).lower().split()
	for word in words:
		if word in wc:
			wc[word] += 1
		else:
			wc[word] = 1
	return wc

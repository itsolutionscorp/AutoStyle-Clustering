import re
def word_count(words):
	dict = {}
	words = re.split('\s+', words)
	for w in words:
		if w in dict:
			dict[w] += 1
		else:
			dict[w] = 1
	return dict

import re
from string import punctuation
def word_count(sentence):
	words = re.sub("[^\w\s" +punctuation+ "]", " ",  sentence).split()
	result = {}
	for word in words:
		if word in result:
			result[word] += 1
		else:
			result[word] = 1
	return result
import re
from collections import Counter
def word_count(sentence):
	sentence = sentence.lower()
	data = re.sub(u'\W', ' ', sentence, flags=re.UNICODE)
	data = data.split(' ')
	words = Counter()
	for x in data:
		if x != '':
			words[x] += 1
	return words

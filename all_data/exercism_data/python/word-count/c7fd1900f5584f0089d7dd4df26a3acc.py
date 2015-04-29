from collections import Counter
import re

def word_count(string):

	phrase = Counter()
	string = re.sub("[^\w]", " ", string).lower()

	for word in string.split():
		phrase[word] += 1

	return dict(phrase)

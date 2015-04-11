import re

def word_count(phrase):
	counter = {}

	words = re.sub('\s+',' ',phrase).split(' ')

	for word in words:
		counter[word] = counter.get(word, 0) + 1

	return counter

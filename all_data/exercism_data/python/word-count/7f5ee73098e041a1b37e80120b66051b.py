#wordcount.py
from string import translate, punctuation

def word_count(input):
	result = {}
	for word in input.split(' '):
		word = translate(word,None,punctuation).lower()
		if word == '':
			continue
		if word not in result:
			result[word] = 1
		else:
			result[word] += 1
	return result

'''
Word count.
Author: SprayIdle
This program counts the number of occurrences of each word in a given phrase.
TESTING:
	word_count.py (seperate file) will run numerous test cases over the 
	wordcount module.
REVISION HISTORY:
	24/09/14:	Initial implementation of word_count function.
'''
import string
def word_count(phrase):
	'''(string) -> dict
	Return a dict containing each unique word in string phrase as keys and 
	word counts as values.
	'''
	words = {}
	split_phrase = phrase.lower().split()
	for word in split_phrase:
		word = word.strip(string.punctuation)
		if word:
			if word not in words:
				words[word] = 1
			else:
				words[word] += 1
	return words

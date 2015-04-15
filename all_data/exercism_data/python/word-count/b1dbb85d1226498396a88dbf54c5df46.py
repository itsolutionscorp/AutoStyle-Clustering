import re
import string

def word_count(phrase):
	numwords = {}
	phrase = phrase.lower()
	transl_table = dict.fromkeys(map(ord,string.punctuation),None)
	words = phrase.translate(transl_table)
	words = words.split()
	for word in words:
		numwords[word] = words.count(word)
	return numwords

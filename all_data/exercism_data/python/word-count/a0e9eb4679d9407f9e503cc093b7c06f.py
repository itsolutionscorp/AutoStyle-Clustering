import re
import string
from collections import Counter

def word_count(phrase):
	words = phrase.split(' ')
	words = [remove_puntuation(word.lower()) for word in words]
	words = filter(lambda word: word != '', words)
		
	return Counter(words)

def remove_puntuation(word):
	return word.translate(None, string.punctuation)

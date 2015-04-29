from collections import Counter
import string

def word_count(phrase):
	phrase = phrase.translate(None, string.punctuation).lower()
	counter = Counter(phrase.split(' '))
	del counter['']
	return counter

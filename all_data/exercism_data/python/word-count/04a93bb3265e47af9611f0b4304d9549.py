"""
Simple word counter.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

def nopunc(word):
	return ''.join(char for char in word if char.isalpha() or char.isdigit())


def word_count(phrase):
	words = {}
	for _word in phrase.split():
		word = nopunc(_word).lower()
		if word:
			if word in words:
				words[word] += 1
			else:
				words[word] = 1
	return words

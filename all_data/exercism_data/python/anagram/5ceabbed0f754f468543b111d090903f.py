"""
Returns a correct list of anagrams given an overfull one.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

def detect_anagrams(word, possible_anagrams):
	"""Prunes out non-anagrams and returns the fixed list."""
	with_correct_count = []
	for anagram in possible_anagrams:
		if word.lower() != anagram.lower():
			for letter in set(list(word) + list(anagram)):
				if word.lower().count(letter) != anagram.lower().count(letter):
					break
			else:
				with_correct_count.append(anagram)
	return with_correct_count

def detect_anagrams(word, anagrams):
	"""For a given word and list of words, gives possible anagrams of word from list"""

	word_letter_counts = count_letters(word.lower())

	anagrams_letter_counts = {}
	
	for entry in anagrams:
		anagrams_letter_counts[entry] = count_letters(entry.lower())

	true_anagrams = []

	for entry in anagrams_letter_counts:
		if anagrams_letter_counts[entry] == word_letter_counts and entry.lower() != word.lower():
			true_anagrams.insert(0, entry)

	return true_anagrams

def count_letters(word):
	"""Counts occurrences of letters in words"""
	
	letter_counts = {}
	
	for letter in word:
		letter_counts[letter] = word.count(letter)

	return letter_counts

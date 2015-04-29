def detect_anagrams(word, potential_anagram_words):
	anagrams = []
	for potential_anagram_word in potential_anagram_words:
		if are_anagrams(word, potential_anagram_word):
			anagrams.append(potential_anagram_word)
	return anagrams


def are_anagrams(word1, word2):
	if (len(word1) == len(word2)):
		word1 = word1.lower()
		word2 = word2.lower()

	if len(word1) != len(word2):
		return False
	elif word1 == word2:
		return False
	else:
		sorted_lower_word1 = sorted(word1)
		sorted_lower_word2 = sorted(word2)
		return sorted_lower_word1 == sorted_lower_word2

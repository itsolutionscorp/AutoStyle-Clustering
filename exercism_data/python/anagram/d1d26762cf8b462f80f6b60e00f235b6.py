def detect_anagrams(word, possible_anagrams):

	for anagram in possible_anagrams:
		if not anagram == word:
			anagrams = [anagram for anagram in possible_anagrams if sorted(anagram.lower()) == sorted(word.lower())]
			return anagrams
		else:
			return []

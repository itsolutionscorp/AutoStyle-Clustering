def detect_anagrams(word, candidates):
	test_word = alphabetize(word)
	anagrams = []
	for candidate in candidates:
		if candidate == word: #word is not anagram of itself
			break
		temp = alphabetize(candidate)
		if temp == test_word:
			anagrams.append(candidate)
	return anagrams

def alphabetize(word):
	return "".join(sorted(word.lower()))

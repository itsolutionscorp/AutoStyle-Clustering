def detect_anagrams(matchWord, possibleWords):
	anagrams = []
	for word in possibleWords:
		if (sorted(word.lower()) == sorted(matchWord.lower())) and (word.lower() != matchWord.lower()):
			anagrams.append(word)
	return anagrams

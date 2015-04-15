def detect_anagrams(word, choices):
	anagrams = []
	lWord = list(word.lower())
	lWord.sort()

	for choice in choices:
		if choice.lower() == word.lower():
			continue

		lChoice = list(choice.lower())
		lChoice.sort()
		if lChoice == lWord:
			anagrams.append(choice)

	return anagrams

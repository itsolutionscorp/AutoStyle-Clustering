def detect_anagrams(word, candidates):
	# normalize the word due to case insensitivity
	word = word.lower()
	letters_in_word = list(word)
	letters_in_word.sort()
	matches = []

	for candidate in candidates:
		normalized = candidate.lower()
		if normalized == word:
			continue

		letters_in_candidate = list(normalized)
		letters_in_candidate.sort()
		
		if letters_in_candidate == letters_in_word:
			matches.append(candidate)

	return matches

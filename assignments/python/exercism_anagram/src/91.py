def detect_anagrams(word, candidates):
	# normalize the word due to case insensitivity
	letters_in_word = sorted(word.lower())
	matches = []
	for candidate in candidates:
		normalized = candidate.lower()
		
		if normalized == word:
			continue
		letters_in_candidate = sorted(normalized)
		
		if letters_in_candidate == letters_in_word:
			matches.append(candidate)
	return matches

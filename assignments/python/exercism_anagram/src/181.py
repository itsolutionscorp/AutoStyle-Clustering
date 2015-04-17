def detect_anagrams(word, candidates):
	newList = []
	sortedWord = ''.join(sorted(word.upper()))
	for c in candidates:
		candidate = ''.join(sorted(c.upper()))
		if candidate == sortedWord and word.upper() != c.upper():
			newList.append(c)
	return newList

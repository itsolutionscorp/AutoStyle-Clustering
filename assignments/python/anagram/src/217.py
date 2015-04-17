def detect_anagrams(orig, candidates):
	orig = orig.lower()
	solutions = []
	for word in candidates:
		if orig != word.lower() and sorted(orig) == sorted(word.lower()):
			solutions.append(word)
	return solutions

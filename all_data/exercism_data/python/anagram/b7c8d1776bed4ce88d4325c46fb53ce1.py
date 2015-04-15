def detect_anagrams(word, anagrams):
	matches = []
	for p in anagrams:
		lWord = word.lower()
		lAnagram = p.lower()
		
		if lWord != lAnagram:
			if sorted(lWord) == sorted(lAnagram):
				matches.append(p)
	return matches
	

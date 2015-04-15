def detect_anagrams(word, list_words):
	anagrams = []
	for candidate in list_words:
		if word.lower() != candidate.lower() and len(word) == len(candidate):
			remov_cand = candidate.lower()
			for c in word.lower():
				remov_cand = remov_cand.replace(c, '', 1)
			if remov_cand == '':
				anagrams.append(candidate)
	return anagrams
		
			

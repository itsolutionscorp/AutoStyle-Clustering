def detect_anagrams(word, thelist):
	candidates = []
	masterdict = create_dict(word.lower())

	for candidate in thelist:

# ensure the words are not the same word, and that they have matching lengths		
		if word.lower() != candidate.lower() and len(word) == len(candidate):
			cdict = create_dict(candidate.lower())
			if cdict == masterdict:
				candidates.append(candidate)
	return candidates
	
	

def create_dict(word):
	letterdict = {}
	for letter in range(len(word)):
		letterdict[word[letter]] = word.count(word[letter])
	return letterdict

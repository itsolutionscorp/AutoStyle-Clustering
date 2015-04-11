def detect_anagrams(word, thelist):
	masterlist = []
	candidatelist = []
	masterdict = create_dict(word.lower())
	for candidate in thelist:
		if word.lower() != candidate.lower():
			cdict = create_dict(candidate.lower())
			if cdict == masterdict:
				candidatelist.append(candidate)
	return candidatelist
	
	

def create_dict(word):
	letterdict = {}
	letterlist = []
	for letter in range(len(word)):
		letterlist.append(word[letter])
		letterdict[word[letter]] = letterlist.count(word[letter])
	return letterdict

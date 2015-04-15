def detect_anagrams(word,testlist):
	out = []
	worddict = {}
	for char in word.lower():
		try:
			worddict[char] += 1
		except KeyError:
			worddict[char] = 1
	for test in testlist:
		if test.lower() == word.lower():
			continue
		testdict = {}
		for char in test.lower():
			try:
				testdict[char] += 1
			except KeyError:
				testdict[char] = 1
		if worddict == testdict:
			out.append(test)
	return out

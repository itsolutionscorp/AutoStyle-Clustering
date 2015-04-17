def detect_anagrams(wordInput,listInput):
	wordList = [i.strip() for i in listInput]
	testString = ''.join(sorted(wordInput.lower()))
	testVal = wordInput.lower()
	output = []
	for i in wordList:
		testI = ''.join(sorted(i.lower()))
		testIlow = i.lower()
		if testI == testString:
			if testVal != testIlow:
				output.append(i)
	return output

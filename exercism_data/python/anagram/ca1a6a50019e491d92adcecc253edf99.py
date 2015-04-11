def detect_anagrams(word, list):
	outPut = []
	for x in list:
		if ''.join(sorted(x.upper())) == ''.join(sorted(word.upper())):
			if (x.upper() != word.upper()):
				outPut.append(x)
	return outPut

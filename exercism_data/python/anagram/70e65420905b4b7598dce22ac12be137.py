def detect_anagrams(word, testwords):
	return [testword for testword in testwords 
	if sorted(word.lower()) == sorted(testword.lower()) 
	and word.lower() != testword.lower()]

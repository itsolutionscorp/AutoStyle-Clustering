def detect_anagrams(word, words):
	test = [x for x in words if sorted(x.lower()) == sorted(word.lower()) and x.lower() != word.lower()]
	return test

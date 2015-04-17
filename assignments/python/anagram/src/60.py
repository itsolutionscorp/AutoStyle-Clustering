def detect_anagrams(word, candidates):
	_word = sorted(word.lower())
	return [x for x in candidates if sorted(x.lower()) == _word and x.lower() != word.lower()]

def detect_anagrams(word, candidates):
	word = word.lower()
	s_word = sorted_word(word)
	return [c for c in candidates if word != c.lower() and s_word == sorted_word(c)]

def sorted_word(word):
	return "".join(sorted(word.lower()))

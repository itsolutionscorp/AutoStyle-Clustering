def normalize(s):
	return str(sorted(s.lower()))

def detect_anagrams(word, candidates):
	target = normalize(word)
	word_lower = word.lower()
	return [c for c in candidates if normalize(c) == target and c.lower() != word_lower]

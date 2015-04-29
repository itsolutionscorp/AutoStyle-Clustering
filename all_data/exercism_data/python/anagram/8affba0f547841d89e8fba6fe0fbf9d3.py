def detect_anagrams(match, words):
	match = match.lower()
	words = filter(lambda x: x.lower() != match, words)
	return [word for word in words if ''.join(sorted(word.lower())) == ''.join(sorted(match))]

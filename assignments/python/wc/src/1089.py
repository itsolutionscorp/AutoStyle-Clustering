def word_count(sentence):
	sentence = sentence.strip()
	s = sentence.split()
	pairs = {}
	for i in range(0, len(s)):
		if s[i] in pairs:
			pairs[s[i]] = pairs[s[i]] + 1
		else:
			pairs[s[i]] = 1
	return pairs

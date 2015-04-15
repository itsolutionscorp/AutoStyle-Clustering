def word_count(sentence):
	
	s = sentence.split()
	
	pairs = {}

	for i in s:
		if i in pairs:
			pairs[i] = pairs[i] + 1
		else:
			pairs[i] = 1

	return pairs

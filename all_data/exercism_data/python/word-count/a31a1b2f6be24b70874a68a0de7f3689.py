def word_count(phrase):
	occurences = {}
	words = phrase.split()
	for word in words:
		if word in occurences:
			occurences[word] = occurences[word] + 1
		else:
			occurences[word] = 1
	return occurences

def word_count(input):
	words = {}
	for word in input.split():
		if words.has_key(word):
			words[word] += 1
		else:
			words[word] = 1
	return words
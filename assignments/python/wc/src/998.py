def word_count(str):
	words = {}
	for word in str.strip().split():
		if word in words:
			words[word] += 1
		else:
			words[word] = 1
	return words

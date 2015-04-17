def word_count(words):
	counter = {}
	for word in words.split():
		if word in counter:
			counter[word] += 1
		else:
			counter[word] = 1
	return counter

def word_count(text):
	words = text.split()
	counter = {}
	for word in words:
		if word in counter:
			counter[word] += 1
		else:
			counter[word] = 1
	return counter

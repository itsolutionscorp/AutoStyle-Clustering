def word_count(words):
	count = {}
	for word in words.split():
		if word not in count:
			count[word] = 1
		else:
			count[word] += 1
	return count

def word_count(phrase):
	count = {}
	for word in phrase.split():
		if word in count:
			count[word] += 1
		else:
			count[word] = 1
	return count

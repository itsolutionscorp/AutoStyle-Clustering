def word_count(phrase):
	phrase = phrase.split()
	count = {}
	for word in phrase:
		if word in count:
			count[word] += 1
		else:
			count[word] = 1
	return count

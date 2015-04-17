def word_count(phrase):
	map = {}
	words = phrase.split()
	for word in words:
		if word in map:
			map[word] = map[word] + 1
		else:
			map[word] = 1
	return map

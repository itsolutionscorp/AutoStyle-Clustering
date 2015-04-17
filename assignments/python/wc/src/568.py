def word_count(phrase):
	count = {}
	for item in phrase.split():
		if item in count:
			count[item] += 1
		else:
			count[item] = 1
	return count

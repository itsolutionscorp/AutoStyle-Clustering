def word_count(phrase):
	items = phrase.split()
	count = {}
	for item in items:
		if item in count:
			count[item] += 1
		else:
			count[item] = 1
	return count

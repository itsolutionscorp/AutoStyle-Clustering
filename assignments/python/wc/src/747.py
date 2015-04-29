def word_count(phrase):
	"""Count words in a phrase"""
	seen = []
	count = {}
	for x in phrase.split():
		if x not in seen:
			seen.append(x)
			count[x] = 1
		else:
			count[x] = count[x] + 1
	return count

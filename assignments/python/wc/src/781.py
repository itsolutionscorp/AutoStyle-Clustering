def word_count(phrase):
	"""Count words in a phrase"""
	count = {}
	for x in phrase.split():
		if x in count:
			count[x] += 1
		else:
			count[x] = 1	
	return count

def word_count(text=''):
	"""
	Counts numbers of occurrences of distinct words in a text.
	"""
	words = text.split()
	result = {}
	for word in words:
		if not word in result:
			result[word] = 1
		else:
			result[word] += 1
	return result

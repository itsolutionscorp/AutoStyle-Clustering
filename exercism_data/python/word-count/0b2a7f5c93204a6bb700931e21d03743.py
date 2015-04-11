def word_count(line):
	'''
	Return count of each word in input string

	>>> word_count('hello world world')
	{'world': 2, 'hello': 1}
	'''

	words = line.split()
	counts = {}
	# Iterate over copy of words because we do words.remove()
	for word in list(words):
		try:
			_ = counts[word]
		except KeyError:
			counts[word] = words.count(word)
		words.remove(word)
	return counts

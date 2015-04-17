def word_count(phrase):
	"""
	Input: String with words. Delimited by spaces, new lines, tabs.
	Output: Frequency of each word.
	"""
	words = phrase.split()
	dict = {}
	for word in words:
		if word not in dict:
			dict[word] = 1
		else:
			dict[word] += 1
	return dict

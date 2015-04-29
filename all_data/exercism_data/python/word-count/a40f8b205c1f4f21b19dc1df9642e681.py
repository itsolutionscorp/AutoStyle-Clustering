def word_count(text):
	result = {}
	words = text.split()
	for word in words:
		if word in result:
			result[word] = result[word] + 1
		else:
			result[word] = 1
	return result

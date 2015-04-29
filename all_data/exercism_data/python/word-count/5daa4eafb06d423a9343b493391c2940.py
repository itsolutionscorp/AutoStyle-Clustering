def word_count(text):
	text_split = text.split()
	result = {}
	for word in text_split:
		try:
			result[word] += 1
		except:
			result[word] = 1
	return result

def word_count(text):
	text_split = text.split()
	result = {}
	for word in text_split:
		result[word] = text_split.count(word)
	return result

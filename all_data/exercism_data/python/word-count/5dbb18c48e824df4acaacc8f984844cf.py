def word_count(phrase):
	words = phrase.split()
	result = {}

	for word in words:
		if not word in result:
			result.update({word:1})
		else:
			result[word] = result.get(word) + 1

	return result

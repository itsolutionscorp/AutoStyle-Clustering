def word_count(word):
	words = word.split()
	result = {}
	for w in words:
		if(result.get(w)):
			result[w] = result[w] + 1
		else:
			result[w] = 1
	return result

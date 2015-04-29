def word_count(sentence):
	word_list = sentence.split()
	result = dict()

	for w in word_list:
		if w not in result:
			result[w] = 1
		else:
			result[w] += 1
	return result	

# Easier to just use collections.Counter
#	return Counter(word_list)

def word_count(sentence):
	out = {}
	words = sentence.split(' ')
	while words: 
		count = 0
		word = words[0]
		for i, j in enumerate(words):
			if j == '':
				words.pop(i)
			elif j == word:
				words.pop(i)
				count += 1
		if count > 0:
			out[word] = count
	return out

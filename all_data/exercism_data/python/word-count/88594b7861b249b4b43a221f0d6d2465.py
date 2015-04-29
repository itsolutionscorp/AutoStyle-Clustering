def word_count(sentence):
	out = {}
	words = sentence.split(' ')
	while words: 
		count = 0
		word = words[0]
		for i, j in enumerate(words):
			# To get rid of empty spaces
			if j == '':
				words.pop(i)
			elif j == word:
				words.pop(i)
				count += 1
		# To prevent spaces to appear as {'': 0}
		if count > 0:
			out[word] = count
	return out
# How to get rid of the dreaded '\n'

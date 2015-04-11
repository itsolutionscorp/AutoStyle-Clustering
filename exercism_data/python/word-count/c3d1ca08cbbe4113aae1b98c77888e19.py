def word_count(sentence):
	words = dict('')
	for word in sentence.split(' '):
		word = word.lower()
		word = word.strip("""!@#$%^&*() -=,./?>:<'""")
		if word != '':
			if word in words:
				words[word] = words[word] + 1
			else:
				words[word] = 1
	return words

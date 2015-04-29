def word_count(sentence):
	sentence = ' '.join(sentence.split())
	wordcount = {}
	words = sentence.split(' ')
	for word in words:
		if word != '':
			if word not in wordcount:
				wordcount[word] = 1
			else:
				wordcount[word] += 1

	return wordcount

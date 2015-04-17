def word_count(sentence):
	wordcount = {}
	sentence = "".join(x for x in sentence if x not in '!?,.#:&@$%^')
	for word in sentence.lower().split():
		if wordcount.get(word, False):
			wordcount[word] += 1
		else:
			wordcount[word] = 1
	return wordcount

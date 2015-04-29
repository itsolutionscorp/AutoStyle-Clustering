def word_count(sentence):
	sentence_list = sentence.split()
	freq = {}
	for word in sentence_list:
		if not freq.has_key(word):
			freq[word] = sentence_list.count(word)
		else:
			pass
	return freq

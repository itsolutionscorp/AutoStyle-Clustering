def word_count(sentence):
	sentence_list = sentence.split()
	freq = {}
	for word in sentence_list:
		freq[word] = freq.get(word, 0) + 1
	return freq

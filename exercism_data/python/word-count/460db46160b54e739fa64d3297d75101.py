def word_count(sentence):
	words = sentence.split()
	count_dict = {}
	for word in words:
		#second parameter of get is default value if no value found for key
		count_dict[word] = count_dict.get(word, 0) + 1
	return count_dict

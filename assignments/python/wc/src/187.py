def word_count(sentence):
	count_dict = {}
	sentence = sentence.lower() 
	for char in sentence:
		if char.isalnum() == False and char.isspace() == False:
			sentence = sentence.replace(char,'')
	sentence = sentence.split()
	for word in set(sentence):
		count_dict[word] = sentence.count(word)
	return count_dict

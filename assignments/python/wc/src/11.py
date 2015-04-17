def word_count(test_string):
	word_list = {}
	word_array = test_string.split()
	for word in word_array:
		if word in word_list:
			word_list[word]+=1
		else:
			word_list[word]=1
	return word_list

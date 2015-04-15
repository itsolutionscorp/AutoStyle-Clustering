def word_count(test_string):
	word_list = {}
	word_array = test_string.split()
	
	for i in range(len(word_array)):
		if word_array[i] in word_list:
			word_list[word_array[i]]+=1
		else:
			word_list[word_array[i]]=1
	
	
	return word_list

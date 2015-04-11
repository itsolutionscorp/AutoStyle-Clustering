def word_count(sentence):
	word_list = sentence.split()
	word_dic = {}
	for word in word_list:
		if word in word_dic:
			word_dic[word] += 1
		else: word_dic[word] = 1
	return word_dic

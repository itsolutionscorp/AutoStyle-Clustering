def word_count(phrase):
	word_list = phrase.split()
	unique_list = list(set(word_list))
	word_dict = {}
	for i in range(len(unique_list)):
		word_dict[unique_list[i]] = word_list.count(unique_list[i])
	return word_dict

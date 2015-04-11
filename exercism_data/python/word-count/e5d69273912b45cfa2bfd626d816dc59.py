def word_count(string):
	word_list = string.split()
	number_list = []
	new_dict = {}
	for item in word_list:
		number_list.append(word_list.count(item))	
	for item in range(len(word_list)):
		new_dict.update({word_list[item]:number_list[item]})
	return new_dict

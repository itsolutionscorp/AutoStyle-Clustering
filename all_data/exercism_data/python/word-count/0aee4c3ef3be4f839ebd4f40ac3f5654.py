def word_count(string):
	wc_dict = {}
	word = ""
	index = 0
	while index < len(string):

		if string[index].isalnum():
			word = word + string[index]
			index += 1	
		else:
			if word:
				if word.lower() in wc_dict:
					wc_dict[word.lower()] += 1
				else:
					wc_dict[word.lower()] = 1
			word = ""
			index += 1

	if word:
		if word.lower() in wc_dict:
			wc_dict[word.lower()] += 1
		else:
			wc_dict[word.lower()] = 1

	return wc_dict

def word_count(phrase):
	split_string = phrase.split()
	string_list = {}
	for word in split_string:
		if string_list.has_key(word):
			string_list[word] = string_list[word] + 1
		else:
			string_list[word] = 1
	return string_list

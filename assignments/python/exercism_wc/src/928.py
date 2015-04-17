def word_count(str):
	dict = {}
	for word in str.split():
		if not word in dict:
			dict[word] = 1
		else:
			dict[word] += 1
	return dict

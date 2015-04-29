def word_count(str):
	dict = {}
	split = str.split()
	for word in split:
		if not word in dict:
			dict[word] = 1
		else:
			dict[word] += 1
	for word in dict:
		print('{}: {}'.format(word, dict[word]))

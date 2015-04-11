def word_count(words):
	all = {}
	for word in words.split():
		if word in all.keys():
			all[word] += 1
		else:
			all[word] = 1
	return all

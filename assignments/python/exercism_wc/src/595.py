def word_count(words):
	lis = words.split()
	dict = {}
	for i in lis:
		dict[i] = 0
		for j in lis:
			if j == i:
				dict[i] += 1
	return dict

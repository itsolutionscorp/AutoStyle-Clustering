def word_count(phrase):
	counts = {}
	p_list = phrase.replace('\n',' ').split(' ')
	for word in p_list:
		if word != '':
			if not word in counts:
				counts[word] = 1
			else:
				counts[word] += 1
	return counts

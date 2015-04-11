def word_count(sentance):
	words = sentance.split()
	d = {}
	for i in words:
		if i not in d:
			d[i] = 1
		else:
			d[i] += 1
	return d
	
	

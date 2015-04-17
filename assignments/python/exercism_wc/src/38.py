def word_count(sentence):
	times = []
	words = sentence.split()
	worten = words
	newlist = []
	zuruck = {}
	for i in words:
		if i not in newlist:
			z = 0 
			for k in range(0,len(worten)):
				if i == worten[k]:
					z = z + 1 
			times.append(z)
			newlist.append(i)
			zuruck[i] = z
	return zuruck

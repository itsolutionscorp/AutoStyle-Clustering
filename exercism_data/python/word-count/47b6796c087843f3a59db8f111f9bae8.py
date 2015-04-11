def word_count(prompt):
	words = prompt.split()
	l = list()
	for i in words:
		k = list()
		c = 0
		for x in words:
			if i == x:
					c = c + 1		
		k.append(i)
		k.append(c)
		if k not in l:
			l.append(k)

	res = dict(l)
	return res
	

def distance(s1, s2):
	hd = 0
	for i in xrange(len(s1)):
		if s1[i] != s2[i]:
			hd += 1
	return hd

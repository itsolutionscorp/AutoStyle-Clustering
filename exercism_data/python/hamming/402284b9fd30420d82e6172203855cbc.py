def distance(one,second):
	z = 0 
	for i in range(0,len(one)):
		if one[i] != second[i]:
			z = z + 1
	return z

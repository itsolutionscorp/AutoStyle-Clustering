def distance(first, second):
	dis = 0
	
	for i, j in enumerate(first):
		if first[i] != second[i]:
			dis += 1

	return dis 

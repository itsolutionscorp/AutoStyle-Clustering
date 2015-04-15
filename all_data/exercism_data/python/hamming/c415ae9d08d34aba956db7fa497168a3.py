def distance(first, second):
	c = 0
	for x in range(len(first)):
		if first[x] != second[x]:
			c += 1
	return c

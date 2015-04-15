def distance(a, b):
	count = 0
	for x in range(len(a)):
		if (a[x] != b[x]):
			count = count + 1
	return count

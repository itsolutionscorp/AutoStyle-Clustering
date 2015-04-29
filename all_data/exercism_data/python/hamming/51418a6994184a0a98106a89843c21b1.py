def distance(a, b):
	count = 0
	for i in range(0, len(a)):
		if a[i] != b[i]:
			count += 1

	return count

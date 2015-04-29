def distance(a, b):

	count = 0
	i = 0
	while i <= len(a)-1:
		if a[i] != b[i]:
			count += 1
		i += 1

	return count

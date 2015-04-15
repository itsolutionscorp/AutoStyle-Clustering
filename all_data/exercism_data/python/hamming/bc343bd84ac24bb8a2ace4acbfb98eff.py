def distance(a, b):
	h = 0		# hamming distance
	
	a,b = list(a), list(b)

	distance = 0
	i = 0
	for j in a:
		if a[i] != b[i] : distance = distance + 1
		i = i+1

	return distance

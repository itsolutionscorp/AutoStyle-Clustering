def distance(a,b):
	x = 0
	distance = 0
	while x < len(a):
		if a[x] != b[x]:
			distance += 1
		x += 1
	return distance

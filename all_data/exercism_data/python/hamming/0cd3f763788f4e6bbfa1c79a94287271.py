def distance(s1, s2):
	distance = 0
	for i in range(0, len(s1)):
		if s1[i] != s2[i]:
			distance += 1
	return distance

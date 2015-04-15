def distance(strand1, strand2):
	i = 0
	distance = 0
	while i < len(strand1):
		if strand1[i] != strand2[i]:
			distance += 1
		i += 1
	return distance
		

def distance(strand1, strand2):
	distanceCount = 0
	for n in range(0, len(strand1)):
		if strand1[n] != strand2[n]:
			distanceCount += 1
	return distanceCount

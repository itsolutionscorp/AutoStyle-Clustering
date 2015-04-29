def distance(dnaStr1,dnaStr2):
	dist = 0
	for dnaC1,dnaC2 in zip(dnaStr1,dnaStr2):
		if not dnaC1 == dnaC2:
			dist += 1
	return dist

def distance(genomA, genomB):
	numberOfErrors = 0 

	lA = list(genomA)
	lB = list(genomB)

	for i in range(0, len(genomA)):
		if not lA[i] == lB[i]:
			numberOfErrors = numberOfErrors +1

	return numberOfErrors

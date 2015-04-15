def hamming(first, second):
	firstList = list(first)
	secondList = list(second)

	ham_distance = abs(len(firstList) - len(secondList))
	for i, val in enumerate(firstList):
		if  i > len(secondList) - 1:
			pass
		elif val != secondList[i]:
			ham_distance += 1

	return ham_distance

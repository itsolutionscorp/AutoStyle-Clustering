def distance(firstDna, secondDna):
	count = 0
	if len(firstDna) == len(secondDna):
		for i,v in enumerate(firstDna):
			if firstDna[i] != secondDna[i]:
				count += 1
	return count

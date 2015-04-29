def distance (first, second):
	count = 0
	for i,x in enumerate(first):
		if first[i] != second[i]:
			count += 1

	return count

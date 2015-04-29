def distance(x,y):
	if (len(x) != len(y)):
		return

	distance = 0
	for i, j in zip(x,y):
		if i != j:
			distance += 1

	return distance

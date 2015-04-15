def distance(phrase1, phrase2):
	count = 0
	for x,y in zip(phrase1,phrase2):
		if x == y:
			count += 0
		else:
			count += 1
	return count

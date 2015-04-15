def distance(str1, str2):
	dist = 0

	for let1, let2 in zip(str1, str2):
		if let1 != let2:
			dist += 1

	return dist

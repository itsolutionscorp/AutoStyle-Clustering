def distance(s1, s2):
	min_length = min(len(s1), len(s2))
	difference = 0

	index = 0
	while index < min_length:
		if s1[index] != s2[index]:
			difference += 1
		index += 1

	return difference

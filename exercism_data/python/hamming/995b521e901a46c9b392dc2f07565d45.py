def distance(DNA1, DNA2):
	distance = 0

	if len(DNA1) != len(DNA2):
		return -1  # Returns -1 if the two DNA strands are not of equal length (based on definition in README.md)

	for i in range(len(DNA1)):
		if DNA1[i] != DNA2[i]:
			distance += 1

	return distance

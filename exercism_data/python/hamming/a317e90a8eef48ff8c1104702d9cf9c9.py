def distance(a, b):
	if a == b:
		return 0
	
	differences = 0
	for counter in range(len(a)):
		if a[counter] != b[counter]:
			differences += 1
	
	return differences

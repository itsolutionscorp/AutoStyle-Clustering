def distance(strand1, strand2):
	if len(strand1) != len(strand2): 
		return -1 
	elif strand1 == strand2:
		return 0 
	else: 
		sum = 0 
		for i in range(0, len(strand1)):
			if strand1[i] != strand2[i]:
				sum += 1
		return sum

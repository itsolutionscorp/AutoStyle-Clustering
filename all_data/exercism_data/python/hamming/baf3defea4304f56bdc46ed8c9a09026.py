def distance(seq_1, seq_2):
	if len(seq_1) != len(seq_2):
		return "Input Error!"
	
	distance = 0
	
	for i in range(len(seq_1)):
		if seq_1[i] != seq_2[i]:
			distance += 1
	
	return distance

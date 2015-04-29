def hamming(s1,s2):
	count = abs(len(s1) - len(s2))
	for a,b in zip(s1,s2):
		if a != b:
			count += 1
	return count

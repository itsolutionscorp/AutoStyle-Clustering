def hamming(dna1, dna2):
	result = 0
	len1 = len(dna1)
	len2 = len(dna2)
	if(len1 > len2):
		for x in range(0, len(dna2)):
			if dna1[x] != dna2[x]:
				result += 1
		result += (len1 - len2) #account for difference in sequence length
	else:
		for x in range(0, len(dna1)):
			if dna1[x] != dna2[x]:
				result += 1
		result += (len2 - len1) #account for different in sequence length
	return result

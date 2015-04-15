def hamming(strand1,strand2):
	#maximum length of a strand
	maxLen = max(len(strand1), len(strand2))
	#minimum length of a strand
	minLen = min(len(strand1), len(strand2))
	#distance is AT LEAST the difference between the two lengths
	distance = maxLen-minLen
	#iterate over all possible characters
	for i in range(0,minLen):
		#increment count when there is a difference
		if strand1[i] != strand2[i]:
			distance += 1
	#return final count when iteration is complete
	return distance

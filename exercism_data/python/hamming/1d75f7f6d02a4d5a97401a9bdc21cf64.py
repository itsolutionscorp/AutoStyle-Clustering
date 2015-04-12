def distance(strand1,strand2):
	strand1 = strand1[:len(strand2)]
	strand2 = strand2[:len(strand1)]
	
	hamming_distance = 0
	
	for i in range(len(strand1)):
		if strand1[i]!=strand2[i]:
			hamming_distance+=1
			
	return hamming_distance
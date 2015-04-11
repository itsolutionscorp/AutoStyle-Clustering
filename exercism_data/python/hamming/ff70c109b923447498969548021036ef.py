def distance(strand_1, strand_2):
	
	strand_1_list = list(strand_1)
	strand_2_list = list(strand_2)
	hamming_distance = 0

	i = 0
	for nucleotide in strand_1_list:
		if nucleotide != strand_2_list[i]:
			hamming_distance+=1
		i+=1

	return hamming_distance

def distance(dna_strand1,dna_strand2):
	temp_list= list(dna_strand1)
	temp_list2= list(dna_strand2)
	hamming_distance=0

	for i in range(len(temp_list)):
		if temp_list[i] != temp_list2[i]:
			hamming_distance += 1

	return hamming_distance



	

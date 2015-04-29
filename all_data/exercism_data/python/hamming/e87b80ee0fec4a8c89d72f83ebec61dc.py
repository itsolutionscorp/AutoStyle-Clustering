def hamming(dna1, dna2):
    ham_distance = abs(len(dna1) - len(dna2))
    zipped_dna = zip(dna1, dna2)
    for i, j in zipped_dna:
    	if i != j:
    		ham_distance += 1
    return ham_distance

def distance(dna_seq1,dna_seq2):
    hamming_distance = 0
    if len(dna_seq1) != len(dna_seq2) or not (len(dna_seq1) and len(dna_seq2)):
        raise Exception('Length of input parameters does not match or none given')
    else:    
        for n1,n2 in zip(dna_seq1,dna_seq2):
            if n1 != n2:
				hamming_distance += 1
            
    return hamming_distance

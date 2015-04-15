def distance(dna_seq1,dna_seq2):
    hamming_distance = 0
    if len(dna_seq1) != len(dna_seq2) or len(dna_seq1) == len(dna_seq2) == 0:
        return 'Nope.'
    else:    
        for n1,n2 in zip(dna_seq1,dna_seq2):
            if n1 != n2:
				hamming_distance += 1
            
    return hamming_distance

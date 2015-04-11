def distance(dna_strand_1,dna_strand_2):
    hamming_difference = 0
    for dna_count in range(len(dna_strand_1)):
        if dna_strand_1[dna_count] != dna_strand_2[dna_count]:
            hamming_difference+=1
    return hamming_difference
                           

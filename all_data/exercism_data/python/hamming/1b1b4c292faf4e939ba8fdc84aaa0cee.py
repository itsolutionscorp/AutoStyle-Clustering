def distance(dna_1, dna_2):
    num_errors = sum(i != j for i, j in zip(dna_1, dna_2))
    return num_errors

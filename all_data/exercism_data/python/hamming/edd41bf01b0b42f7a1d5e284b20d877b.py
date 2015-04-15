def distance(dna_1, dna_2):

    num_of_mutations = 0

    for i, j in zip(dna_1, dna_2):
        if i != j:
            num_of_mutations += 1

    return num_of_mutations

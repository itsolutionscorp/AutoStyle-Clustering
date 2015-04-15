def distance(dna, rna):
    hamming = 0
    for dna_character, rna_character in zip(dna, rna):
        if dna_character != rna_character:
            hamming += 1
    return hamming

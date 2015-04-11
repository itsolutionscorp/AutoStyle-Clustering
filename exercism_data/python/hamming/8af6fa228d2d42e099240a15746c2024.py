def distance(dna_1, dna_2):
    hamming_difference = 0
    dna_letter_pairs = zip(dna_1, dna_2)
    for dna_1_letter, dna_2_letter in dna_letter_pairs:
        if dna_1_letter != dna_2_letter:
            hamming_difference += 1
    return hamming_difference

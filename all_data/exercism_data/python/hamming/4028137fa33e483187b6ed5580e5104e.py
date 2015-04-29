#!/usr/bin/python
def hamming(dna_string_1, dna_string_2):
    """ Returns the 'hamming_distance' between two strings of dna. The hamming
    distance is the number of elements that are different between the two."""
    hamming_distance = abs(len(dna_string_1) - len(dna_string_2))
    max_index = min(len(dna_string_1), len(dna_string_2))
    for i in range(max_index):
        if dna_string_1[i] != dna_string_2[i]:
            hamming_distance += 1
    return hamming_distance

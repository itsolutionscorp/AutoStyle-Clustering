def distance(dna1, dna2):
    letter_index = 0
    hamming_distance = 0
    for letter in dna1:
        if letter != dna2[letter_index]:
            hamming_distance += 1
        letter_index += 1
    return hamming_distance

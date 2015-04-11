def distance(dna1, dna2):

    if len(dna1) != len(dna2):
        raise ValueError("The two DNA sequences must have an equal length.")

    hamming_distance = 0
    for acid1, acid2 in zip(dna1, dna2):
        if acid1 != acid2:
            hamming_distance += 1

    return hamming_distance

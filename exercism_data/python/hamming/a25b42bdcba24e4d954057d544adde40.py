def distance(dna1, dna2):
    distance = 0
    if len(dna1) != len(dna2):
        raise ValueError(
                "Hamming distance undefined for Sequences of different length")
    else:
        for index, nucleotide in enumerate(dna1):
            if dna2[index] != nucleotide:
                distance += 1
    return distance

def hamming(dna1, dna2):
    counter = 0
    length = min(len(dna1), len(dna2))
    for position in range(0, length):
        if dna1[position] != dna2[position]:
            counter += 1
    return counter + max(len(dna1), len(dna2))-length

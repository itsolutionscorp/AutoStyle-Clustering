def hamming(strand1, strand2):
    length = max(len(strand1), len(strand2))
    strand1 = strand1.ljust(length, ' ')
    strand2 = strand2.ljust(length, ' ')

    distance = 0
    for i, nucleotide in enumerate(strand1):
        if nucleotide != strand2[i]:
            distance += 1
    return distance

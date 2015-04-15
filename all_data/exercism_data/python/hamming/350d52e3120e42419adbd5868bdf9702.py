def hamming(strand1, strand2):
    if len(strand1) > len(strand2):
        shorter_strand = strand2
        longer_strand = strand1
    else:
        shorter_strand = strand1
        longer_strand = strand2

    distance = 0
    for i, n in enumerate(longer_strand):
        if i >= len(shorter_strand) or n != shorter_strand[i]:
            distance += 1
    return distance

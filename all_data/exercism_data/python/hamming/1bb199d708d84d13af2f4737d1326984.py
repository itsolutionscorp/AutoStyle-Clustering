def distance(strand1, strand2):
    if len(strand1) != len(strand2):
        raise ValueError('Strand lengths are not equal.')
    distance = 0
    for idx, nucleotide in enumerate(strand1):
        if strand1[idx] != strand2[idx]:
            distance += 1
    return distance

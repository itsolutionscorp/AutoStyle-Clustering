def distance(strand1, strand2):
    hamming = 0
    return [(hamming + 1) for base1, base2 in zip(strand1, strand2) if not base1 == base2]

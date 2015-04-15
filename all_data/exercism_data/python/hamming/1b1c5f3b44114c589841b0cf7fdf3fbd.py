def distance(strand1, strand2):
    hamming = 0
    for n1, n2 in zip(strand1, strand2):
        if n1 != n2:
            hamming += 1
    return hamming

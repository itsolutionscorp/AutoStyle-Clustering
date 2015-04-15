def hamming(strand1, strand2):
    strands = zip(strand1, strand2)
    return (len([x for x, y in strands if x != y]) +
            abs(len(strand1) - len(strand2)))

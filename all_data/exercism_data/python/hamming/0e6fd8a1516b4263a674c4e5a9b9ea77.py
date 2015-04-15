def hamming(strand1, strand2):
    distance = abs(len(strand1) - len(strand2))
    distance += sum([1 for x, y in zip(strand1, strand2) if x != y])
    return distance

def distance(strand1, strand2):
    return sum(a != b for a, b in zip(strand1, strand2))

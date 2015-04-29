def distance(strand1, strand2):
    return sum([1 for (n1, n2) in zip(strand1, strand2) if n1 != n2])

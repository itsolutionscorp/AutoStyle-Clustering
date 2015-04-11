def distance(strand1, strand2):
    return sum([1 for x, y in zip(strand1, strand2) if x != y])

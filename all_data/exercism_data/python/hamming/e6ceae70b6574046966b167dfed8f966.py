def distance(strand1, strand2):
    return sum(map(lambda x, y: x != y, strand1, strand2))

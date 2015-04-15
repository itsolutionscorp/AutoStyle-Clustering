def distance(strand1, strand2):
    assert len(strand1) == len(strand2)
    return sum(1 for a, b in zip(strand1, strand2) if a != b)

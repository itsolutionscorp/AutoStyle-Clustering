def distance(strand_a, strand_b):
    return sum(a != b for a, b in zip(strand_a, strand_b))

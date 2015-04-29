def distance(strand_1, strand_2):
    return sum(base_1 != base_2 for base_1, base_2 in zip(strand_1, strand_2))

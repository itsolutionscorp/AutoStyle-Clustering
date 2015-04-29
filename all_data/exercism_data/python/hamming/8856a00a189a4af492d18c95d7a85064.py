def distance(strand_a, strand_b):
    return reduce(lambda acc, i: acc + (1 if strand_a[i] != strand_b[i] else 0), range(len(strand_a)), 0)

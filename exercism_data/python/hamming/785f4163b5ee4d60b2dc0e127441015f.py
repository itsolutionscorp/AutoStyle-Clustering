def hamming(strand_a, strand_b):

    return sum(map(lambda x, y : abs(cmp(x, y)), strand_a, strand_b))

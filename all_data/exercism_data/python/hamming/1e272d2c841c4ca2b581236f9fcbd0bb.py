def distance(strand_one, strand_two):
    hamming = 0
    for nuc_one, nuc_two in zip(strand_one, strand_two):
        if nuc_one != nuc_two:
            hamming += 1
    return hamming

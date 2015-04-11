def distance(dna_1, dna_2):
    '''Compute the Hamming distance between two DNA strands.

    If the two DNA strands are of different length,
    compute the Hamming distance for the minimum length.'''

    return sum(1 for (n_1, n_2) in zip(dna_1, dna_2) if n_1 != n_2)

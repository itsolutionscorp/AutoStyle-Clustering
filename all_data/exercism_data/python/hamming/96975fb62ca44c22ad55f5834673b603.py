def distance(dna1, dna2):
    """
    Returns the Hamming distance between two DNA strands.
    """
    return sum(map(lambda x: x[0] != x[1], zip(dna1, dna2)))

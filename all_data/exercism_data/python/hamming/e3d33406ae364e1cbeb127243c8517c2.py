def distance(dna1, dna2):
    """
    Returns the Hamming distance between two DNA strands.
    """
    return sum(x != y for x, y in zip(dna1, dna2))

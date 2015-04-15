def distance(dna1, dna2):
    """Determines Hamming distance of two DNA sequences.

    Raises a ValueError if sequences are of differing length."""
    if len(dna1) != len(dna2):
        raise ValueError("Hamming distance undefined for differing lengths")

    hamming = 0
    for base1, base2 in zip(dna1, dna2):
        if base1 != base2:
            hamming += 1
    return hamming

def distance(strand1, strand2):
    """Return the Hamming distance between two equal-length DNA strands.
    
    Note: The Hamming distance is the number of nucleotides by which the
    two strands differ. It is undefined for strands of differing lengths
    
    Parameters:
        strand1, strand2 -- the DNA strands to compare
    """
    count = 0
    for a, b in zip(strand1, strand2):
        if a != b:
            count += 1
    return count

def distance(first, second):
    """Calculate the hamming distance between 2 dna strands."""
    pairs = zip(first, second)
    dist = (1 for f, s in pairs if f != s)
    return sum(dist)

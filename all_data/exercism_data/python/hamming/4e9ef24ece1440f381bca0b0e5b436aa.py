def distance(strand1, strand2):
    if len(strand1) != len(strand2):
        raise ValueError("Strands must be of equal length")

    return sum([1 for n1, n2 in zip(strand1, strand2) if n1 != n2])

def distance(s1, s2):
    if len(s1) != len(s2):
        raise ValueError("Sequences must be equal length")
    dist = sum(1 for n1, n2 in zip(s1, s2) if n1 != n2)
    return dist

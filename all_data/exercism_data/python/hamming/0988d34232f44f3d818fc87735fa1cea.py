def distance(sequence1, sequence2):
    """
    :type sequence1: str
    :type sequence2: str
    :rtype : int
    """
    return sum([int(n1 == n2) for n1, n2 in zip(sequence1, sequence2)])

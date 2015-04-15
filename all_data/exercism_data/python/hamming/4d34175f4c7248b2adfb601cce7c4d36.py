def distance(original, mutated):
    """
    Compares each base in the original and mutated string and returns the
    Hamming distance.
    """
    hDistance = 0
    for i, base in enumerate(original):
        if base != mutated[i]:
            hDistance += 1
    return hDistance

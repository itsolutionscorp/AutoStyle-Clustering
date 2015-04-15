def distance(original, mutated):
    """
    Compares each base in the original and mutated string and returns the
    Hamming distance.
    """
    return sum(oBase != mBase for oBase, mBase in zip(original, mutated))

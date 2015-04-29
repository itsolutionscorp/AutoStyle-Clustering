# hamming exercise from exercism.io

def distance(*strands):
    """calculates the hamming distance of two strands of DNA"""

    # confirm two arguments are given.
    assert len(strands) == 2
    # confirm comparison is between strands of same length
    if len(strands[0]) != len(strands[1]):
        raise ValueError("Hamming distance of strands of differents length")

    # make a list of True/False representing wether the items differ,
    # then counts the number of "True" (pairs that differ)
    return sum([1 if a != b else 0
            for (a,b) in zip(*strands)])

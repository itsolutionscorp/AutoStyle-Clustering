def distance(dnaString1, dnaString2):
    """
    Calculate the Hamming distance between two DNA strands.
    returns False if strings are not equal length.
    """
    if len(dnaString1) != len(dnaString2):
        return False

    h_distance = 0
    for base1, base2 in zip(dnaString1, dnaString2):
        if base1 != base2:
            h_distance += 1

    return h_distance

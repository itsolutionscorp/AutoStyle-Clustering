def distance(strand1, strand2):
    """Returns the Hamming distance between two DNA strands

    Args:
        strand1: A string representation of a DNA strand.
        strand2: A string representation of a DNA strand.

    Returns:
        The Hamming distance between the two DNA strands entered
        as arguments to the function.
    """
    count = 0

    if len(strand1) == len(strand2):
        for i in range(len(strand1)):
            if strand1[i] != strand2[i]:
                count += 1

    return count

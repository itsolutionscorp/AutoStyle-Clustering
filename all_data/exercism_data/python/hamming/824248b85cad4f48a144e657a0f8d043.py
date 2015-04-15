__author__ = 'jetties'

def distance(a, b):
    """
    Computes the Hamming distance between two DNA strands.
    :param a: String representation of a DNA strand.
    :param b: String representation of a DNA strand; must be the same length as a.
    :return: The numerical hamming distance.
    """
    return sum([1 if a[i] != b[i] else 0 for i in range(0, len(a))])

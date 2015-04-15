"""
series - a module for finding subsequences in numeric strings.
"""

def slices(string, num):
    """
    Return a list of subsequences of length num chosen from string.
    """

    # Calculate length and check usage; error checking requirements for this
    # problem were nominally different from those of the original.
    length = len(string)
    if num > length:
        raise ValueError("Requested subsequence length too long.")

    # Return the requested subsequences.
    subsequences = []
    for i in xrange(0, length - num + 1):
        subsequences.append([int(c) for c in string[i:i + num]])
    return subsequences


def largest_product(string, num):
    """
    Find the largest product of num consective numbers in a numeric string.
    """

    # Default to a largest known product of zero and find all products using
    # the slices function. Empty lists have product one (for some reason).
    largest_known_product = 0
    for subsequence in slices(string, num):
        if subsequence:
            product = reduce(lambda x, y: x * y, subsequence)
        else:
            product = 1
        if product > largest_known_product:
            largest_known_product = product
    return largest_known_product

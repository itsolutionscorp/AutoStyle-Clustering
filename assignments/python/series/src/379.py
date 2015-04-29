"""
series - a module for finding subsequences in numeric strings.
"""
def slices(string, num):
    """
    Return a list of subsequences of length num chosen from string.
    """
    length = len(string)
    if num > length:
        raise ValueError("Requested subsequence length too long.")
    elif num == 0:
        raise ValueError("Subsequence length must be non-zero.")
    subsequences = []
    for i in xrange(0, length - num + 1):
        subsequences.append([int(c) for c in string[i:i + num]])
    return subsequences

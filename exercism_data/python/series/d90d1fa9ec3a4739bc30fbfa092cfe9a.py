"""
series - a module for finding subsequences in numeric strings.
"""

def slices(string, num):
    """
    Return a list of subsequences of length num chosen from string.
    """

    # Calculate length and check usage.
    length = len(string)
    if num > length:
        raise ValueError("Requested subsequence length too long.")
    elif num == 0:
        raise ValueError("Subsequence length must be non-zero.")
    
    # Return the requested subsequences.
    subsequences = []
    for i in xrange(0, length - num + 1):
        subsequences.append([int(c) for c in string[i:i + num]])
    return subsequences

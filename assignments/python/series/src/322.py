def slices(string, n):
    """Returns all possible consecutive number sequences of length 'n' in a string"""
    if n < 1:
        raise ValueError('Slice length must be greater than 0')
    elif n > len(string):
        raise ValueError('Slice length must be less than or equal to string length')
    else:
        return [list(map(int, list(string[i:i+n]))) for i in range(len(string) - n + 1)]

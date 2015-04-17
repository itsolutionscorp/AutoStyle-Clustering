def slices(in_string, n):
    """returns all possible slices converted to lists of ints."""
    if not 0 < n <= len(in_string):
        raise ValueError('Length of slice is wrong')
    if not all( c in "0123456789" for c in in_string):
        raise ValueError('Can slice only strings representing numbers')
    return [list(map(int,in_string[i:i+n])) for i in range(len(in_string)-n+1)]

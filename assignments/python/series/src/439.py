def slices(given, length):
    """
    Returns all possible consecutive number series of length `n`
    for the digits in a given string.
    """
    if length <= 0:
        raise ValueError("requested slice of invalid length")
    if length > len(given):
        raise ValueError("input too short for slices of "+str(length))
    digits = [int(c) for c in given]
    return [digits[n:n+length] for n in xrange(0, len(given)-length+1)]

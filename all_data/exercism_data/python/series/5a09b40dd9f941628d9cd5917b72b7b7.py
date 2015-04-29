def slices(digits, n):
    """Get all possible consecutive number series of length n
    in a string of digits"""

    if n > len(digits) or n == 0:
        raise ValueError

    digits = map(int, digits)
    return [digits[i:i+n]
            for i in range(0, len(digits)-n+1)]

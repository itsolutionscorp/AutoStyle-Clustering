def slices(digits, n):
    if (n is 0) or len(digits) < n:
        raise ValueError

    result = []
    digits = map(int, digits)
    range = xrange(0, len(digits) - n + 1)
    for i in range:
        result.append(digits[i:i+n])
    return result

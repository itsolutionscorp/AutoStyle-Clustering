def slices(digits, count):
    """
    Return a list of ``count``-length slices from an integer iterable.
    """
    last = len(digits)-count+1
    if count == 0:
        raise ValueError('Zero-length slices are not allowed.')
    elif last <= 0:
        raise ValueError('Slice is longer than the string itself.')
    else:
        series = []
        for start in range(last):
            substring = digits[start:start+count]
            series.append([int(x) for x in substring])
        return series

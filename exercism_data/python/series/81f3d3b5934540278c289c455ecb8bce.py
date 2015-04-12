def slices(series, n):
    results = []
    series_digits = [int(c) for c in series]
    series_len = len(series)
    if not (0 < n < series_len + 1):
        raise ValueError("slice {0:d} too big for series of size {1:d}".format(n, len(series)))
    for i in xrange(0, series_len - n + 1):
        results.append(series_digits[i:i+n])
    return results
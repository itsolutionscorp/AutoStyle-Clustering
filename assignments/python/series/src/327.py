def slices(series, n):
    results = []
    series_len = len(series)
    if n < 1 or n > series_len:
        raise ValueError("slice {0:d} too big for series of size {1:d}".format(n, len(series)))
    for i in xrange(0, series_len - n + 1):
        results.append([int(c) for c in series[i:i+n]])
    return results

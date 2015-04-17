def slices(series, n):
    length = len(series)
    if n > length or n <= 0:
        raise ValueError('slice size outside of specification')
    sequences = []
    for start in xrange(length - n + 1):
        values = [int(x) for x in series[start:start+n]]
        sequences.append(values)
    return sequences

def slices(series, n):
    length = len(series)
    sequences = []
    if n > length or n == 0:
        raise ValueError('slice size outside of specification')
    for start in xrange(length - n + 1):
        values = [int(x) for x in series[start:start+n]]
        sequences.append(values)
    return sequences

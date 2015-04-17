def slices(series, n):
    length = len(series)
    start = 0
    sequences = []
    if n > length or n == 0:
        raise ValueError('slice size outside of specification')
    while start <= length - n:
        values = [int(x) for x in series[start:start+n]]
        sequences.append(values)
        start += 1
    return sequences

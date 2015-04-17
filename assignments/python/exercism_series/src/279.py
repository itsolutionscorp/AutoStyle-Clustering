def slices(series, length):
    if length > len(series) or length <= 0:
        raise ValueError
    series = map(int, series)
    ranges = zip(xrange(0, len(series)+1), xrange(length, len(series)+1))
    sliced_results = [series[x: y] for x, y in ranges]
    return sliced_results

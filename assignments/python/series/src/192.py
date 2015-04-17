def slices(series, length):
    if length > len(series) or length <= 0:
        raise ValueError
    series = map(int, series)
    ranges = zip(xrange(0, len(series)+1), xrange(length, len(series)+1))
    sliced_ranges = [slice(x, y) for x, y in ranges]
    sliced_results = map(lambda x: series[x], sliced_ranges)
    return sliced_results

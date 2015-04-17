def slices(series, size):
    series = [int(x) for x in series]
    if size <= 0 or size > len(series):
        raise ValueError
    offsets = range(len(series) - size + 1)
    return [series[x:x+size] for x in offsets]

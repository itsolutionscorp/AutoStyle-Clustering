def slices(series, size):
    if not 0 < size <= len(series):
        raise ValueError("I can't slice this big")
    slices = [list(series[i:size + i]) for i in range(0, len(series) - size + 1)]
    return [[int(y) for y in x] for x in slices]

def slices(series, length):
    result = []
    if len(series) < length or length == 0:
        raise ValueError
    for i in range(0, len(series)):
        if i + length - 1 < len(series):
            result.append(map(int, series[i : i + length]))
    return result

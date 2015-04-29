def slices(series, digit):
    if digit > len(series):
        raise ValueError("Series is too short")
    if digit == 0:
        raise ValueError("No digit given")
    series = map(int, series)
    return [series[x:x+digit] for x in range(len(series) - digit + 1)]

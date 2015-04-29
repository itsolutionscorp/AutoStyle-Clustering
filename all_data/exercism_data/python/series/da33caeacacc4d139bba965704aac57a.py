def slices(series, length):
    if length < 1 or length > len(series):
        raise ValueError("invalid length argument '{0}' for series of length {1}"
                            .format(length, len(series)))

    result = []
    for start in range(len(series) - length + 1):
        result.append(list(map(int, series[start:start + length])))

    return result

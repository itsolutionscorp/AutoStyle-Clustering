def slices(series, length):
    if length < 1 or length > len(series):
        raise ValueError('Bravo!')

    slices = []
    for i in range(len(series)):
        if i + length - 1 < len(series) or length == 1:
            slices.append([int(s) for s in series[i: i+length]])

    return slices

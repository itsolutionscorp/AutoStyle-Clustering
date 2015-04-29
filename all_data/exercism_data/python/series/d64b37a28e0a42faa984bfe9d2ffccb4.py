def slices(series, length):
    if not 0 < length <= len(series):
        raise(ValueError, 'length must be between one and %s, the length of series %s' % (len(series), series))
    slices = []
    series = [int(a) for a in list(series)]
    for i in range(0, len(series)):
        if i + length < len(series) + 1: slices.append(series[i:i+length])
    return slices

def slices(series, length):
    slices = []
    if len(series) < length:
        raise ValueError
    elif length == 0:
        raise ValueError
    else:
        for i in range(len(series)-length+1):
            slices.append(map(int, list(series[i:i+length])))
    return slices

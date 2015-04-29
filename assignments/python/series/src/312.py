def slices(series, length):
    if len(series) < length:
        raise ValueError
    elif length == 0:
        raise ValueError
    else:
        return [map(int, list(series[i:i+length]))
                for i in range(len(series)-length+1)]

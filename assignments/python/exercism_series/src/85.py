def slices(series, n):
    if n == 0:
        raise ValueError('n != 0')
    if n > len(series):
        raise ValueError('n !> series')
    series = [int(x) for x in series]
    return [list(series[i:i+n]) for i in range(len(series)-n+1)]

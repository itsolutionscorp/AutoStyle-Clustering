def slices(digitstring, length):
    digitlist = [int(i) for i in list(digitstring)]
    
    if length > len(digitlist) or length < 1:
        raise ValueError('invalid length')
    series = []
    for i in range(len(digitlist) - length + 1):
        series.append(digitlist[i:i+length])

    return series

def slices(digitstring, length):
    digitlist = [int(i) for i in list(digitstring)]
    
    if length > len(digitlist) or length < 1:
        raise ValueError('invalid length')

    series = [digitlist[i:i+length] for i in range(len(digitlist) - length + 1)]

    return series

def slices(NumSeries,length):
    if length < 1 or length > len(NumSeries):
        raise ValueError('Out of Bounds')
    sets = list()
    for s in range(0, len(NumSeries) - length + 1):
        t = NumSeries[s:(s + length)]
        x = [int(t[y]) for y in range(0,len(t))]
        sets.append(x)
    return sets

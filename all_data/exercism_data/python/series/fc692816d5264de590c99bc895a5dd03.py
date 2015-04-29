def slices(series, n):
    if n<1 or n>len(series):
        raise ValueError()
    result = []
    for position in range(0,len(series)):
        if position+n<=len(series):
            slice = [int(i) for i in series[position:position+n]]
            result.append(slice)
    return result

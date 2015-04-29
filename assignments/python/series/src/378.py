def slices(series, length):
    if length > len(series) or length < 1:
        raise ValueError
    intlist = [int(a) for a in series]
    slicelist = []
    for a in range(len(intlist)-length+1):
        slicelist.append(intlist[a:a+length])
    return slicelist

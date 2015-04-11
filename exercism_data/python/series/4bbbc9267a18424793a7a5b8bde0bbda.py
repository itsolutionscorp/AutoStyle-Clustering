def slices(series, length):
    if length > len(series) or length < 1:
        raise ValueError
    slicelist = []
    for a in range(len(series)-length+1):
        slicelist.append(list(series[a:a+length]))
    return [[int(b) for b in c] for c in slicelist]

def slices(series, portion):
    if portion > len(series) or portion == 0:
        raise ValueError
    members = []
    for i in xrange(len(series) + 1 - portion):
        members.append(map(lambda x: int(x), list(series[i:i+portion])))
    return members

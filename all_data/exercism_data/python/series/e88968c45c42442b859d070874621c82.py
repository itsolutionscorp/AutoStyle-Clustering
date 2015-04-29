def slices(series, length):
    slices = []
    if len(series) < length:
        raise ValueError
    elif length == 0:
        raise ValueError
    else:
        for i in range(len(series)-length+1):
            slices.append(list(series[i:i+length]))
    for slice in slices:
        for i in range(len(slice)):
            slice[i] = int(slice[i])
    return slices

print(slices('01234', 5))

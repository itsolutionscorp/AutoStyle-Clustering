def slices(numstring,slice_length):
    if slice_length > len(numstring):
        raise ValueError("Slice length %d is larger than string length %d" %(slice_length,len(numstring)))
    if slice_length == 0:
        raise ValueError("Slice length %d is not possible" %slice_length)
    s = numstring
    l = slice_length
    f = []
    for x in range(len(s)-l+1):
        f.append(list(map(int,list(s[x:x+l]))))
    return f

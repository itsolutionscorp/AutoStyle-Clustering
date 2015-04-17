def slices(series, of):
    if of == 0 or of > len(series):
        raise(ValueError)
    r = []
    s = [int(i) for i in series]
    for i in range(len(s)):
        if i+of <= len(s):
            r.append(s[i:i+of])
    return(r)

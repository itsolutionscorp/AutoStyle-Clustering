def slices(s, n):
    if n > len(s) or n==0:
        raise ValueError
    result = []
    for i in xrange(len(s)-n+1):
        result.append(map(int,list(s[i:i+n])))
    return result
